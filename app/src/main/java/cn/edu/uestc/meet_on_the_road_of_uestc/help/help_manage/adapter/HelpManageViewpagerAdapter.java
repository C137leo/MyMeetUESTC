package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HelpManageViewpagerAdapter extends PagerAdapter {
    List<View> helpManageActivityList;
    List<String> activityTitleList;

    public HelpManageViewpagerAdapter(List<View> helpManageActivityList, List activityTitleList) {
        super();
        this.activityTitleList=activityTitleList;
        this.helpManageActivityList=helpManageActivityList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
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
        return helpManageActivityList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(helpManageActivityList.get(position));
        return helpManageActivityList.get(position);
    }
}
