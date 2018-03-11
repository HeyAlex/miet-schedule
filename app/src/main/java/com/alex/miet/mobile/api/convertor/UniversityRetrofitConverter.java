package com.alex.miet.mobile.api.convertor;

import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class UniversityRetrofitConverter extends Converter.Factory {

    private final Converter.Factory xml;
    private final Converter.Factory json;

    @Inject
    public UniversityRetrofitConverter() {
        xml = SimpleXmlConverterFactory.create();
        json = GsonConverterFactory.create(new GsonBuilder()
                .setLenient()
                .create()
        );
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Xml.class) {
                return xml.responseBodyConverter(type, annotations, retrofit);
            }

            if (annotation.annotationType() == Json.class) {
                return json.responseBodyConverter(type, annotations, retrofit);
            }
        }
        return null;
    }
}