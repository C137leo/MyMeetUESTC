package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.adapter.AppointmentMeRecyclerViewAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster.AppointmentPrensterMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public class AppointmentMeAcceptFragment extends Fragment {
    RecyclerView appointmentMeAcceptRecyclerView;
    View view;
    List<AppointmentInfo> appointmentMeAcceptList;
    AppointmentMeRecyclerViewAdapter appointmentMeRecyclerViewAdapter=new AppointmentMeRecyclerViewAdapter(appointmentMeAcceptList,0);
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getMyContext());
    AppointmentPrensterMe appointmentPrensterMe=new AppointmentPrensterMe(MyApplication.getMyContext());
    SwipeRefreshLayout appointmentMeAcceptRefresh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.from(getActivity()).inflate(R.layout.fragment_me_accept_appointment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appointmentMeAcceptRecyclerView=getActivity().findViewById(R.id.appointment_me_accept_recycler_view);
        appointmentMeAcceptRefresh=getActivity().findViewById(R.id.appointment_me_accept_swipe_refresh);
        appointmentPrensterMe.attchView(iVew);
        appointmentMeAcceptRecyclerView.setLayoutManager(linearLayoutManager);
        appointmentMeAcceptRecyclerView.setAdapter(appointmentMeRecyclerViewAdapter);
        appointmentMeAcceptRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
            appointmentMeRecyclerViewAdapter.updateAllData(appointmentMeAcceptList);
        }

        @Override
        public void setAppointmentMePublish() {

        }

        @Override
        public void hideRefreshing() {
            appointmentMeAcceptRefresh.setRefreshing(false);
        }

        @Override
        public void updateError(String errMsg) {
            Toast.makeText(MyApplication.getMyContext(),errMsg,Toast.LENGTH_SHORT).show();
        }
    };

}
