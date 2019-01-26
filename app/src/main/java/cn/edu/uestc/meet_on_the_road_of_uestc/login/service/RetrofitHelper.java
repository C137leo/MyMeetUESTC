package cn.edu.uestc.meet_on_the_road_of_uestc.login.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    static RetrofitHelper instance=null;
    Context mContext;
    private Retrofit mRetrofit=null;
    OkHttpClient okHttpClient=new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    public RetrofitHelper(Context context){
        mContext=context;
    }
    public static RetrofitHelper getInstance(Context mContext){
        if(instance==null){
            instance=new RetrofitHelper(MyApplication.getMyContext());
        }
        return instance;
    }
    public void startRetrofitService(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl("https://www.happydoudou.xyz/")
                .addConverterFactory(factory)
                .client(okHttpClient)
                .build();
    }
    public RetrofitService getRetrofitService(){
        return mRetrofit.create(RetrofitService.class);
    }
}
