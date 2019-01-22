package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.run_activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.track.AMapTrackClient;
import com.autonavi.ae.gmap.GLMapEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import dev.utils.common.DateUtils;
import dev.utils.common.HttpURLConnectionUtils;

public class RunningActivity extends AppCompatActivity {
    TextView distanceText;
    TextView timeText;
    TextView speedText;
    MapView mMapView=null;
    AMap aMap;
    List<LatLng> myRunningRoute=new ArrayList<>();
    LatLng oldLatlng;
    LatLng newLatlng;
    List<LatLng> polylineList=new ArrayList<>();
    Button pauseRunning;
    Button stopRunning;
    Long totalDistance;
    Long totalTime;
    Long startTraceTime;
    Long stopTraceTime;
    Long runTime;
    Long newTime;
    Long oldTime;
    Runnable getShowTime;
    int showTime=0;
    int flag_running=1; //判断是否处于跑步状态，1：正在跑步 2：暂停
    int flag_pause=0; //判断是否按下了暂停按钮，1：按下 2：未按下
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        //获取地图控件引用
        distanceText=findViewById(R.id.distanceDetail);
        timeText=findViewById(R.id.timeDetail);
        speedText=findViewById(R.id.speedDetail);
        mMapView = (MapView) findViewById(R.id.runMap);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        setmRunningMapView();
    }


    public void setmRunningMapView(){
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.animateCamera(CameraUpdateFactory.zoomTo(19));
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认Logger配置认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        HttpURLConnectionUtils.getNetTime(new HttpURLConnectionUtils.TimeCallBack() {
            @Override
            public void onResponse(long time) {
                startTraceTime=time;
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(RunningActivity.this,"开始时间获取失败，跑步不能开始",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if(flag_pause==0) {
                    myRunningRoute.add(new LatLng(location.getLatitude(), location.getLongitude()));
                    newLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                    if (oldLatlng != null) {
                        polylineList.add(newLatlng);
                        polylineList.add(oldLatlng);
                        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                                addAll(polylineList).width(10).color(Color.argb(255, 1, 1, 1)));
                    }
                    oldLatlng = newLatlng;
                    showTime+=1;
                    timeText.setText(DateUtils.secToTimeRetain(showTime));
                }else if(flag_pause==1){
                    oldLatlng=null;
                    newLatlng=null;
                }
            }
        });
        stopRunning=findViewById(R.id.stopRun);
        stopRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpURLConnectionUtils.getNetTime(new HttpURLConnectionUtils.TimeCallBack() {
                    @Override
                    public void onResponse(long time) {
                        stopTraceTime=time;
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(RunningActivity.this,"获取结束时间失败，跑步结束失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                intent.putExtra("runTime",showTime);
                intent.putExtra("startRunTimr",startTraceTime);
                startActivityForResult(intent,1);
                finish();
            }
        });
         pauseRunning=findViewById(R.id.pauseRun);
         pauseRunning.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(flag_pause==0) {
                     pauseRunning.setText("跑步已暂停");
                     flag_pause=1;
                 }else if(flag_pause==1){
                     pauseRunning.setText("暂停跑步");
                     flag_pause=0;
                 }
             }
         });
    }

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
