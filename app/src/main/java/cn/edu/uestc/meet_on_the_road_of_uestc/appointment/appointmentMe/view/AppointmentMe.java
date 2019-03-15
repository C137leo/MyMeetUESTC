package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter.AppointmentMeViewPagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster.AppointmentPrensterMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentMeTitle;

public class AppointmentMe extends AppCompatActivity implements ViewPager.OnPageChangeListener {
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
        setContentView(R.layout.activity_appointment_me);
        appointmentMeTitle=findViewById(R.id.appointment_me_title);
        appointmentMeToolbar=findViewById(R.id.appointment_me_toolbar);
        appointmentMeViewPager=findViewById(R.id.appointment_me_viewpager);
        appointmentMeAccept=new AppointmentMeAcceptFragment();
        appointmentMePublish=new AppointmentMePublishFragment();
        appointmentMeFragment=new ArrayList();
        appointmentMeTitle.initAppointmentMeTitleClick(new AppointmentMeTitle.AppointmentMeTitleListener() {
            @Override
            public void appointmentAcceptClick() {
                appointmentMeViewPager.setCurrentItem(0);
            }

            @Override
            public void appointmentPublishClick() {
                appointmentMeViewPager.setCurrentItem(1);

            }
        });
        appointmentMeFragment.add(appointmentMeAccept);
        appointmentMeFragment.add(appointmentMePublish);
        appointmentMeViewPagerAdapter=new AppointmentMeViewPagerAdapter(getSupportFragmentManager(),appointmentMeFragment);
        appointmentMeViewPager.setAdapter(appointmentMeViewPagerAdapter);
        appointmentMeViewPager.addOnPageChangeListener(this);
        appointmentMeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("onPageScroll",String.valueOf(position));
        if(position==0){
            appointmentMeTitle.setAppointmentTitleBackground(0);
        }else if(position==1){
            appointmentMeTitle.setAppointmentTitleBackground(1);
        }
    }

}
