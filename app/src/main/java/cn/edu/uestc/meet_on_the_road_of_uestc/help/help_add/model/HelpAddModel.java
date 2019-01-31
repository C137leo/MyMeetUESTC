package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.model;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster.HelpAddPrenster;

public class HelpAddModel implements IModel{
    DaoSession daoSession=GreenDaoHelper.getDaoSession();
    List<StuInfo> stuInfoList=new ArrayList<StuInfo>();
    private String stuID;
    private String stuName;
    HelpAddPrenster helpAddPrenster=new HelpAddPrenster();
    @Override
    public void writeDataBases() {

    }

    @Override
    public void getStuInfo() {
        stuInfoList=daoSession.loadAll(StuInfo.class);
        this.stuID=stuInfoList.get(0).getStuID();
        this.stuName=stuInfoList.get(0).getStuName();
        helpAddPrenster.getStuInfoSuccess(stuID,stuName);
    }
}
