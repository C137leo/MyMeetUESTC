package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster.HelpManagePrenster;

public class HelpManageActivity extends AppCompatActivity {
    ViewPager helpManageViewpager;
    HelpManagePrenster helpManagePrenster=new HelpManagePrenster(HelpManageActivity.this,getSupportFragmentManager());
    HelpManageViewpagerAdapter helpManageViewpagerAdapter;
    TabLayout helpManageTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_manage);
        helpManageViewpager=findViewById(R.id.help_manage_viewPager);
        helpManageTabLayout=findViewById(R.id.help_manage_tabTitle);
        helpManageViewpagerAdapter=helpManagePrenster.initViewPagerAdapter();
        helpManageViewpager.setAdapter(helpManageViewpagerAdapter);
        helpManageTabLayout.setupWithViewPager(helpManageViewpager);
    }
}
