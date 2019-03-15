package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter.AppointmentMeRecyclerViewAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster.AppointmentPrensterMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public class AppointmentMePublishFragment extends Fragment {
    RecyclerView appointmentPublishRecyclerView;
    View view;
    List<AppointmentInfo> appointmentMeAcceptInfos=new ArrayList<>();
    AppointmentMeRecyclerViewAdapter appointmentMeRecyclerViewAdapter=new AppointmentMeRecyclerViewAdapter(appointmentMeAcceptInfos,1);
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getMyContext());
    SwipeRefreshLayout appointmentMePublishRefresh;
    AppointmentPrensterMe appointmentPrensterMe=new AppointmentPrensterMe(MyApplication.getMyContext());
    public AppointmentMePublishFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me_publish_appointment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appointmentPublishRecyclerView=getActivity().findViewById(R.id.appointment_me_publish_recycler_view);
        appointmentMePublishRefresh=getActivity().findViewById(R.id.appointment_me_publish_swipe_refresh);
        appointmentPrensterMe.attchView(iVew);
        appointmentPublishRecyclerView.setAdapter(appointmentMeRecyclerViewAdapter);
        appointmentPublishRecyclerView.setLayoutManager(linearLayoutManager);
        appointmentMePublishRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateAppointmentData();
            }
        });
    }

    public void updateAppointmentData(){
        appointmentPrensterMe.appointmentLoadAllAppointmentInfo();
    }
    IVew iVew=new IVew() {
        @Override
        public void setAppointmentMeAccept() {

        }

        @Override
        public void setAppointmentMePublish() {
            appointmentMeRecyclerViewAdapter.updateAllData(appointmentMeAcceptInfos);
        }

        @Override
        public void hideRefreshing() {
            appointmentMePublishRefresh.setRefreshing(false);
        }

        @Override
        public void updateError(String errMsg) {
            Toast.makeText(MyApplication.getMyContext(),errMsg, Toast.LENGTH_SHORT).show();
        }
    };
}
