package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster.HelpDetailPrenster;

public class HelpDetailActivity extends AppCompatActivity {
    HelpDetailPrenster helpDetailPrenster=new HelpDetailPrenster();
    Long dataUID;
    TextView helpDetailTitle;
    TextView helpDetailAuthor;
    TextView helpPublishTime;
    TextView helpDetail;
    LatLng helpLatLng;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        helpDetailTitle=findViewById(R.id.help_detail_title);
        helpDetailAuthor=findViewById(R.id.help_detail_author);
        helpPublishTime=findViewById(R.id.help_detail_publish_time);
        helpDetail=findViewById(R.id.help_detail);
        helpDetailPrenster.attchView(iView);
        dataUID=getIntent().getLongExtra("UID",0L);
        helpDetailPrenster.searchDetailData(dataUID);
        helpDetailPrenster.searchLocation();
        helpDetailPrenster.attchView(iView);
        helpDetailPrenster.searchDetailData(dataUID);
    }

    IView iView=new IView() {
        @Override
        public void showData(HelpInfo helpInfo) {
            helpDetailTitle.setText(helpInfo.getGood_title());
            helpDetailAuthor.setText(helpInfo.getOwner_name());
            helpPublishTime.setText(helpInfo.getPublish_time());
            helpDetail.setText(helpInfo.getGood_detail());
        }

        @Override
        public void searchData() {

        }

        @Override
        public void setAmapLocation(LatLng latLng) {
            helpLatLng=latLng;
        }
    };
}
