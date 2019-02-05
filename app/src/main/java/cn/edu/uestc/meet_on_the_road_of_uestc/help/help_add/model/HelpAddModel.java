package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.model;

import android.content.Context;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;

public class HelpAddModel implements IModel{

    Context mContext;

    DaoSession daoSession= GreenDaoHelper.getDaoSession();

    public HelpAddModel(){

    }

    public HelpAddModel(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public void writeDataBases(HelpInfo helpInfo) {

    }

}
