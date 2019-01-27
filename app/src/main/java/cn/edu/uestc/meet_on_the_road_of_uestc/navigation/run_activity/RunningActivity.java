package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.run_activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.traceInfo;
import dev.utils.common.DateUtils;
import dev.utils.common.HttpURLConnectionUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RunningActivity extends AppCompatActivity {
    TextView distanceText;
    TextView timeText;
    TextView speedText;
    MapView mMapView=null;
    AMap aMap;
    List<LatLng> myRunningRoute=new ArrayList<>(); //跑步路径储存List
    LatLng oldLatlng; //跑步前一秒的纬度
    LatLng newLatlng; //跑步后一秒纬度
    List<LatLng> polylineList=new ArrayList<>();  //跑步路径绘制储存list
    Button pauseRunning;
    Button stopRunning;
    ProgressBar mProgressBar;
    Long totalDistance;
    Long totalTime;
    Long startTraceTime;
    Long stopTraceTime;
    Long runTime;
    Long newTime;
    Long oldTime;
    Runnable getShowTime;
    Disposable disposable;
    int showTime=0;
    int flag_running=1; //判断是否处于跑步状态，1：正在跑步 2：暂停
    int flag_pause=0; //判断是否按下了暂停按钮，1：按下 2：未按下
    String runModel;
    String setTime;
    boolean isModel;
    boolean isPause=false;
    DaoSession mDaoSession;
    String date;
    String latitude=null;
    String lontitude=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        mDaoSession= GreenDaoHelper.getDaoSession(); //初始化DaoSession对数据库进行管理
        //获取地图控件引用
        distanceText=findViewById(R.id.distanceDetail);
        timeText=findViewById(R.id.timeDetail);
        speedText=findViewById(R.id.speedDetail);
        mMapView = (MapView) findViewById(R.id.runMap);
        mProgressBar=findViewById(R.id.runProgress);
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
        if(getIntent().getStringExtra("RunModel")!=null) {
            if (getIntent().getStringExtra("RunModel").equals("倒计时模式")) {
                Log.d("It is 倒计时模式", "倒计时模式");
                flag_pause = -1;
            }
        }
        HttpURLConnectionUtils.getNetTime(new HttpURLConnectionUtils.TimeCallBack() {
            @Override
            public void onResponse(long time) {
                startTraceTime=time;
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(RunningActivity.this,"开始时间获取失败，跑步开始失败",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        date=DateUtils.getDateNow();
        /**
         * 设置获取轨迹坐标的list集合，以及绘制跑步的路线
         */
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
                }else if(flag_pause==-1){
                    getRunningModel();
                    myRunningRoute.add(new LatLng(location.getLatitude(), location.getLongitude()));
                    newLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                    if (oldLatlng != null) {
                        polylineList.add(newLatlng);
                        polylineList.add(oldLatlng);
                        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                                addAll(polylineList).width(10).color(Color.argb(255, 1, 1, 1)));
                    }
                    oldLatlng = newLatlng;
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
                changeTheLatLngListToString();
                setDataIndb();
                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                intent.putExtra("runTime",showTime);
                intent.putExtra("startRunTime",startTraceTime);
                startActivityForResult(intent,1);
                finish();
            }
        });
         pauseRunning=findViewById(R.id.pauseRun);
         pauseRunning.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(flag_pause==0||flag_pause==-1) {
                     if(isModel){
                         pauseRunning.setText("跑步已暂停");
                         flag_pause=-1;
                     }else {
                         pauseRunning.setText("跑步已暂停");
                         flag_pause = 1;
                     }
                 }else if(flag_pause==1){
                     pauseRunning.setText("暂停跑步");
                     flag_pause=0;
                 }
             }
         });
    }
    public void setDataIndb(){
        traceInfo traceInfo=new traceInfo(date,"2018021407022",latitude,lontitude,20.00,20.00,startTraceTime,stopTraceTime,(stopTraceTime-startTraceTime));

    }

    /**
     * 将list内的数据转换合并为string类型方便数据库储存
     */

    public void changeTheLatLngListToString(){
        for(LatLng latLng:myRunningRoute){
            latitude=latitude+","+latLng.latitude;
            lontitude=lontitude+","+latLng.longitude;
        }
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

    public void getRunningModel(){
        String runModel=getIntent().getStringExtra("RunModel");
        if(runModel.equals("倒计时模式")){
            Log.d("RunModel",getIntent().getStringExtra("time"));
            if(!isModel) {

                calculateTime();
            }
            isModel=true;
        }
    }
    public void calculateTime(){
        switch(getIntent().getStringExtra("time")){
            case "1:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(61)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (60-aLong);
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(62*1000);
                                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                                    }
                                });
                                valueAnimator.start();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",60);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "2:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(120)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (120-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(122*1000);
                                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                                    }
                                });
                            valueAnimator.start();
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",120);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "3:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(180)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (180-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(182*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",180);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "4:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(240)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (240-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(242*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",240);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "5:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(300)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (300-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(302*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",300);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "6:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(360)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (360-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(362*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",360);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "7:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(420)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (420-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(472*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",420);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "8:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(480)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (480-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(482*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",480);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "9:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(540)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (540-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(542*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",540);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
            case "10:00":
                Observable.interval(0,1, TimeUnit.SECONDS)
                        .take(600)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long aLong) throws Exception {
                                return (600-aLong);
                            }
                        }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        final ValueAnimator valueAnimator=ValueAnimator.ofInt(100).setDuration(602*1000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                mProgressBar.setProgress((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        valueAnimator.start();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onNext(Long o) {
                                timeText.setText(DateUtils.secToTimeRetain(Math.toIntExact(o)));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Intent intent=new Intent(RunningActivity.this,FinishRunActivity.class);
                                intent.putExtra("runTime",600);
                                intent.putExtra("startRunTime",startTraceTime);
                                startActivity(intent);
                                disposable.dispose();
                                finish();

                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable=d;
                            }

                        });
                break;
        }
    }
}
