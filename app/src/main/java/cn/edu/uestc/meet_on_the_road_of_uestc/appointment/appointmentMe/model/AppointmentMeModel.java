package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.model;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;

public class AppointmentMeModel implements IModel{

    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    @Override
    public void addAppointmentInfo(List<AppointmentInfo> appointmentInfoList) {
        for(AppointmentInfo appointmentInfo:appointmentInfoList) {
            daoSession.getAppointmentInfoDao().insertOrReplace(appointmentInfo);
        }
    }
}
