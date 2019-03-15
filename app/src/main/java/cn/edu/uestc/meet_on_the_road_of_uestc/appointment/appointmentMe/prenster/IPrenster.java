package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public interface IPrenster {

    void attchView(IVew iVew);
    List<AppointmentInfo> getAppointmentAcceptData();
    List<AppointmentInfo> getAppointmentPublishData();
    void appointmentLoadAllAppointmentInfo();
}
