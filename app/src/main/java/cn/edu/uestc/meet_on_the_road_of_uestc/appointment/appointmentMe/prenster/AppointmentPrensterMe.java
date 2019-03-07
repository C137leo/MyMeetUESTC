package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster;

import android.content.Context;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.IVew;

public class AppointmentPrensterMe implements IPrenster{
    Context context;
    IVew iVew;

    public AppointmentPrensterMe(Context context) {
        this.context = context;
    }

    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }
}
