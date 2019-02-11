package cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.IVew;

public class MePrenster implements IPrenster{
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    StuInfo stuInfo;
    IVew iVew;
    Context context;
    public MePrenster(Context context){
        this.context=context;
    }
    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public void getStuInfo() {
        stuInfo=daoSession.getStuInfoDao().loadAll().get(0);
        iVew.searchInformationSuccess(stuInfo);
    }

    @Override
    public Uri isImageChange() {
        SharedPreferences sharedPreferences= MyApplication.getMyContext().getSharedPreferences("ImageSave", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("isChange",false)){
            return FileProvider.getUriForFile(context,"cn.edu.uestc.meet_on_the_road_of_uestc",new File(sharedPreferences.getString("ImagePath",null)));
        }else {
            return null;
        }
    }


}
