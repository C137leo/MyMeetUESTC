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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter.Help_RecyclerViewAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster.PrensterComl;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.HelpDetailActivity;

public class Help_InfoFragment extends Fragment {
    private List<HelpInfo> mList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView mRecyclerView;
    Help_RecyclerViewAdapter help_recyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    PrensterComl prensterComl=new PrensterComl(MyApplication.getMyContext());
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
        prensterComl.attchView(iView);
        swipeRefreshLayout=getActivity().findViewById(R.id.help_all_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                prensterComl.getData();
            }
        });
        help_recyclerViewAdapter=new Help_RecyclerViewAdapter(getActivity(),mList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getMyContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        help_recyclerViewAdapter.setOnItemClickListener(new Help_RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position,String UID) {
                Intent intent=new Intent(getActivity(), HelpDetailActivity.class);
                intent.putExtra("UID",UID);
                Log.d("UID",String.valueOf(UID));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(help_recyclerViewAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        prensterComl.getData();
    }


    IView iView=new IView() {
        @Override
        public void hideRefershing() {
            Log.d("stopRefreshing","stopRefreshing");
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void updateData(List<cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo> helpInfoList) {
            help_recyclerViewAdapter.updateDataInFragment(helpInfoList);

        }
    };
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
    public void onDestroy() {
        super.onDestroy();
    }
}
