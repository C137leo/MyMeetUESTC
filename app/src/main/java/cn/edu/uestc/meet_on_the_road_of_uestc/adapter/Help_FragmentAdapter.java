package cn.edu.uestc.meet_on_the_road_of_uestc.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.fragment.Help_InfoFragment;

public class Help_FragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitle;
    private List<Help_InfoFragment> help_infoFragments;
    public Help_FragmentAdapter(FragmentManager fragmentManager,List<String> mTitle, List<Help_InfoFragment> help_infoFragments){
        super(fragmentManager);
        this.mTitle=mTitle;
        this.help_infoFragments=help_infoFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return help_infoFragments.get(i);
    }

    @Override
    public int getCount() {
        return help_infoFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
