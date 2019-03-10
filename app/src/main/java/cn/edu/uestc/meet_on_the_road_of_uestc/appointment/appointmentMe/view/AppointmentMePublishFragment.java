package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentMePublishFragment extends Fragment {
    RecyclerView appointmentPublishRecyclerView;
    View view;
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
    }
}
