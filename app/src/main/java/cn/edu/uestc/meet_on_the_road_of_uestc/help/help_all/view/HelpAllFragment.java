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
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.view.MessageAllActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view.HelpAddActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter.Help_FragmentAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster.PrensterComl;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageActivity;

public class HelpAllFragment extends Fragment implements View.OnClickListener {

    private List<HelpInfo> mList;
    ViewPager mViewPager;
    List fragments;
    TabLayout tabLayout;
    FloatingActionButton helpAddButton;
    FloatingActionButton helpMyself;
    PrensterComl prensterComl=new PrensterComl(getActivity());
    Help_FragmentAdapter help_fragmentAdapter;
    ImageView enterMessageList;
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
        enterMessageList=getActivity().findViewById(R.id.enter_message_list);
        enterMessageList.setOnClickListener(this);
        helpAddButton=getActivity().findViewById(R.id.add_help_button);
        helpMyself=getActivity().findViewById(R.id.my_help_button);
        helpMyself.setOnClickListener(this);
        helpAddButton.setOnClickListener(this);
        initViewPager();
    }
    private void initViewPager(){
        List<String> titles=new ArrayList<>();
        titles.add("最新发布");
        titles.add("附近帮帮");
        fragments = new ArrayList<>();
        fragments.add(new HelpInfoLatestFragment());
        fragments.add(new HelpInfoNearbyFragment());
        help_fragmentAdapter=new Help_FragmentAdapter(getChildFragmentManager(),titles,fragments);
        mViewPager.setAdapter(help_fragmentAdapter);
        mViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_help_button:
                Intent myIntent=new Intent(getActivity(), HelpManageActivity.class);
                startActivity(myIntent);
                break;
            case R.id.add_help_button:
                Intent addIntent=new Intent(getActivity(), HelpAddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.enter_message_list:
                Intent messageListIntent=new Intent(getActivity(), MessageAllActivity.class);
                startActivity(messageListIntent);
                break;
        }
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
