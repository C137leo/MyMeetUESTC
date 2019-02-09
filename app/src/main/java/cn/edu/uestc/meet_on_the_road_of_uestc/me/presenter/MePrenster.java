package cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.IVew;

public class MePrenster implements IPrenster{
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    StuInfo stuInfo;
    IVew iVew;


    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public void getStuInfo() {
        stuInfo=daoSession.getStuInfoDao().loadAll().get(0);
        iVew.searchInformationSuccess(stuInfo);
    }
}
