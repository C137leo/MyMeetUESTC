package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Application;
import android.content.Context;

import dev.DevUtils;
import dev.utils.app.ADBUtils;

public class MyApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DevUtils.init(getApplicationContext());
    }
    public static Context getMyContext(){
        return context;
    }
}
