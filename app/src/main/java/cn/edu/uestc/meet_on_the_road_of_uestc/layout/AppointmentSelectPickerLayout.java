package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentSelectPickerLayout extends LinearLayout {
    TextView typeStudy;
    TextView typeEat;
    TextView typeHappy;
    TextView typeOther;
    int type; //0:自习 1:吃饭 2:娱乐 3:其他
    public AppointmentSelectPickerLayout(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentSelectPickerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.appointment_type_picker,this,true);
        typeStudy=findViewById(R.id.type_study);
        typeEat=findViewById(R.id.type_eat);
        typeHappy=findViewById(R.id.type_happy);
        typeOther=findViewById(R.id.type_other);
        typeStudy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                typeHappy.setBackground(null);
                typeOther.setBackground(null);
                typeEat.setBackground(null);
                typeStudy.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
                type=0;
            }
        });
        typeEat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                typeHappy.setBackground(null);
                typeOther.setBackground(null);
                typeStudy.setBackground(null);
                typeEat.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
                type=1;
            }
        });
        typeHappy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                typeEat.setBackground(null);
                typeOther.setBackground(null);
                typeStudy.setBackground(null);
                typeHappy.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
                type=2;
            }
        });
        typeOther.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                typeHappy.setBackground(null);
                typeHappy.setBackground(null);
                typeStudy.setBackground(null);
                typeOther.setBackground(getResources().getDrawable(R.drawable.finish_help_button));
                type=3;
            }
        });
    }

    public int getType() {
        return type;
    }
}
