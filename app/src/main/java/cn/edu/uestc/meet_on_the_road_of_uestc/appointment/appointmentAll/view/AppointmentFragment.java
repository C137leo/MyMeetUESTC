package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.adapter.AppointmentAllRecyclerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.prenster.AppointmentPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public class AppointmentFragment extends Fragment {
    RecyclerView appointmentAllRecyclerView;
    AppointmentAllRecyclerAdapter appointmentAllRecyclerAdapter;
    List<AppointmentInfo> appointmentInfoList;
    AppointmentPrenster appointmentPrenster=new AppointmentPrenster();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_appointment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appointmentAllRecyclerView=getActivity().findViewById(R.id.appointment_recyclerView);
    }

    IView iView=new IView() {
        @Override
        public void updateAppointmentData(List<AppointmentInfo> appointmentInfoList) {
            appointmentAllRecyclerAdapter.updateAppointmentList(appointmentInfoList);
        }

        @Override
        public void getDataError(String errMsg) {

        }
    };
}
