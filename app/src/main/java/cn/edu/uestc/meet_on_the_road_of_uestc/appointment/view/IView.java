package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public interface IView {
    void initAppointmentData(List<AppointmentInfo> appointmentInfoList);
    void getDataError(String errMsg);
}
