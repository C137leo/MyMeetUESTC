package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster.AppointmentPrensterMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentMeTitle;

public class AppointmentMe extends AppCompatActivity {
    AppointmentPrensterMe appointmentPrensterMe;
    AppointmentMeTitle appointmentMeTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentPrensterMe=new AppointmentPrensterMe(AppointmentMe.this);
        appointmentPrensterMe.attchView(iVew);
        setContentView(R.layout.activity_appointment_me);
        appointmentMeTitle=findViewById(R.id.appointment_me_title);
    }

    IVew iVew=new IVew() {
    };
}
