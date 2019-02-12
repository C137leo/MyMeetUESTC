package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.content.Intent;
import android.view.View;

import cn.edu.uestc.meet_on_the_road_of_uestc.login.view.IView;

public interface Prenster {

    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(IView view);

    void attachIncomingIntent(Intent intent);//暂时没用到

    void getLoginStatus();
}
