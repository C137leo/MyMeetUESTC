package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.amap.api.maps.MapView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster.HelpAddPrenster;

public class HelpAddActivity extends AppCompatActivity {
    MapView mapView;
    EditText good_title_edittext;
    EditText good_detail_edittext;
    EditText publish_location_edittext;
    HelpAddPrenster helpAddPrenster=new HelpAddPrenster();
    ListView listView;
    private int isPay;
    private String good_title;
    private String good_detail;
    private String publish_location;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_add);
        good_title_edittext=findViewById(R.id.help_add_title);
        good_detail_edittext=findViewById(R.id.help_add_detail);
        publish_location_edittext=findViewById(R.id.help_add_location);
        mapView=findViewById(R.id.help_add_map);
        listView=findViewById(R.id.add_help_location_tips);
        mapView.onCreate(savedInstanceState);
        helpAddPrenster.attchView(iView);

    }

    IView iView=new IView() {
        @Override
        public void addSuccess() {
            finish();
        }

        /**
         * 获取详情框中所填数据
         */
        @Override
        public void getInputData() {
            good_title=good_title_edittext.getText().toString();
            good_detail=good_detail_edittext.getText().toString();
            publish_location=publish_location_edittext.getText().toString();
            helpAddPrenster.getDataFromView(good_title,good_detail,publish_location);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
}
