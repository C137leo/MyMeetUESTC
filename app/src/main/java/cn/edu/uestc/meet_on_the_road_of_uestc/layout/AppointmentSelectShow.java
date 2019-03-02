package cn.edu.uestc.meet_on_the_road_of_uestc.layout;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class AppointmentSelectShow extends LinearLayout {
    SelectOnClickListener onClickListener;
    TextView selectShow;
    public AppointmentSelectShow(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentSelectShow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_time_select,this,true);
        selectShow=findViewById(R.id.timeSelectedShow);
        selectShow.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        selectShow.getPaint().setAntiAlias(true); //抗锯齿
        selectShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.selectClick();
            }
        });
    }

    public interface SelectOnClickListener{
        void selectClick();
    }

    public void initOnClickLister(SelectOnClickListener onClickLister){
        this.onClickListener=onClickListener;
    }
}
