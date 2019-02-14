package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster.HelpManagePrenster;

public class HelpManagePublishViewpagerFragment extends Fragment {
    View view;
    RecyclerView helpPublishRecycleView;
    HelpManagePrenster helpManagePrenster=new HelpManagePrenster(getActivity());
    HelpManageListViewPublishAdapter helpManageListViewPublishAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_publish_help,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helpManagePrenster.attchView(iVew);
        swipeRefreshLayout=getActivity().findViewById(R.id.help_publish_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                helpManagePrenster.getListViewDataPublish();
            }
        });
        helpManageListViewPublishAdapter=helpManagePrenster.initHelpManageListViewPublishAdapter();
        helpManageListViewPublishAdapter.setOnClickListener(new HelpManageListViewPublishAdapter.OnItemClickListener() {
            @Override
            public void onClick(String UID) {
                Log.d("onClick","onClick");
                helpManagePrenster.updateStatusToFinish(UID);
            }
        });
        helpPublishRecycleView=view.findViewById(R.id.help_publish_listview);
        helpPublishRecycleView.setLayoutManager(new LinearLayoutManager(MyApplication.getMyContext()));
        helpPublishRecycleView.setAdapter(helpManageListViewPublishAdapter);
    }

    IVew iVew=new IVew() {
        @Override
        public void updateStatusToSuccess() {
            helpManageListViewPublishAdapter.updateStatusToFinish();
            helpManageListViewPublishAdapter.notifyDataSetChanged();
        }

        @Override
        public void dismissSwipeRefrshLayout() {
            swipeRefreshLayout.setRefreshing(false);
        }
    };
}
