package com.hey.mietunoff.mietunofficial.api;

import com.google.gson.GsonBuilder;
import com.hey.mietunoff.mietunofficial.api.convertor.UniversityRetrofitConvertor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Class used to generate {@link UniversityService}
 */
/*package*/ class UniversityServiceGenerator {

    private final static String SERVICE_MIET_ENDPOINT = "https://miet.ru";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder retrofitUniversityBuilder
            = new Retrofit.Builder()
            .baseUrl(SERVICE_MIET_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(new UniversityRetrofitConvertor());


    /*package*/
    static <S> S createScheduleService(Class<S> serviceClass) {
        Retrofit retrofit = retrofitUniversityBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
