package cn.edu.uestc.meet_on_the_road_of_uestc.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class SetGoalActivity extends AppCompatActivity {
    ViewPager setGoalViewPager;
    List<GoalSetFragment> goalSetFragments;
    List<String> fragment_titles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSetGoalViewPager();
        setContentView(R.layout.activity_set_goal);
        setGoalViewPager=findViewById(R.id.setGoalViewpager);
        setGoalViewPager.setOffscreenPageLimit(2);
        setGoalViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return goalSetFragments.get(i);
            }

            @Override
            public int getCount() {
                return goalSetFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return fragment_titles.get(position);
            }

        });
    }

    public void setSetGoalViewPager(){
        goalSetFragments =new ArrayList<>();
        fragment_titles=new ArrayList<>();
        fragment_titles.add("时间");
        fragment_titles.add("距离");
        GoalSetFragment setTimeFragment=new GoalSetFragment(new String[]{"1:00","2:00","3:00","4:00","5:00"});
        GoalSetFragment setDistanceFragment=new GoalSetFragment(new String[]{"1km","2km","3km","5km","10km"});
        goalSetFragments.add(setTimeFragment);
        goalSetFragments.add(setDistanceFragment);
    }
}
