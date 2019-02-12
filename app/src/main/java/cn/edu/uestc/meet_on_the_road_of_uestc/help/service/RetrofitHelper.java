package cn.edu.uestc.meet_on_the_road_of_uestc.help.service;

import android.content.Context;

import com.google.gson.Gson;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private Context mContext;
    private Retrofit retrofit;
    GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create(new Gson());
    static RetrofitHelper instance;
    public RetrofitHelper(Context context){
        mContext=context;
    }

    public static RetrofitHelper getInstance(Context context){
        if(instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }
    public void startRetrofitService(Context context) {
        retrofit=new Retrofit.Builder()
                .baseUrl("https://www.happydoudou.xyz/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getRetrofitService(Context context){
        startRetrofitService(context);
        return retrofit.create(RetrofitService.class);
    }
}
