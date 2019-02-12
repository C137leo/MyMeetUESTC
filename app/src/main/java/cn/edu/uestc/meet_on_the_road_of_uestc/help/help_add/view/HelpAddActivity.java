package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster.HelpAddPrenster;

public class HelpAddActivity extends AppCompatActivity {
    MapView mapView;
    EditText good_title_edittext;
    EditText good_detail_edittext;
    EditText publish_location_edittext;
    HelpAddPrenster helpAddPrenster=new HelpAddPrenster(HelpAddActivity.this);
    ListView listView;
    private int isPay;
    private String good_title;
    private String good_detail;
    private String publish_location;
    AMap aMap;
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
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                helpAddPrenster.getLatLngFromView(location.getLatitude(),location.getLongitude());
                helpAddPrenster.geoCoder();
            }
        });
        setUpMap();
    }
    public void setUpMap(){
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(false); //置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));//设置默认缩放级别
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

        @Override
        public void updateLocationEdittext(String location) {
            publish_location_edittext.setText(location);
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
