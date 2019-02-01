package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import dev.DevUtils;
import dev.utils.common.DateUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class HelpAddPrenster implements IPrenster, Inputtips.InputtipsListener, GeocodeSearch.OnGeocodeSearchListener {
    private Context mContext;
    HelpInfo helpInfo;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    IView iView;
    private int isPay;
    private String stuID;
    private String owner_name;
    private String good_title;
    private String publish_time;
    private double latitude;
    private double longitude;
    private String good_detail;
    private String publish_location;
    public HelpAddPrenster(Context context){
        this.mContext=context;
    }
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }

    @Override
    public void getDataFromView(String good_title, String good_detail, String publish_location) {
        this.good_title=good_title;
        this.good_detail=good_detail;
        this.publish_location=publish_location;
    }

    @Override
    public void getTime() {
        publish_time = DateUtils.getDateNow();
    }

    @Override
    public void getStuInfoSuccess(String stuID, String stuName) {
        this.stuID=stuID;
        this.owner_name=stuName;
    }

    @Override
    public void initPostData() {
        HelpInfo helpInfo=new HelpInfo();
    }

    @Override
    public void postData() {
        Observable<ResponseBody> observable=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).postGoodData(helpInfo);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iView.addSuccess();
                    }
                });
    }

    @Override
    public void getTips() {

    }

    @Override
    public void getLatLngFromView(double latitude, double longitude) {
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void geoCoder(){
        GeocodeSearch geocoderSearch = new GeocodeSearch(mContext);
        geocoderSearch.setOnGeocodeSearchListener(this);
        LatLonPoint latLonPoint=new LatLonPoint(latitude,longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    public void getTipsInListView(String inputLocation){
        InputtipsQuery inputquery = new InputtipsQuery(inputLocation, "");
        inputquery.setCityLimit(true);//限制在当前城市
        Inputtips inputTips = new Inputtips(mContext, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }
    /**
     * 逆地理编码，坐标转地址，来自高德
     * @param regeocodeResult 逆地理编码结果
     * @param i 状态码，1000即成功
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        publish_location=regeocodeResult.getRegeocodeAddress().getFormatAddress();
        iView.updateLocationEdittext(publish_location);
        Log.d("RegeocodeSearched",String.valueOf(i));
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    /**
     * 获取提示列表
     * @param list 提示列表
     * @param i 状态码，1000即成功
     */
    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        if(i==1000){
            if (list.get(0).getPoiID()!=null){
                Log.d("getTipList","getTipList");
                Log.d("getTipList",list.get(0).getName());
                iView.getTipList(list);
            }
        }else{
            Log.e("getInpuTips","错误码为"+i);
            Toast.makeText(mContext,"网络错误，检查网络后重试",Toast.LENGTH_SHORT).show();
        }
    }
}

