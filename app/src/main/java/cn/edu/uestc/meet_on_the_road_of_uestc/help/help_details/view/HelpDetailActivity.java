package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster.HelpDetailPrenster;

public class HelpDetailActivity extends AppCompatActivity {
    HelpDetailPrenster helpDetailPrenster=new HelpDetailPrenster();
    String dataUID;
    TextView helpDetailTitle;
    TextView helpDetailAuthor;
    TextView helpPublishTime;
    TextView helpDetail;
    PoiItem helpPoiItem;
    MapView mMapView;
    ImageView helpDetailBack;
    AMap aMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        //获取地图控件引用
        mMapView = findViewById(R.id.help_detail_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        helpDetailTitle=findViewById(R.id.help_detail_title);
        helpDetailAuthor=findViewById(R.id.help_detail_author);
        helpPublishTime=findViewById(R.id.help_detail_publish_time);
        helpDetail=findViewById(R.id.help_detail);
        helpDetailBack=findViewById(R.id.help_detail_back);
        helpDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(aMap==null){
            aMap=mMapView.getMap();
        }
        setUpMap();
        helpDetailPrenster.attchView(iView);
        dataUID=getIntent().getStringExtra("UID");
        Log.d("getIntent",String.valueOf(dataUID));
        helpDetailPrenster.searchDetailData(dataUID);
        helpDetailPrenster.searchLocation();
        helpDetailPrenster.attchView(iView);
    }

    public void setUpMap(){
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(false); //置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));//设置默认缩放级别
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
        public void setAmapLocation(PoiItem poiItem) {
            helpPoiItem=poiItem;
            final Marker marker = aMap.addMarker(new MarkerOptions()
                    .position(new LatLng(helpPoiItem.getLatLonPoint().getLatitude(), helpPoiItem.getLatLonPoint().getLongitude()))
                    .title(helpPoiItem.getTitle()).snippet(helpPoiItem.getSnippet()));
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(helpPoiItem.getLatLonPoint().getLatitude(), helpPoiItem.getLatLonPoint().getLongitude())));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
