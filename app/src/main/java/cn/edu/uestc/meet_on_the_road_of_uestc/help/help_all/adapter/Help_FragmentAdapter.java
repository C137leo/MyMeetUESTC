package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.Help_InfoFragment;

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
        Log.d("Help_Fragment getItem","GetItem");
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

}
