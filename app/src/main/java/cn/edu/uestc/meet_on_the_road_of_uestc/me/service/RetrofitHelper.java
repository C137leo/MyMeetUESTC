package cn.edu.uestc.meet_on_the_road_of_uestc.me.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    static RetrofitHelper instance;
    Retrofit retrofit;
    GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create();
    public static RetrofitHelper getInstance(){
        if(instance==null){
            instance=new RetrofitHelper();
        }
        return instance;
    }

    public  RetrofitService initRetrofitService(){
        retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .baseUrl("https://www.happydoudou.xyz/moou/")
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
