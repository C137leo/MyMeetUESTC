package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter.AppointmentMeViewPagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster.AppointmentPrensterMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentMeTitle;

public class AppointmentMe extends AppCompatActivity {
    AppointmentMeAcceptFragment appointmentMeAccept;
    AppointmentMePublishFragment appointmentMePublish;
    AppointmentPrensterMe appointmentPrensterMe;
    AppointmentMeTitle appointmentMeTitle;
    Toolbar appointmentMeToolbar;
    ViewPager appointmentMeViewPager;
    List appointmentMeFragment;
    AppointmentMeViewPagerAdapter appointmentMeViewPagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentPrensterMe=new AppointmentPrensterMe(AppointmentMe.this);
        appointmentPrensterMe.attchView(iVew);
        setContentView(R.layout.activity_appointment_me);
        appointmentMeTitle=findViewById(R.id.appointment_me_title);
        appointmentMeToolbar=findViewById(R.id.appointment_me_toolbar);
        appointmentMeViewPager=findViewById(R.id.appointment_me_viewpager);
        appointmentMeAccept=new AppointmentMeAcceptFragment();
        appointmentMePublish=new AppointmentMePublishFragment();
        appointmentMeFragment.add(appointmentMeAccept);
        appointmentMeFragment.add(appointmentMePublish);
        appointmentMeViewPagerAdapter=new AppointmentMeViewPagerAdapter(getSupportFragmentManager(),appointmentMeFragment);
        appointmentMeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    IVew iVew=new IVew() {
    };
}
