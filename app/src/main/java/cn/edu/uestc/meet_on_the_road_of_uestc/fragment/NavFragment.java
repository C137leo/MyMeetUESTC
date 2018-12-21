package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchFunctionType;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.nearby.UploadInfoCallback;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.adapter.InputTipsAdapter;
import dev.DevUtils;
import dev.utils.app.ADBUtils;
import dev.utils.app.PhoneUtils;
import dev.utils.app.logger.DevLogger;
import dev.utils.app.logger.LogConfig;
import dev.utils.app.logger.LogLevel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class NavFragment extends Fragment implements PoiSearch.OnPoiSearchListener,
        Inputtips.InputtipsListener,GeocodeSearch.OnGeocodeSearchListener,
        RouteSearch.OnRouteSearchListener,NearbySearch.NearbyListener {
    private MapView mMapView;
    public AMapLocationClient mLocationClient;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private LatLng mCurLocation;
    private String address;
    AMap aMap;
    OkHttpClient mOkHttpClient;
    private double Latitude;
    private double Longitude;
    Gson mGson=new Gson();
    String location_json;
    MediaType json=MediaType.parse("application/json;charset=utf-8");
    EditText searchPoi;
    ArrayList poiArray=null;
    String poiKey;
    PoiSearch poiSearch;
    PoiSearch.Query query;
    NearbySearch.NearbyQuery nearbyQuery;
    String keywords;
    String ctgr;
    String city;
    ArrayList<PoiItem> array;
    PoiSearch.Query searchquery;
    private ListView mInputListView;
    private String address_title;
    private String address_detail;
    InputTipsAdapter mAdapter;
    RouteSearch routeSearch;
    LatLonPoint mLatLonPoint;
    List<NearbyInfo> nearbyInfoList;
    ImageView nearByview;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMap();
        ImageView help = getActivity().findViewById(R.id.emergency_help);
        uploadNearbyInfo();
        nearByview=getActivity().findViewById(R.id.nearBy);
        nearByview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNearBy();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission
                .CALL_PHONE)!=PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }
            }
        });
        ImageView imageView=getActivity().findViewById(R.id.find);
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
        mInputListView=getActivity().findViewById(R.id.inputtip_list);
        doSearch();
        mInputListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mInputListView.setVisibility(View.GONE);
                Log.d("position",String.valueOf(position));
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(mAdapter.getTip_Latlng(position)));
                final Marker marker = aMap.addMarker(new MarkerOptions()
                                                        .position(mAdapter.getTip_Latlng(position))
                                                        .title(mAdapter.getTip_title(position))
                                                        .snippet(mAdapter.getTip_address(position)));
            }
        });
        setRandomRoute();
    }

    private void setUpMap(){
        aMap = mMapView.getMap();
        HeatMapCreate();
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        UiSettings mUiSettings=aMap.getUiSettings();//获取UI设置类
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));//设置默认缩放级别
        mLocationClient=new AMapLocationClient(MyApplication.getMyContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    Latitude=aMapLocation.getLatitude();
                    Longitude=aMapLocation.getLongitude();
                    mLatLonPoint=new LatLonPoint(Latitude,Longitude);
                    mCurLocation = new LatLng(Latitude, Longitude,false);
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
        });
        mLocationClient.startLocation();
    }

    public void call(){
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:61830110"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    Toast.makeText(getActivity(),"You have denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
    }

    public void HeatMapCreate(){
        LatLng[] latlngs = new LatLng[500];
        double x = 30.749125;
        double y = 103.931633;

        for (int i = 0; i < 500; i++) {
            double x_ = 0;
            double y_ = 0;
            x_ = Math.random() * 0.5 - 0.25;
            y_ = Math.random() * 0.5 - 0.25;
            latlngs[i] = new LatLng(x + x_, y + y_,false);
        }
        // 构建热力图 HeatmapTileProvider
        HeatmapTileProvider.Builder builder = new HeatmapTileProvider.Builder();
        builder.data(Arrays.asList(latlngs)) // 设置热力图绘制的数据
                .gradient(HeatmapTileProvider.DEFAULT_GRADIENT); // 设置热力图渐变，有默认值 DEFAULT_GRADIENT，可不设置该接口
        // Gradient 的设置可见参考手册
        // 构造热力图对象
        HeatmapTileProvider heatmapTileProvider = builder.build();
        // 初始化 TileOverlayOptions
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.tileProvider(heatmapTileProvider); // 设置瓦片图层的提供者
        // 向地图上添加 TileOverlayOptions 类对象
        aMap.addTileOverlay(tileOverlayOptions);
    }

    public void sendLocationtoServer(){
        mOkHttpClient=new OkHttpClient();
        mCurLocation location=new mCurLocation();
        location.setLatitude(Latitude);
        location.setLongitude(Longitude);
        location_json=mGson.toJson(location);
        RequestBody mLocation=FormBody.create(json,location_json);
        Request curLocation=new Request.Builder().url("http://47.107.162.132:80")
                                            .post(mLocation)
                                            .build();
        mOkHttpClient.newCall(curLocation).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    protected void doSearch(){
        searchPoi = getActivity().findViewById(R.id.inputPoi);
        searchPoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null) {
                    mInputListView.setVisibility(View.VISIBLE);
                    InputtipsQuery inputquery = new InputtipsQuery(String.valueOf(s), "成都市");
                    inputquery.setCityLimit(true);//限制在当前城市
                    Inputtips inputTips = new Inputtips(MyApplication.getMyContext(), inputquery);
                    inputTips.setInputtipsListener(NavFragment.this);
                    inputTips.requestInputtipsAsyn();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchPoi.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    keywords = searchPoi.getText().toString();
                    searchquery = new PoiSearch.Query(keywords, "", "郫都区");
                    searchquery.setPageSize(10);
                    poiSearch = new PoiSearch(MyApplication.getMyContext(), searchquery);
                    poiSearch.setOnPoiSearchListener(NavFragment.this);
                    poiSearch.searchPOIAsyn();
                    mInputListView.setVisibility(View.GONE);
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(15));//设置默认缩放级别
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        array=poiResult.getPois();
        Iterator it = array.iterator();
        while (it.hasNext()) {
            PoiItem poiItem=(PoiItem)it.next();
            LatLng latLng=new LatLng(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
            LatLonPoint latLonPoint=new LatLonPoint(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
            GeocodeSearch geocoderSearch = new GeocodeSearch(MyApplication.getMyContext());
            geocoderSearch.setOnGeocodeSearchListener(NavFragment.this);
            RegeocodeQuery query = new RegeocodeQuery(latLonPoint,50,GeocodeSearch.AMAP);
            geocoderSearch.getFromLocationAsyn(query);
            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(poiItem.getTitle()).snippet(poiItem.getSnippet()));
        }
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(array.get(0).getLatLonPoint().getLatitude(),array.get(0).getLatLonPoint().getLongitude())));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        // 正确返回
        if (i == 1000) {
            new Activity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(list.size()!=0) {
                        List<String> listString = new ArrayList<String>();
                        for (int i = 0; i < list.size(); i++) {
                            listString.add(list.get(i).getName());
                        }
                        mAdapter = new InputTipsAdapter(MyApplication.getMyContext(), list);
                        mInputListView.setAdapter(mAdapter);
                    }
                }
            });
        } else {
            Toast.makeText(MyApplication.getMyContext(), "错误码 :" + i,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        address_title=regeocodeResult.getRegeocodeAddress().getBuilding();
        address_detail=regeocodeResult.getRegeocodeAddress().getFormatAddress();
        Log.d("address_title",address_title);
        Log.d("address_detail",address_detail);
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    public void setRandomRoute(){
        routeSearch = new RouteSearch(MyApplication.getMyContext());
        routeSearch.setRouteSearchListener(this);
        RouteSearch.FromAndTo fromAndTo=new RouteSearch.FromAndTo(new LatLonPoint(30.750036,103.928276),new LatLonPoint(30.750341,103.937428));
        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo);
        routeSearch.calculateWalkRouteAsyn(query);//开始算
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        Log.d("walkroute",String.valueOf(i));
        aMap.clear();
        WalkRouteResult mWalkRouteResult=walkRouteResult;
        List<WalkPath> walkPaths=mWalkRouteResult.getPaths();
        List<WalkStep> walkSteps=walkPaths.get(0).getSteps();
        List<LatLng> latLngs=new ArrayList<LatLng>();
        for(int temp1=0;temp1<walkSteps.size();temp1++) {
            List<LatLonPoint> latLonPoints=walkSteps.get(temp1).getPolyline();
            for (int temp = 0; temp < latLonPoints.size(); temp++) {
                latLngs.add(new LatLng(latLonPoints.get(temp).getLatitude(), latLonPoints.get(temp).getLongitude()));
            }
        }
        final Polyline polyline =aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
    }

    public void uploadNearbyInfo(){
        DevUtils.init(MyApplication.getMyContext());
        // == 初始化日志配置 ==
        // 设置默认Logger配置
        LogConfig logConfig = new LogConfig();
        logConfig.logLevel = LogLevel.DEBUG;
        logConfig.tag = "LOG_TAG";
        DevLogger.init(logConfig);
        // 打开 lib 内部日志
        DevUtils.openLog();
        DevUtils.openDebug();
        NearbySearch mNearbySearch = NearbySearch.getInstance(MyApplication.getMyContext());
        mNearbySearch.startUploadNearbyInfoAuto(new UploadInfoCallback() {
            @Override
            public UploadInfo OnUploadInfoCallback() {
                UploadInfo loadInfo = new UploadInfo();
                loadInfo.setCoordType(NearbySearch.AMAP);
                //位置信息
                loadInfo.setPoint(mLatLonPoint);
                //用户id信息
                loadInfo.setUserID(String.valueOf(PhoneUtils.getIMEI()));
                Log.d("UserId",String.valueOf(PhoneUtils.getIMEI()));
                Log.d("Upload Info","Upload Info");
                return loadInfo;
            }
        },10000);
    }

    public void searchNearBy(){
        NearbySearch mNearbySearch = NearbySearch.getInstance(MyApplication.getMyContext());
        mNearbySearch.addNearbyListener(this);
        //设置搜索条件
        nearbyQuery = new NearbySearch.NearbyQuery();
        //设置搜索的中心点
        nearbyQuery.setCenterPoint(new LatLonPoint(30.749125,103.931633));
        //设置搜索的坐标体系
        nearbyQuery.setCoordType(NearbySearch.AMAP);
        //设置搜索半径
        nearbyQuery.setRadius(10000);
        //设置查询的时间
        nearbyQuery.setTimeRange(10000);
        //设置查询的方式驾车还是距离
        nearbyQuery.setType(NearbySearchFunctionType.DRIVING_DISTANCE_SEARCH);
        //调用异步查询接口
        NearbySearch.getInstance(MyApplication.getMyContext())
                .searchNearbyInfoAsyn(nearbyQuery);
    }
    @Override
    public void onUserInfoCleared(int i) {

    }

    @Override
    public void onNearbyInfoSearched(NearbySearchResult nearbySearchResult, int i) {
        if(i==1000){
            if(nearbySearchResult!=null) {
                nearbyInfoList = nearbySearchResult.getNearbyInfoList();
                for (NearbyInfo nearbyInfo : nearbyInfoList) {
                    LatLng latLng=new LatLng(nearbyInfo.getPoint().getLatitude(),nearbyInfo.getPoint().getLongitude());
                    final Marker marker=aMap.addMarker(new MarkerOptions().position(latLng).title(nearbyInfo.getUserID()));
                }
            }else{
                Toast.makeText(MyApplication.getMyContext(),"未搜索到周边的人",Toast.LENGTH_SHORT).show();
            }
        }else{
            Log.e("NearByService error","错误码为:"+i);
        }
    }

    @Override
    public void onNearbyInfoUploaded(int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

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