package cn.edu.uestc.meet_on_the_road_of_uestc.home.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    static RetrofitHelper instance;

    public static RetrofitHelper getInstance(){
        if(instance==null){
            RetrofitHelper instance=new RetrofitHelper();
        }
        return instance;
    }

    public WeatherService getService(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.seniverse.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(WeatherService.class);
    }
}
