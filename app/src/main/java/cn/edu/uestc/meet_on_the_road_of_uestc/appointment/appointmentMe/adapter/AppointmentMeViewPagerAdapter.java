package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.AppointmentMeAcceptFragment;

public class AppointmentMeViewPagerAdapter extends FragmentStatePagerAdapter {
    List appointmentMeFragment;

    public AppointmentMeViewPagerAdapter(FragmentManager fm, List appointmentMeFragment) {
        super(fm);
        this.appointmentMeFragment=appointmentMeFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) appointmentMeFragment.get(position);
    }

    @Override
    public int getCount() {
        return appointmentMeFragment.size();
    }

}
