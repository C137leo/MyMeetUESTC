package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentTimePicker extends RelativeLayout implements View.OnClickListener {
    MaterialCalendarView materialCalendarView;
    LinearLayout selectedTimeShow;
    Button appointmentTimeSelectedCancel;
    Button appointmentTimeSelectedConfirm;
    SetAppointmentTimeClick setAppointmentTimeClick;
    TimePicker appointmentSelectTime;
    public AppointmentTimePicker(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_date_time_picker,this,true);
        materialCalendarView=findViewById(R.id.calendarView);
        selectedTimeShow=findViewById(R.id.selected_time_show);
        appointmentTimeSelectedCancel=findViewById(R.id.appointment_add_time_cancel);
        appointmentTimeSelectedConfirm=findViewById(R.id.appointment_add_time_confirm);
        appointmentSelectTime=findViewById(R.id.appointment_time_selecter);
        selectedTimeShow.setOnClickListener(this);
        appointmentTimeSelectedConfirm.setOnClickListener(this);
        appointmentTimeSelectedCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_add_time_cancel:
                setAppointmentTimeClick.onCancelClick();
            case R.id.appointment_add_time_confirm:
                setAppointmentTimeClick.onConfirmClick();
            case R.id.selected_time_show:
                appointmentSelectTime.setVisibility(VISIBLE);
        }
    }

    public interface SetAppointmentTimeClick{
        void onCancelClick();
        void onConfirmClick();
    }

    public void setAppointmentTimeClick(SetAppointmentTimeClick setAppointmentTimeClick){
        this.setAppointmentTimeClick=setAppointmentTimeClick;
    }
}
