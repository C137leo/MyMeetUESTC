package cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.IpPrefix;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.IVew;
import dev.utils.common.DateUtils;

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

    @Override
    public void drawFirstChartWithIPublish() {
        List<HelpInfo> IPublishHelp=daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.Owner_name.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        for(HelpInfo helpInfo : IPublishHelp){
            Date date= DateUtils.parseDate(DateUtils.parseLong(helpInfo.getPublish_time()));
        }
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        iVew.initFirstChart(list);
    }

    @Override
    public void drawSecondChartWithIAccept() {
        List<HelpInfo> IPublishHelp=daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.Owner_name.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        for(HelpInfo helpInfo : IPublishHelp){
            Date date= DateUtils.parseDate(DateUtils.parseLong(helpInfo.getPublish_time()));
        }
        iVew.initSecondChart(IPublishHelp);
    }
}
