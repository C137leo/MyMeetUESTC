package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    Retrofit retrofit;
    static RetrofitHelper instance;
    Context context;
    RxJava2CallAdapterFactory rxJava2CallAdapterFactory=RxJava2CallAdapterFactory.create();
    GsonConverterFactory gsonConverterFactory= GsonConverterFactory.create(new Gson());
    public static RetrofitHelper getInstance(){
        if(instance==null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }

    public Retrofit initRetrofit(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://www.happydoudou.xyz/moou/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
        return retrofit;
    }

    public RetrofitService getRetrofitService(){
        initRetrofit();
        return retrofit.create(RetrofitService.class);
    }
}
