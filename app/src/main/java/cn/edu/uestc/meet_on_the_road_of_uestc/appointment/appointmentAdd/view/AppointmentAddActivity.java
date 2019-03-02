package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentSelectTime;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentSelectTypePickerLayout;

public class AppointmentAddActivity extends AppCompatActivity {
    AppointmentSelectTypePickerLayout appointmentSelectTypePickerLayout;
    int appointmentType; //0:自习 1:吃饭 2:娱乐 3:其他
    AppointmentSelectTime appointmentSelectTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_add);
        appointmentSelectTypePickerLayout=findViewById(R.id.appointmentTypeSelected);
        appointmentSelectTime=findViewById(R.id.appointmentTimeSelected);
    }

    public void initClickListener(){
        appointmentSelectTime.initOnClickLister(new AppointmentSelectTime.SelectOnClickListener() {
            @Override
            public void selectClick() {

            }
        });
    }
}
