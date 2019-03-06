package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.AppointmentMeFragment;

public class AppointmentMeViewPagerAdapter extends FragmentStatePagerAdapter {
    List<AppointmentMeFragment> appointmentMeFragmentList;

    public AppointmentMeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return appointmentMeFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return appointmentMeFragmentList.size();
    }
}
