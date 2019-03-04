package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentSelectTime extends LinearLayout {
    SelectOnClickListener mOnClickListener;
    TextView selectShow;
    public AppointmentSelectTime(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentSelectTime(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_time_select,this,true);
        selectShow=findViewById(R.id.timeSelectedShow);
        selectShow.getPaint().setAntiAlias(true); //抗锯齿
        selectShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.selectClick();
            }
        });
    }

    public interface SelectOnClickListener{
        void selectClick();
    }
    public void setDateTimeTextShow(String textShow){
        selectShow.setText(textShow);
    }
    public void initOnClickLister(SelectOnClickListener mOnClickLister){
        this.mOnClickListener=mOnClickLister;
    }
}
