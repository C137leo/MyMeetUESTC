package cn.edu.uestc.meet_on_the_road_of_uestc.login.model;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.NetWorkStatus;

public class LoginModel  implements IModel{
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    @Override
    public void writeDtabases(NetWorkStatus netWorkStatus) {
        StuInfo stuInfo=new StuInfo(netWorkStatus.getStuID(),netWorkStatus.getStuName(),netWorkStatus.getStuPassWord(),netWorkStatus.getStuSignature(),netWorkStatus.getStuGrade(),netWorkStatus.getNickName());
        daoSession.getStuInfoDao().insert(stuInfo);
    }
}
