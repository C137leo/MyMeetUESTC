package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.HelpInfoLatestFragment;

public class Help_FragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitle;
    private List<HelpInfoLatestFragment> help_infoLatestFragments;
    public Help_FragmentAdapter(FragmentManager fragmentManager,List<String> mTitle, List<HelpInfoLatestFragment> help_infoLatestFragments){
        super(fragmentManager);
        this.mTitle=mTitle;
        this.help_infoLatestFragments = help_infoLatestFragments;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d("Help_Fragment getItem",String.valueOf(i));
        return help_infoLatestFragments.get(i);
    }

    @Override
    public int getCount() {
        return help_infoLatestFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

}
