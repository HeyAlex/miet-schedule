package com.alex.miet.miet_api.convertor

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type

class MietConverter : Converter.Factory() {
    private val xml: Converter.Factory
    private val json: Converter.Factory
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation.annotationClass == Xml::class.java) {
                return xml.responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation.annotationClass == Json::class.java) {
                return json.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }

    init {
        @Suppress("DEPRECATION")
        xml = SimpleXmlConverterFactory.create()
        json = GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }
}