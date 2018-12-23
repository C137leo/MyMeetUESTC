package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.ProcessOption;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.SortType;
import com.baidu.trace.model.TransportMode;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.adapter.InputTipsAdapter;
import dev.DevUtils;
import dev.utils.app.ADBUtils;
import dev.utils.app.PhoneUtils;
import dev.utils.app.logger.DevLogger;
import dev.utils.app.logger.LogConfig;
import dev.utils.app.logger.LogLevel;
import dev.utils.common.HttpURLConnectionUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.baidu.trace.model.SortType.asc;
import static java.lang.Enum.valueOf;

public class NavFragment extends Fragment implements PoiSearch.OnPoiSearchListener,
        Inputtips.InputtipsListener,GeocodeSearch.OnGeocodeSearchListener,
        RouteSearch.OnRouteSearchListener,NearbySearch.NearbyListener,OnTraceListener {
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
    private Timer timer;
    private TimerTask task;
    HashMap<String,Marker> nearByUserMap=new HashMap<String,Marker>();
    LatLonPoint nearbyLatLonPoint;
    int isAmapClear=0;
    LBSTraceClient mTraceClient;
    ImageView run;
    Trace mTrace;
    long serviceId = 207968;
    JSONObject tracetime;
    private long startTime;
    private long stopTime;
    private OkHttpClient okHttpClient;



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
        final int[] flag = {0};
        nearByview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag[0]) {
                    case 0: {
                        timer=new Timer();
                        Handler searchhandler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                searchNearBy();
                                super.handleMessage(msg);
                            }
                        };
                        task = new TimerTask() {
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what = 1;
                                searchhandler.sendMessage(message);
                            }
                        };
                        timer.schedule(task, 2000, 3000);
                        flag[0]=1;
                        break;
                    }
                    case 1: {
                        timer.cancel();
                        clearMarker();
                        flag[0] =0;
                        break;
                    }
                }
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
                aMap.moveCamera(CameraUpdateFactory.zoomTo(19));//设置默认缩放级别
                final Marker marker = aMap.addMarker(new MarkerOptions()
                                                        .position(mAdapter.getTip_Latlng(position))
                                                        .title(mAdapter.getTip_title(position))
                                                        .snippet(mAdapter.getTip_address(position)));
            }
        });
        setRandomRoute();
        run=getActivity().findViewById(R.id.run);
        int[] runflag = {0};
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (runflag[0]){
                    case 0:
                        traceService();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                searchHistoryTrace();
                            }
                        },2000);
                        runflag[0]++;
                        break;
                    case 1:
                        mTraceClient.stopGather(NavFragment.this);
                        runflag[0]--;
                        break;
                }
            }
        });
    }

    /**
     * 初始化高德地图
     */
    private void setUpMap(){
        aMap = mMapView.getMap();
        HeatMapCreate();
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        UiSettings mUiSettings=aMap.getUiSettings();//获取UI设置类
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));//设置默认缩放级别
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if(location.getExtras().getInt(MyLocationStyle.ERROR_CODE)==0){
                    Latitude=location.getLatitude();
                    Longitude=location.getLongitude();
                    mLatLonPoint=new LatLonPoint(Latitude,Longitude);
                    mCurLocation=new LatLng(Latitude,Longitude,false);
                }else{
                    Log.e("AmapError","location Error, ErrCode:"+location.getExtras().getInt(MyLocationStyle.ERROR_CODE));
                }
            }
        });
        //定位监听器第一次获取位置需要一定时间，设置postDelayed来延迟移动相机视角
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(mCurLocation));
            }
        },2000);
