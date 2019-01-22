package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.run_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.gson.Gson;

import cn.edu.uestc.meet_on_the_road_of_uestc.Interface.UploadInformation;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import dev.utils.common.DateUtils;
import okhttp3.OkHttpClient;

public class FinishRunActivity extends AppCompatActivity implements UploadInformation {
    OkHttpClient okHttpClient;
    Gson gson;
    Button stopRunningButton;
    int runTime;
    TextView distanceText;
    TextView timeText;
    TextView aveSpeed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_finish_running);
        stopRunningButton=findViewById(R.id.stopRunningButton);
        runTime=getIntent().getIntExtra("runTime",0);
        timeText=findViewById(R.id.total_time);
        timeText.setText(DateUtils.secToTimeRetain(runTime));
        stopRunningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void uploadInformation() {

    }
}
