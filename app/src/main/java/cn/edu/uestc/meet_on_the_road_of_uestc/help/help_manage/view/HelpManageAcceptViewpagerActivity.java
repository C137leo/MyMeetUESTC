package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class HelpManageAcceptViewpagerActivity extends AppCompatActivity {
    ViewPager helpManageViewpager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accept_help);
    }
}
