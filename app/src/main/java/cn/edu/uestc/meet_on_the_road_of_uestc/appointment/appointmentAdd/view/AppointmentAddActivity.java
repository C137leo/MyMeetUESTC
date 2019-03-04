package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.entity.TimeMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.prenster.AppointmentAddPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.prenster.AppointmentPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentNumberLayout;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentSelectTime;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentSelectTypePickerLayout;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.AppointmentTimePicker;
import cn.jpush.im.android.api.event.MessageEvent;

public class AppointmentAddActivity extends AppCompatActivity implements View.OnClickListener {
    AppointmentSelectTypePickerLayout appointmentSelectTypePickerLayout;
    int appointmentType; //0:自习 1:吃饭 2:娱乐 3:其他
    AppointmentSelectTime appointmentSelectTime;
    int dateText;
    int timeText;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    AppointmentTimePicker appointmentTimePicker;
    AppointmentNumberLayout appointmentNumberLayout;
    Button appointmentAddPublish;
    EditText appointmentLocation;
    EditText appointmentIntroductionAdd;
    AppointmentAddPrenster appointmentAddPrenster;
    String dateTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentAddPrenster=new AppointmentAddPrenster(AppointmentAddActivity.this);
        setContentView(R.layout.activity_appointment_add);
        appointmentAddPrenster.attchView(iVew);
        appointmentSelectTypePickerLayout=findViewById(R.id.appointmentTypeSelected);
        appointmentSelectTime=findViewById(R.id.appointmentTimeSelected);
        appointmentTimePicker=findViewById(R.id.appointment_time_picker);
        appointmentNumberLayout=findViewById(R.id.appointment_number_picker);
        appointmentAddPublish=findViewById(R.id.appointment_publish);
        appointmentLocation=findViewById(R.id.appointment_add_location);
        appointmentIntroductionAdd=findViewById(R.id.appointment_introduction_add);
        appointmentTimePicker.setAppointmentTimeClick(new AppointmentTimePicker.SetAppointmentTimeClick() {
            @Override
            public void onCancelClick() {
                appointmentTimePicker.setVisibility(View.GONE);
            }

            @Override
            public void onConfirmClick() {
                appointmentTimePicker.setVisibility(View.GONE);
            }
        });
        EventBus.getDefault().register(this);
        initClickListener();
    }

    public void initClickListener(){
        appointmentSelectTime.initOnClickLister(new AppointmentSelectTime.SelectOnClickListener() {
            @Override
            public void selectClick() {
                appointmentTimePicker.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_publish:
                appointmentAddPrenster.initPublishData(appointmentType,appointmentNumberLayout.getNumber(),dateTime,appointmentLocation.getText().toString(),appointmentIntroductionAdd.getText().toString());
        }
    }

    IVew iVew=new IVew() {
        @Override
        public void publishSuccessfully(String errMsg) {
            finish();
        }

        @Override
        public void publishError(String errMsg) {
            Toast.makeText(AppointmentAddActivity.this,errMsg,Toast.LENGTH_SHORT).show();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUI(TimeMessage timeMessage){
        if(timeMessage.getSelectTime()!=null) {
            dateTime=timeMessage.getDate() + "     " + timeMessage.getSelectTime();
            appointmentSelectTime.setDateTimeTextShow(timeMessage.getDate() + "     " + timeMessage.getSelectTime());
        }else if(timeMessage.getDate()==null) {
            Toast.makeText(this,"请选择时间",Toast.LENGTH_SHORT).show();
        }else if(timeMessage.getSelectTime()==null){
            dateTime=timeMessage.getDate();
            appointmentSelectTime.setDateTimeTextShow(timeMessage.getDate());
        }
    }
}
