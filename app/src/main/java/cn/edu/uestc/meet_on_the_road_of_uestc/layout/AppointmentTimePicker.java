package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import androidx.annotation.RequiresApi;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentTimePicker extends RelativeLayout implements View.OnClickListener {
    MaterialCalendarView materialCalendarView;
    LinearLayout selectedTime;
    TextView selectedTimeShow;
    Button appointmentTimeSelectedCancel;
    Button appointmentTimeSelectedConfirm;
    SetAppointmentTimeClick setAppointmentTimeClick;
    RelativeLayout appointmentSelectTime;
    Button timePickerConfirm;
    String selectTime;
    TimePicker appointmentTimePicker;
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
        selectedTime=findViewById(R.id.selected_time);
        selectedTimeShow=findViewById(R.id.selected_time_show);
        appointmentTimeSelectedCancel=findViewById(R.id.appointment_add_time_cancel);
        appointmentTimeSelectedConfirm=findViewById(R.id.appointment_add_time_confirm);
        appointmentSelectTime=findViewById(R.id.appointment_time_selecter);
        timePickerConfirm=findViewById(R.id.appointment_time_pick_confirm);
        appointmentTimePicker=findViewById(R.id.time_picker);
        timePickerConfirm.setOnClickListener(this);
        selectedTime.setOnClickListener(this);
        appointmentTimeSelectedConfirm.setOnClickListener(this);
        appointmentTimeSelectedCancel.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_add_time_cancel:
                setAppointmentTimeClick.onCancelClick();
                break;
            case R.id.appointment_add_time_confirm:
                setAppointmentTimeClick.onConfirmClick();
                break;
            case R.id.selected_time:
                appointmentSelectTime.setVisibility(VISIBLE);
                Log.d("setVisible","setVisible");
                break;
            case R.id.appointment_time_pick_confirm:
                appointmentSelectTime.setVisibility(GONE);
                int hour=appointmentTimePicker.getHour();
                int minute=appointmentTimePicker.getMinute();
                if(minute>10||minute == 10) {
                    selectTime = hour + ":" + minute;
                }else{
                    selectTime=hour+":0"+minute;
                }
                selectedTimeShow.setText(selectTime);
                break;
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
