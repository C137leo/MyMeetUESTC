package cn.edu.uestc.meet_on_the_road_of_uestc.navigation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.util.ConvertUtils;
import cn.addapp.pickers.widget.WheelListView;
import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

@SuppressLint("ValidFragment")
public class DistanceSetFragment extends Fragment {
    String[] goalList;
    View view;
    static String distance;
    @SuppressLint("ValidFragment")
    public DistanceSetFragment(String[] goalList){
        this.goalList=goalList;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view!=null){
            Log.d("checkViewgroup","checkViewGroup");
            ViewGroup mViewGroup=(ViewGroup)view.getParent();
            if(mViewGroup!=null) {
                Log.d("removeView","removeView");
                mViewGroup.removeView(view);
            }
        }
        view = getLayoutInflater().inflate(R.layout.adapter_fragment_goal_distance, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        WheelListView pickDistanceGoal;
        final TextView timeGoal;
        super.onActivityCreated(savedInstanceState);
        timeGoal=getActivity().findViewById(R.id.distanceGoal);
        pickDistanceGoal=getActivity().findViewById(R.id.pickDistanceGoal);
        pickDistanceGoal.setItems(goalList);
        pickDistanceGoal.setSelectedTextColor(0xFFFF00FF);
        LineConfig lineConfig=new LineConfig();
        lineConfig.setColor(Color.parseColor("#26A1BD"));
        lineConfig.setAlpha(100);
        pickDistanceGoal.setSelectedIndex(2);
        pickDistanceGoal.setOffset(3);
        lineConfig.setThick(ConvertUtils.toPx(MyApplication.getMyContext(),3));
        pickDistanceGoal.setLineConfig(lineConfig);
        pickDistanceGoal.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int i, String s) {
                distance=s;
                timeGoal.setText(s);
            }
        });
    }

    @Override
    public void onResume() {
        Log.d("LifeCycle","onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LifeCycle","onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d("LifeCycle","onDestoryView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("LifeCycle","onDestory");
        super.onDestroy();
    }

    public static String getDistance() {
        return distance;
    }
}