//        mLocationClient=new AMapLocationClient(MyApplication.getMyContext());
//        mLocationOption = new AMapLocationClientOption();
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setNeedAddress(true);
//        mLocationOption.setMockEnable(true);
//        mLocationClient.setLocationOption(mLocationOption);
//        mLocationClient.setLocationListener(new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    Latitude=aMapLocation.getLatitude();
//                    Longitude=aMapLocation.getLongitude();
//                    mLatLonPoint=new LatLonPoint(Latitude,Longitude);
//                    mCurLocation = new LatLng(Latitude, Longitude,false);
//                    address = (aMapLocation.getProvince() + ""
//                            + aMapLocation.getCity() + ""
//                            + aMapLocation.getProvince() + ""
//                            + aMapLocation.getDistrict() + ""
//                            + aMapLocation.getStreet() + ""
//                            + aMapLocation.getStreetNum());
//                } else {
//                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError", "location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//        });
//        mLocationClient.startLocation();
    }

    public void call(){
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:61830110"));
        startActivity(intent);
    }

    /**
     * 判断获取权限是否成功
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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

    /**
     * 初始化热点图
     */
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

    /**
     * (已弃用）发送位置到服务器，现使用高德云图替代
     */
//    public void sendLocationtoServer(){
//        mOkHttpClient=new OkHttpClient();
//        mCurLocation location=new mCurLocation();
//        location.setLatitude(Latitude);
//        location.setLongitude(Longitude);
//        location_json=mGson.toJson(location);
//        RequestBody mLocation=FormBody.create(json,location_json);
//        Request curLocation=new Request.Builder().url("http://47.107.162.132:80")
//                                            .post(mLocation)
//                                            .build();
//        mOkHttpClient.newCall(curLocation).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//            }
//        });
//    }

    /**
     * 搜索框活动
     */
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
                }else{
                    aMap.clear(true);
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

    /**
     * poi搜索结果异步回调
     * @param poiResult 搜索结果
     * @param i 状态码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        aMap.clear(true);
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

    /**
     * 获取输入框文字以获取提示
     * @param list
     * @param i
     */
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

    /**
     * 步行路线搜索
     * @param walkRouteResult 结果
     * @param i 状态码
     */
    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        Log.d("walkroute",String.valueOf(i));
        aMap.clear(true);
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

    /**
     * 上传位置信息到服务器以获取附近的人
     */
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

    /**
     * 搜素附近的人
     */
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

    /**
     * 清除地图上的点标记
     */
    public void clearMarker(){
        aMap.clear(true);
        isAmapClear=1;
    }
    @Override
    public void onUserInfoCleared(int i) {

    }

    /**
     * 搜索结果异步回调
     * @param nearbySearchResult
     * @param i
     */
    @Override
    public void onNearbyInfoSearched(NearbySearchResult nearbySearchResult, int i) {
        if(i==1000){
            if(nearbySearchResult!=null) {
                nearbyInfoList = nearbySearchResult.getNearbyInfoList();
                for (NearbyInfo nearbyInfo : nearbyInfoList) {
                    if(nearByUserMap==null){
                        nearByUserMap=new HashMap<String,Marker>();
                        LatLng latLng = new LatLng(nearbyInfo.getPoint().getLatitude(), nearbyInfo.getPoint().getLongitude());
                        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(nearbyInfo.getUserID()).snippet(String.valueOf(nearbyInfo.getDistance())));
                        nearByUserMap.put(nearbyInfo.getUserID(),marker);
                    }else {
                        if (nearByUserMap.containsKey(nearbyInfo.getUserID())) {
                            if(nearbyInfo.getPoint().getLatitude() == nearbyLatLonPoint.getLatitude() && nearbyInfo.getPoint().getLongitude() == nearbyLatLonPoint.getLongitude()) {
                                LatLng mLatLng = new LatLng(nearbyInfo.getPoint().getLatitude(), nearbyInfo.getPoint().getLongitude());
                                Marker marker = nearByUserMap.get(nearbyInfo.getUserID());
                                marker.setPosition(mLatLng);
                            }else if(isAmapClear==1){
                                aMap.addMarker(nearByUserMap.get(nearbyInfo.getUserID()).getOptions());
                            }
                        } else {
                            nearbyLatLonPoint = nearbyInfo.getPoint();
                            LatLng latLng = new LatLng(nearbyInfo.getPoint().getLatitude(), nearbyInfo.getPoint().getLongitude());
                            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(nearbyInfo.getUserID()).snippet(String.valueOf(nearbyInfo.getDistance())));
                            nearByUserMap.put(nearbyInfo.getUserID(), marker);
                        }
                    }
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

    public void traceService(){
        DevUtils.init(MyApplication.getMyContext());
        // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
        boolean isNeedObjectStorage = true;
        //entity标识
        String entityName = PhoneUtils.getIMEI();
        // 初始化轨迹服务
        mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(MyApplication.getMyContext());
        // 定位周期(单位:秒)
        int gatherInterval = 5;
        // 打包回传周期(单位:秒)
        int packInterval = 10;
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);
        // 开启服务
        mTraceClient.startTrace(mTrace, NavFragment.this);

    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
    }

    @Override
    public void onBindServiceCallback(int i, String s) {

    }

    @Override
    public void onStartTraceCallback(int i, String s) {
        if(i==0){
            // 开启采集
            mTraceClient.startGather(NavFragment.this);
        }else{
            Log.e("traceService",i+s);
        }
    }

    @Override
    public void onStopTraceCallback(int i, String s) {
        Log.d("stopTrace",i+s);
    }

    @Override
    public void onStartGatherCallback(int i, String s) {
        HttpURLConnectionUtils.getNetTime(new HttpURLConnectionUtils.TimeCallBack() {
            @Override
            public void onResponse(long time) {
                startTime=time;
                Log.d("TraceStartTime",String.valueOf(startTime));
            }

            @Override
            public void onFail(Exception e) {
                Log.d("TraceTimeFail","获取时间失败");
            }
        });
    }

    @Override
    public void onStopGatherCallback(int i, String s) {
        HttpURLConnectionUtils.getNetTime(new HttpURLConnectionUtils.TimeCallBack() {
            @Override
            public void onResponse(long time) {
                stopTime=time;
                Log.d("StopTraceTime",String.valueOf(stopTime));
            }

            @Override
            public void onFail(Exception e) {

            }
        });
        okHttpClient=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(json,String.valueOf(tracetime));
    }

    @Override
    public void onPushCallback(byte b, PushMessage pushMessage) {

    }

    @Override
    public void onInitBOSCallback(int i, String s) {

    }


    public void searchHistoryTrace(){
        DevUtils.init(MyApplication.getMyContext());
        //entity标识
        String entityName = PhoneUtils.getIMEI();
        //是否返回精简的结果（0 : 将只返回经纬度，1 : 将返回经纬度及其他属性信息）
        int simpleReturn = 1;
        //开始时间（Unix时间戳）
        int startTime = (int) (System.currentTimeMillis() / 1000 - 12 * 60 * 60);
        Log.d("currentTIMR",String.valueOf(startTime));
        //结束时间（Unix时间戳）
        int endTime = (int) (System.currentTimeMillis() / 1000);
        //分页大小
        int pageSize = 1000;
        //分页索引
        int pageIndex = 1;
        //轨迹查询监听器
        OnTrackListener trackListener = new OnTrackListener() {
            @Override
            public void onHistoryTrackCallback(HistoryTrackResponse historyTrackResponse) {
                Log.d("searchHistoryTrack",String.valueOf(historyTrackResponse.getSize())+","+historyTrackResponse.getStatus()+historyTrackResponse.getMessage());
            }
        };
        ProcessOption processOption=new ProcessOption(true,true,false,0,TransportMode.driving);
        //查询历史轨迹
        mTraceClient.queryHistoryTrack(new HistoryTrackRequest(0,serviceId,entityName,startTime,endTime,true,processOption,null,SortType.asc,CoordType.bd09ll,pageIndex,pageSize),trackListener);
    }

    public void postTraceTime(){
        DevUtils.init(MyApplication.getMyContext());
        tracetime=new JSONObject();
        try {
            tracetime.put("DeviceIMEI", PhoneUtils.getIMEI());
            tracetime.put("starttime",startTime);
            tracetime.put("stopTraceTime",stopTime);
        }catch (Exception e){

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