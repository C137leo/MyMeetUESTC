package cn.edu.uestc.meet_on_the_road_of_uestc.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter.GoalSetViewpagerAdapter;

public class SetGoalActivity extends AppCompatActivity {
    ViewPager setGoalViewPager;
    List<Fragment> goalSetFragments;
    List<String> fragment_titles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSetGoalViewPager();
        setContentView(R.layout.activity_set_goal);
        setGoalViewPager=findViewById(R.id.setGoalViewpager);
        GoalSetViewpagerAdapter goalSetViewpagerAdapter=new GoalSetViewpagerAdapter(getSupportFragmentManager(),goalSetFragments,fragment_titles);
        setGoalViewPager.setAdapter(goalSetViewpagerAdapter);

    }

    public void setSetGoalViewPager(){
        goalSetFragments =new ArrayList<>();
        fragment_titles=new ArrayList<>();
        fragment_titles.add("时间");
        fragment_titles.add("距离");
        TimeSetFragment setTimeFragment=new TimeSetFragment(new String[]{"1:00","2:00","3:00","4:00","5:00"});
        DistanceSetFragment setDistanceFragment=new DistanceSetFragment(new String[]{"1km","2km","3km","5km","10km"});
        goalSetFragments.add(setTimeFragment);
        goalSetFragments.add(setDistanceFragment);
    }
}
