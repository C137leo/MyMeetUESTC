package cn.edu.uestc.meet_on_the_road_of_uestc.home.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.entity.BinPic;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.prenster.HomePrenster;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private ImageView bing_pic;
    private View view;
    TextView homeTemperature;
    TextView homeLocation;
    OkHttpClient mClient;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    HomePrenster homePrenster=new HomePrenster();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=getLayoutInflater().inflate(R.layout.fragment_home,container,false);
        bing_pic=view.findViewById(R.id.bing_pic);
        homeTemperature=view.findViewById(R.id.home_weather_temperature);
        homeLocation=view.findViewById(R.id.weather_location);
        homePrenster.attchIVew(iVew);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            getPic(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("GetPicError", "GetPicError");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Log.d("responData", responseData);
                    parseJSON(responseData);
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        homePrenster.getWeatherData();
    }

    public void getPic(okhttp3.Callback callback){
        bing_pic=view.findViewById(R.id.bing_pic);
        mClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1")
                .build();
        mClient.newCall(request).enqueue(callback);
    }

    private void parseJSON(String jsonData){
        Gson gson=new Gson();
        BinPic urlList=gson.fromJson(jsonData,new TypeToken<BinPic>(){}.getType());
        String url_bing=null;
        for(BinPic.ImagesBean imagesBean: urlList.getImages()){
            url_bing="http://www.bing.com"+imagesBean.getUrl();
        }
        pref=PreferenceManager.getDefaultSharedPreferences(MyApplication.getMyContext());
        editor=pref.edit();
        editor.putString("loadBingpic",url_bing);
        editor.apply();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPic();
            }
        });
    }

    private void setPic(){
        Glide.with(getActivity())
                .load(pref.getString("loadBingpic",""))
                .into(bing_pic);

    }

    IVew iVew=new IVew() {
        @Override
        public void setTemperature(String temperature) {
            homeTemperature.setText(temperature);
        }

        @Override
        public void setLocation(String location) {
            homeLocation.setText(location);
        }
    };
}
