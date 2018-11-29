package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class NavFragment extends Fragment {
    private MapView mMapView;
    public AMapLocationClient mLocationClient;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private LatLng mCurLocation;
    private String address;
    AMap aMap;
    AMapLocation aMapLocation;
//    onClickListener mOnClickListener=new onClickListener();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getLayoutInflater().inflate(R.layout.fragment_nav,container,false);
        //获取地图控件引用
        mMapView = view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMap();
        Button button = getActivity().findViewById(R.id.emergency_help);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:61830110"));
                    startActivity(intent);
            }
        });
        ImageView imageView=getActivity().findViewById(R.id.location);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurLocation == null) {
                    Toast.makeText(getActivity(), "当前信号不佳，请稍候...", Toast.LENGTH_SHORT).show();
                }else {
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(mCurLocation));
                }
            }
        });
    }

    private void setUpMap(){
        aMap = mMapView.getMap();
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        UiSettings mUiSettings=aMap.getUiSettings();//获取UI设置类
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));//设置默认缩放级别
        Log.d("test for location","test for location");
        mLocationClient=new AMapLocationClient(MyApplication.getMyContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationClient.setLocationListener(new mAMapLocationListener());
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    class mAMapLocationListener implements AMapLocationListener{
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

            if (aMapLocation.getErrorCode() == 0) {
                mCurLocation = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                address = (aMapLocation.getProvince() + ""
                        + aMapLocation.getCity() + ""
                        + aMapLocation.getProvince() + ""
                        + aMapLocation.getDistrict() + ""
                        + aMapLocation.getStreet() + ""
                        + aMapLocation.getStreetNum());
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //在activity执行onDestroy时执行mMapView.onDestroyView()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}