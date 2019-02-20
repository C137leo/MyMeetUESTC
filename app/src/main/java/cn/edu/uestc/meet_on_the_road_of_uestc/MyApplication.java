package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.content.Context;
import android.content.Intent;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import cn.edu.uestc.meet_on_the_road_of_uestc.chat.view.ChatActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.exceptions.JMessageException;
import cn.jpush.im.android.api.model.UserInfo;
import dev.DevUtils;

public class MyApplication extends TinkerApplication {
    GreenDaoHelper greenDaoHelper=new GreenDaoHelper();
    static String jiguanAppkey="d240a417f22da27f1922aba3";
    public MyApplication(){
        super(ShareConstants.TINKER_ENABLE_ALL, "cn.edu.uestc.meet_on_the_road_of_uestc.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DevUtils.init(getApplicationContext());
        greenDaoHelper.initGreenDao();
        JAnalyticsInterface.init(context);
        JAnalyticsInterface.setDebugMode(true);
        JPushInterface.init(context);
        JPushInterface.setDebugMode(true);
        JMessageClient.init(context,true);
        JMessageClient.registerEventReceiver(this);
    }
    public static Context getMyContext(){
        return context;
    }

    public static String getJiguangAppkey(){
        return jiguanAppkey;
    }

    public void onEvent(NotificationClickEvent event){
        Intent notificationIntent = new Intent(context, ChatActivity.class);
        UserInfo userInfo= (UserInfo) event.getMessage().getTargetInfo();
        notificationIntent.putExtra("StuID",userInfo.getUserName());
        context.startActivity(notificationIntent);//自定义跳转到指定页面
    }
}
