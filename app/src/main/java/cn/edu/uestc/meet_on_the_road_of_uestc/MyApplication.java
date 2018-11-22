package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Application;
import android.content.Context;

import com.mapbox.mapboxsdk.Mapbox;

public class MyApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Mapbox.getInstance(getApplicationContext(),getString(R.string.mapbox_access_token));
        context = getApplicationContext();
    }
    public static Context getMyContext(){
        return context;
    }
}
