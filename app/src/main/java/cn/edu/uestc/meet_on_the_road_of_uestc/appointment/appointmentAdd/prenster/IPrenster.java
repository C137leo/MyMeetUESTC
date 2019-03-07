package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.prenster;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public interface IPrenster {

    void addSingleAppointmentData(AppointmentInfo appointmentInfo);
    void initPublishData(int type,int number,String dateTime,String location,String introduction);
    void attchView(IVew iVew);
}
