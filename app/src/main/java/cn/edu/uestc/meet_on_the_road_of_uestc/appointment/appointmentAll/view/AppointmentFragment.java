package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.view.AppointmentAddActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.adapter.AppointmentAllRecyclerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.prenster.AppointmentPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.AppointmentMe;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

public class AppointmentFragment extends Fragment implements View.OnClickListener {
    RecyclerView appointmentAllRecyclerView;
    AppointmentAllRecyclerAdapter appointmentAllRecyclerAdapter=new AppointmentAllRecyclerAdapter(MyApplication.getMyContext(),new ArrayList<AppointmentInfo>());
    List<AppointmentInfo> appointmentInfoList;
    AppointmentPrenster appointmentPrenster=new AppointmentPrenster();
    ImageView appointmentAddView;
    ImageView appointmentMe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_appointment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appointmentPrenster.attchView(iView);
        appointmentAllRecyclerView=getActivity().findViewById(R.id.appointment_recyclerView);
        appointmentAllRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getMyContext()));
        appointmentAllRecyclerView.setAdapter(appointmentAllRecyclerAdapter);
        appointmentMe=getActivity().findViewById(R.id.appointment_me);
        appointmentAddView=getActivity().findViewById(R.id.appointment_add);
        setClickListener();
        appointmentPrenster.initAppointmentData();
    }

    public void setClickListener(){
        appointmentAddView.setOnClickListener(this);
        appointmentMe.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_add:
                Intent intent=new Intent(getActivity(), AppointmentAddActivity.class);
                startActivity(intent);
                break;
            case R.id.appointment_me:
                Intent intent1=new Intent(getActivity(), AppointmentMe.class);
                startActivity(intent1);
                break;
        }
    }
}
