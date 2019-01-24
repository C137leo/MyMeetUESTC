package cn.edu.uestc.meet_on_the_road_of_uestc.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter.GoalSetViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.run_activity.RunningActivity;

public class SetGoalActivity extends AppCompatActivity {
    ViewPager setGoalViewPager;
    List<Fragment> goalSetFragments;
    List<String> fragment_titles;
    PagerTitleStrip pagerTitleStrip;
    Button setGoal_startRun;
    ImageView setGoalBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSetGoalViewPager();
        setContentView(R.layout.activity_set_goal);
        setGoalViewPager=findViewById(R.id.setGoalViewpager);
        GoalSetViewpagerAdapter goalSetViewpagerAdapter=new GoalSetViewpagerAdapter(getSupportFragmentManager(),goalSetFragments,fragment_titles);
        setGoalViewPager.setAdapter(goalSetViewpagerAdapter);
        setGoalViewPager.setOffscreenPageLimit(1);
        pagerTitleStrip=findViewById(R.id.pagerTitleStrip);
        pagerTitleStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        setGoal_startRun=findViewById(R.id.pick_startRun);
        setGoalBack=findViewById(R.id.setGoalBack);
        setGoalBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setGoal_startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Poistion",String.valueOf(GoalSetViewpagerAdapter.getShowingFragment()));
                Log.d("Click Finfish","click");
                if(TimeSetFragment.getTime().contains(":")){
                    Log.d("get","get");
                    Intent intent=new Intent(SetGoalActivity.this, RunningActivity.class);
                    intent.putExtra("RunModel","倒计时模式");
                    intent.putExtra("time",TimeSetFragment.getTime());
                    startActivity(intent);
                }else if(DistanceSetFragment.getDistance().contains("km")){
                    Intent intent=new Intent(SetGoalActivity.this, RunningActivity.class);
                    intent.putExtra("RunModel","距离模式");
                    intent.putExtra("distance",DistanceSetFragment.getDistance());
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    public void setSetGoalViewPager(){
        goalSetFragments =new ArrayList<>();
        fragment_titles=new ArrayList<>();
        fragment_titles.add("时间");
        fragment_titles.add("距离");
        TimeSetFragment setTimeFragment=new TimeSetFragment(new String[]{"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00"});
        DistanceSetFragment setDistanceFragment=new DistanceSetFragment(new String[]{"1km","2km","3km","5km","10km"});
        goalSetFragments.add(setTimeFragment);
        goalSetFragments.add(setDistanceFragment);
    }
}
