package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public class NavPrenster implements IPrenster{
    DaoSession daoSession=GreenDaoHelper.getDaoSession();
    private String stuID;
    @Override
    public String getStuId() {
        stuID=daoSession.loadAll(StuInfo.class).get(0).getStuID();
        return stuID;
    }
}
