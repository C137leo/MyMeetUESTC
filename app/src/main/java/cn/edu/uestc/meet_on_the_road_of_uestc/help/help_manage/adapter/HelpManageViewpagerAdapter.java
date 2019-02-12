package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManagePublishViewpagerFragment;

public class HelpManageViewpagerAdapter extends FragmentStatePagerAdapter {
    List helpManageActivityList;
    List<String> activityTitleList;

    public HelpManageViewpagerAdapter(FragmentManager fragmentManager, List helpManageActivityList, List activityTitleList) {
        super(fragmentManager);
        this.activityTitleList=activityTitleList;
        this.helpManageActivityList=helpManageActivityList;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return activityTitleList.get(position);
    }

    @Override
    public int getCount() {
        return activityTitleList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) helpManageActivityList.get(position);
    }
}
