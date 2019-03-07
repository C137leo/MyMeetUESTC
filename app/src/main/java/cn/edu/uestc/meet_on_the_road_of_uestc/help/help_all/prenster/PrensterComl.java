package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpInfoWithDistance;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model.HelpModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IVewNearBy;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IViewLatest;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PrensterComl implements IPrenster, AMapLocationListener {
    Observable<ResponseBody> observable;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    HelpModel helpModel=new HelpModel();
    private Context context;
    IViewLatest iViewLatest;
    JsonParser jsonParser=new JsonParser();
    JsonArray jsonArray;
    Gson gson=new Gson();
    Disposable disposable;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    LatLng myLatlng;
    List<HelpInfoWithDistance> helpInfoWithDistancesList;
    List<HelpInfo> helpInfoList;
    IVewNearBy iVewNearBy;
    List<HelpInfo> helpInfos;
    public PrensterComl(Context context){
        this.context=context;
    }

    @Override
    public void attchViewLatest(IViewLatest iViewLatest) {
        this.iViewLatest = iViewLatest;
    }

    public void attchViewNearBy(IVewNearBy iVewNearBy){
        this.iVewNearBy=iVewNearBy;
    }
    @Override
    public void getLocation() {
        AMapLocationClient aMapLocationClient=new AMapLocationClient(MyApplication.getMyContext());
        aMapLocationClient.setLocationListener(this);
        AMapLocationClientOption aMapLocationClientOption=new AMapLocationClientOption();
        aMapLocationClientOption.setOnceLocationLatest(true);
        aMapLocationClientOption.setInterval(60000);
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        myLatlng=new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
        calculateDistance(myLatlng);
    }

    public void getData() {
        observable=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).getGoodsData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        List<HelpInfo> helpInfoList = new ArrayList<>();
                        Log.d("getResponse","getResponse");
                        try {
                            jsonArray=jsonParser.parse(responseBody.string()).getAsJsonArray();
                        } catch (IOException e) {
                            e.printStackTrace();
                            if(iVewNearBy==null) {
                                iViewLatest.hideRefershing();
                            }else {
                                iVewNearBy.hideRefershing();
                            }
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                            if(iVewNearBy==null) {
                                iViewLatest.hideRefershing();
                            }else {
                                iVewNearBy.hideRefershing();
                            }
                        }
                        if(jsonArray==null){
                            if(iVewNearBy==null) {
                                iViewLatest.hideRefershing();
                            }else {
                                iVewNearBy.hideRefershing();
                            }
                        }else {
                            for (JsonElement jsonElement : jsonArray) {
                                HelpInfo helpInfo = gson.fromJson(jsonElement, HelpInfo.class);
                                helpInfoList.add(helpInfo);
                                helpModel.saveGoodsData(helpInfo);
                            }
                        }
                        getDataFromDatabases();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        if(iVewNearBy==null) {
                            iViewLatest.hideRefershing();
                        }
                        disposable.dispose();
                    }
                });
    }

    @Override
    public void getDataFromDatabases() {
        helpInfos=new ArrayList<>();
        helpInfos= daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.IsFinish.eq(0)).list();
        Log.d("size", String.valueOf(helpInfos.size()));
        for(HelpInfo helpInfo:daoSession.getHelpInfoDao().loadAll()){
            Log.d("isFinish",String.valueOf(helpInfo.getIsFinish()));
        }
        Log.d("helpInfosSize", String.valueOf(helpInfos.size()));
        if(iVewNearBy==null) {
            iViewLatest.updateData(helpInfos);
        }else {
            getLocation();
        }
    }

    @Override
    public void calculateDistance(LatLng latLng1) {
        helpInfoWithDistancesList=new ArrayList<>();
        for(HelpInfo helpInfo:helpInfos){
            float distance= AMapUtils.calculateLineDistance(latLng1,new LatLng(helpInfo.getLatitude(),helpInfo.getLongitude()));
            Log.d("distance",String.valueOf(distance));
            HelpInfoWithDistance helpInfoWithDistance=new HelpInfoWithDistance(helpInfo.getUID(),helpInfo.getStuID(),helpInfo.getLocation(),helpInfo.getOwner_name(),helpInfo.getGood_title(),helpInfo.getPublish_time(),helpInfo.getIsPay(),helpInfo.getGood_detail(),helpInfo.getIsFinish(),helpInfo.getWhoFinishIt(),helpInfo.getAcceptTime(),helpInfo.getWhoFinishItStuID(),helpInfo.getWhoFinishItStuMajor(),helpInfo.getWhoFinishItStuGrade(),distance);
            helpInfoWithDistancesList.add(helpInfoWithDistance);
        }
        Collections.sort(helpInfoWithDistancesList);
        iVewNearBy.updateData(helpInfoWithDistancesList);
        iVewNearBy.hideRefershing();
    }
}
