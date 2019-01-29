package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    static Retrofit retrofit;
    private Context mContext;
    private static RetrofitHelper retrofitHelper;
    GsonConverterFactory converterFactory=GsonConverterFactory.create(new GsonBuilder().create());

    public RetrofitHelper(Context mContext){
        this.mContext=mContext;
    }

    public static RetrofitHelper getInstance(Context mContext){
        if(retrofitHelper==null){
            retrofitHelper=new RetrofitHelper(mContext);
        }
        return retrofitHelper;
    }

    public void startRetrofitService(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://www.happydoudou.xyz")
                .addConverterFactory(converterFactory)
                .build();
    }

    public RetrofitService getRetrofitService(Context mContext){
        return retrofit.create(RetrofitService.class);
    }
}
