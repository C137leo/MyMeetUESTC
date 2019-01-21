package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

public class GoalSetViewpagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> timeSetFragments;
    private FragmentManager fragmentManager;
    private List<String> fragment_titles;
    public GoalSetViewpagerAdapter(FragmentManager fragmentManager,List<Fragment> timeSetFragments,List<String> fragment_titles){
        super(fragmentManager);
        this.timeSetFragments=timeSetFragments;
        this.fragment_titles=fragment_titles;
    }
    @Override
    public Fragment getItem(int i) {
        Log.d("Position",String.valueOf(i));
        return timeSetFragments.get(i);
    }

    @Override
    public int getCount() {
        Log.d("size",String.valueOf(timeSetFragments.size()));
        return timeSetFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("PositionTitle",String.valueOf(position));
        Log.d("fragment_titles",fragment_titles.get(position));
        return fragment_titles.get(position);
    }

}
