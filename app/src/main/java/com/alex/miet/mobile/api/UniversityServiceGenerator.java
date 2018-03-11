package com.alex.miet.mobile.api;

import com.alex.miet.mobile.api.convertor.UniversityRetrofitConverter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
            .addConverterFactory(new UniversityRetrofitConverter());


    /*package*/
    static <S> S createScheduleService(Class<S> serviceClass) {
        Retrofit retrofit = retrofitUniversityBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
