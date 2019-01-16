package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Application;
import android.content.Context;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import dev.DevUtils;
import dev.utils.app.ADBUtils;

public class MyApplication extends TinkerApplication {
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
    }
    public static Context getMyContext(){
        return context;
    }
}
