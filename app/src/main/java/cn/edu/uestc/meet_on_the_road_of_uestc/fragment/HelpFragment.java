package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.adapter.Help_FragmentAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.bean.Help_Info;

public class HelpFragment extends Fragment {

    private List<Help_Info> mList;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    List<Help_InfoFragment> fragments;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_help,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager=getActivity().findViewById(R.id.help_viewPager);
        mTabLayout=getActivity().findViewById(R.id.help_tabLayout);
        initViewPager();
    }
    private void initViewPager(){
        List<String> titles=new ArrayList<>();
        titles.add("最新发布");
        titles.add("附近帮帮");
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new Help_InfoFragment());
        }
        Help_FragmentAdapter help_fragmentAdapter=new Help_FragmentAdapter(getChildFragmentManager(),titles,fragments);
        mViewPager.setAdapter(help_fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
