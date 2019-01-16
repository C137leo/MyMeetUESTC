package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.adapter.Help_RecyclerViewAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.bean.Help_Info;

public class Help_InfoFragment extends Fragment {
    private List<Help_Info> mList;
    LinearLayoutManager linearLayoutManager;
    RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.fragment_viewpager_help,container,false);
        mRecyclerView=view.findViewById(R.id.good_info);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHelpData();
        Help_RecyclerViewAdapter help_recyclerViewAdapter=new Help_RecyclerViewAdapter(getActivity(),mList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getMyContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(help_recyclerViewAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        help_recyclerViewAdapter.notifyDataSetChanged();
    }
    public void initHelpData(){
        mList=new ArrayList<Help_Info>();
        Help_Info data1=new Help_Info("ray","重金求子","10","10");
        Help_Info data2=new Help_Info("RAY","重金求子","10","10");
        mList.add(data1);
        mList.add(data2);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LifeCycle","Viewpager Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LifeCycle","Viewpager Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LifeCycle","Viewpager Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","Viewpager Destory");
    }
}
