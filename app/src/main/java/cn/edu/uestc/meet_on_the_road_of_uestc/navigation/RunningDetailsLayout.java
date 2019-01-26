package cn.edu.uestc.meet_on_the_road_of_uestc.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class RunningDetailsLayout extends RelativeLayout {
    private Context mContext;
    private TextView distanceDetail;
    private TextView timeDetail;
    private TextView speedDetail;
    private String distanceText;
    private String timeText;
    private String speedText;
    private Button pauseRunning;
    private Button stopRunning;
    buttonRunningListener buttonRunningListener;
    public RunningDetailsLayout(Context context){
        super(context);
        initView(context);
    }

    public RunningDetailsLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.RunningDetails);
        distanceText=typedArray.getString(R.styleable.RunningDetails_distance);
        timeText=typedArray.getString(R.styleable.RunningDetails_time);
        speedText=typedArray.getString(R.styleable.RunningDetails_speed);
        typedArray.recycle();
        initView(context);
    }
    public RunningDetailsLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.running_details_layout,this,true);
        distanceDetail=findViewById(R.id.distanceDetail);
        timeDetail=findViewById(R.id.timeDetail);
        speedDetail=findViewById(R.id.speedDetail);
        distanceDetail.setText(distanceText);
        timeDetail.setText(timeText);
        speedDetail.setText(speedText);
        pauseRunning=findViewById(R.id.pauseRun);
        stopRunning=findViewById(R.id.stopRun);
        pauseRunning.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRunningListener.pauseRunning();
            }
        });
        stopRunning.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRunningListener.stopRunning();
            }
        });
    }

    public interface buttonRunningListener{
        void pauseRunning();
        void stopRunning();
    }

    public void setButtonRunningListener(buttonRunningListener buttonRunningListener){
        this.buttonRunningListener=buttonRunningListener;
    }
}
