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

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.model.HelpAddModel;
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

public class HelpAddPrenster implements IPrenster, Inputtips.InputtipsListener, GeocodeSearch.OnGeocodeSearchListener , PoiSearch.OnPoiSearchListener {
    private Context mContext;
    HelpInfo helpInfo;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    IView iView;
    private int isPay;
    private String stuID;
    private String good_title;
    private String publish_time;
    private String good_detail;
    private String publish_location;
    double latitude;
    double longitude;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    List<StuInfo> stuInfoList=new ArrayList<StuInfo>();
    private String stuName;

    public HelpAddPrenster(Context context){
        this.mContext=context;
    }
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }

    @Override
    public void initPostInfo(String good_title, String good_detail, String publish_location) {
        iView.showInProgress();
        stuInfoList=daoSession.loadAll(StuInfo.class);
        this.stuID=stuInfoList.get(0).getStuID();
        this.stuName=stuInfoList.get(0).getStuName();
        helpInfo=new HelpInfo(1,stuID,stuName,good_title, DateUtils.getDateNow(),publish_location,good_detail);
        postData();
    }

    @Override
    public void postData() {
        Log.d("Retrofit","beginPost");
        Observable<ResponseBody> observable=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).postGoodData(helpInfo);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iView.addSuccess(); //因为目前没有responseBody，所以临时解决办法
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d("Retrofit","onNext");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("Retrofit","onComplete");
                        iView.addSuccess();
                    }
                });
    }


    /**
     * 通过输入框中地址获取地图位置
     * @param location 输入框中地址
     */
    @Override
    public void getPoiSearch(String location) {
        PoiSearch.Query query = new PoiSearch.Query(location, "", "");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(1);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(mContext, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
        Log.d("poiSearch","开始poi搜索");
    }

    /**
     * 获取初始化activity时的位置
     * @param latitude 初始化的经度
     * @param longitude 初始化的纬度
     */

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

    /**
     * Poi搜索
     * @param poiResult poi搜索结果
     * @param i 状态码，1000即搜搜成功
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        Log.d("POI搜索结果","错误码为"+i);
        iView.setPoiSearchLocation(poiResult.getPois().get(0));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}

