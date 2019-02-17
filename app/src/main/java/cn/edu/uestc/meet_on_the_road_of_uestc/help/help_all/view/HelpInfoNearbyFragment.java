package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpInfoWithDistance;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter.Help_RecyclerViewAdapterNearBy;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster.PrensterComl;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.HelpDetailActivity;

public class HelpInfoNearbyFragment extends Fragment {
    private List<HelpInfoWithDistance> mList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView mRecyclerView;
    Help_RecyclerViewAdapterNearBy help_recyclerViewAdapterNearBy;
    SwipeRefreshLayout swipeRefreshLayout;
    PrensterComl prensterComl=new PrensterComl(MyApplication.getMyContext());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.fragment_viewpager_help_nearby,container,false);
        mRecyclerView=view.findViewById(R.id.good_info_nearby);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prensterComl.attchViewNearBy(iViewLatestNearby);
        swipeRefreshLayout=getActivity().findViewById(R.id.help_all_refresh_layout_nearby);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("onRefresh","onRefresh");
                swipeRefreshLayout.setRefreshing(true);
                prensterComl.getData();
            }
        });
        help_recyclerViewAdapterNearBy =new Help_RecyclerViewAdapterNearBy(getActivity(),mList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getMyContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        help_recyclerViewAdapterNearBy.setOnItemClickListener(new Help_RecyclerViewAdapterNearBy.onItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position,String UID) {
                Intent intent=new Intent(getActivity(), HelpDetailActivity.class);
                intent.putExtra("UID",UID);
                Log.d("UID",UID);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(help_recyclerViewAdapterNearBy);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        prensterComl.getData();
    }


    IVewNearBy iViewLatestNearby =new IVewNearBy() {
        @Override
        public void hideRefershing() {
            Log.d("stopRefreshing","stopRefreshing");
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void updateData(List<HelpInfoWithDistance> helpInfoList) {
            help_recyclerViewAdapterNearBy.updateDataInFragment(helpInfoList);
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        prensterComl.getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        prensterComl.getData();
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
    public void onDestroy() {
        super.onDestroy();
    }
}
