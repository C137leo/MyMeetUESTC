package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoMaster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;
import dev.DevUtils;

public class MyApplication extends TinkerApplication {
    GreenDaoHelper greenDaoHelper=new GreenDaoHelper();
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
    }
    public static Context getMyContext(){
        return context;
    }
}
