package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.model;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public interface IModel {

    void addAppointmentInfo(List<AppointmentInfo> appointmentInfoList);
}
