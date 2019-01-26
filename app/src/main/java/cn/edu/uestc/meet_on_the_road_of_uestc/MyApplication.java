package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoMaster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import dev.DevUtils;
import dev.utils.app.ADBUtils;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;

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

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "moou.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
