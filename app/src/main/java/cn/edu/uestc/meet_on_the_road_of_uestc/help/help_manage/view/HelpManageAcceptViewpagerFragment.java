package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.amap.api.maps.MapView;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewAcceptAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster.HelpManagePrenster;

public class HelpManageAcceptViewpagerFragment extends Fragment {
    View view;
    MapView mapView;
    HelpManagePrenster helpManagePrenster=new HelpManagePrenster(MyApplication.getMyContext());
    RecyclerView acceptRecycleView;
    HelpManageListViewAcceptAdapter helpManageListViewAcceptAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_accept_help,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("onActivityCreated","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        acceptRecycleView=view.findViewById(R.id.help_accept_recycleview);
        helpManageListViewAcceptAdapter=helpManagePrenster.initHelpManageListViewAcceptAdapter();
        acceptRecycleView.setLayoutManager(new LinearLayoutManager(MyApplication.getMyContext()));
        acceptRecycleView.setAdapter(helpManageListViewAcceptAdapter);
        mapView=helpManageListViewAcceptAdapter.getAcceptMapView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
