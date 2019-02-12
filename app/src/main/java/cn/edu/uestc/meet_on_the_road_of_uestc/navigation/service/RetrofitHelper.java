package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    static RetrofitHelper instance;
    private Retrofit retrofit;
    GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create(new Gson());


    public static RetrofitHelper getInstance(){
        if(instance==null){
            instance=new RetrofitHelper();
        }
        return instance;
    }

    public void startRetrofirService(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://www.happydoudou.xyz/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getRetrofitService(){
        startRetrofirService();
        return retrofit.create(RetrofitService.class);
    }
}
