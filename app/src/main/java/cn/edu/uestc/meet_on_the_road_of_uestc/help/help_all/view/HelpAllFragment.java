package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MainActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view.HelpAddActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter.Help_FragmentAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.Help_InfoFragment;

public class HelpAllFragment extends Fragment implements IView{

    private List<HelpInfo> mList;
    ViewPager mViewPager;
    List<Help_InfoFragment> fragments;
    TabLayout tabLayout;
    FloatingActionButton helpAddButton;
    FloatingActionButton helpMyself;
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
        helpAddButton=getActivity().findViewById(R.id.add_help_button);
        helpMyself=getActivity().findViewById(R.id.my_help_button);
        helpAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HelpAddActivity.class);
                startActivity(intent);
            }
        });
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
    public void updateData() {

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
