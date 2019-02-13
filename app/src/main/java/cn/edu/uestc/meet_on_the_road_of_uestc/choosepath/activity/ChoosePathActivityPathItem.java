package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class ChoosePathActivityPathItem  extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepath_path);
        ImageView choosePathButton=(ImageView) this.findViewById(R.id.choosethispath1);
        choosePathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(ChoosePathActivityPathItem.this,ChoosePathActivity.class);
                    startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
