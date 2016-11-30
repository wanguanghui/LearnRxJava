package com.wochacha.learnrxjava.network;

import com.wochacha.learnrxjava.network.api.ZhuangBiApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public class Network {

    private static ZhuangBiApi zhuangBiApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory scalarsConverterFactory = ScalarsConverterFactory.create();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhuangBiApi getZhuangBiApi(){
        if (zhuangBiApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(scalarsConverterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangBiApi = retrofit.create(ZhuangBiApi.class);
        }
        return zhuangBiApi;
    }

}
