package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Objects;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class ChatActivity extends AppCompatActivity  implements View.OnClickListener {
    Toolbar toolbar;
    ImageView chat_options_button;
    LinearLayout otherOption;
    ImageView chatAlbum;
    ImageView chatLocation;
    int OPTION_STATUS=0; //0时其他选项为关闭，1时为展开
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        toolbar=findViewById(R.id.chat_detail_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //启动返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chat_options_button=findViewById(R.id.other_option_button);
        chat_options_button.setOnClickListener(this);
        otherOption=findViewById(R.id.other_option);
        chatAlbum=findViewById(R.id.chat_album);
        chatLocation=findViewById(R.id.chat_location);
        chatAlbum.setOnClickListener(this);
        chatLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.other_option_button:
                if(OPTION_STATUS==0) {
                    otherOption.setVisibility(View.VISIBLE);
                    OPTION_STATUS=1;
                }else if(OPTION_STATUS==1){
                    otherOption.setVisibility(View.GONE);
                    OPTION_STATUS=0;
                }
            case R.id.chat_album:

            case R.id.chat_location:

        }
    }
}
