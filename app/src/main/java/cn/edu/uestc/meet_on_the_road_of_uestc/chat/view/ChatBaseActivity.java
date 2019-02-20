package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.jpush.im.android.api.JMessageClient;

public class ChatBaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
