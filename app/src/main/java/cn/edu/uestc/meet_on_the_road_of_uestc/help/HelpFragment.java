package cn.edu.uestc.meet_on_the_road_of_uestc.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.adapter.Help_FragmentAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.view.Help_InfoFragment;

public class HelpFragment extends Fragment {

    private List<HelpInfo> mList;
    ViewPager mViewPager;
    List<Help_InfoFragment> fragments;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_help,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager=getActivity().findViewById(R.id.help_viewPager);
        tabLayout=getActivity().findViewById(R.id.tabTitle);
        initViewPager();
    }
    private void initViewPager(){
        List<String> titles=new ArrayList<>();
        titles.add("最新发布");
        titles.add("附近帮帮");
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new Help_InfoFragment());
        }
        Help_FragmentAdapter help_fragmentAdapter=new Help_FragmentAdapter(getChildFragmentManager(),titles,fragments);
        mViewPager.setAdapter(help_fragmentAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
