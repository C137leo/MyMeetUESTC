package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;

public class GreenDaoHelper {


    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    public void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getMyContext(), "moou.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private static DaoSession daoSession;
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
