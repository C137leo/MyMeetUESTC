package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentMeTitle extends RelativeLayout implements View.OnClickListener {
    TextView appointmentAccept;
    TextView appointmentPublish;
    AppointmentMeTitleListener appointmentMeTitleListener;
    public AppointmentMeTitle(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentMeTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.appointment_me_title,this,true);
        appointmentAccept=findViewById(R.id.appointment_accept);
        appointmentPublish=findViewById(R.id.appointment_publish);
        appointmentAccept.setOnClickListener(this);
        appointmentPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_accept:
                appointmentMeTitleListener.appointmentAcceptClick();
                appointmentAccept.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
                appointmentPublish.setBackground(null);
            case R.id.appointment_publish:
                appointmentMeTitleListener.appointmentPublishClick();
                appointmentAccept.setBackground(null);
                appointmentPublish.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
        }
    }

    public interface AppointmentMeTitleListener{
        void appointmentAcceptClick();
        void appointmentPublishClick();
    }

    public void initAppointmentMeTitleClick(AppointmentMeTitleListener appointmentMeTitleListener){
        this.appointmentMeTitleListener=appointmentMeTitleListener;
    }
}
