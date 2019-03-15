package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public interface IVew {

    void setAppointmentMeAccept(List<AppointmentInfo> appointmentMeAccept);
    void setAppointmentMePublish(List<AppointmentInfo> appointmentMePublish);
    void hideRefreshing();
    void updateError(String errMsg);
}
