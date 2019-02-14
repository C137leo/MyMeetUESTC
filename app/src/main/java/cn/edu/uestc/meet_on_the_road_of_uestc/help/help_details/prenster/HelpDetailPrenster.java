package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster;

import android.util.Log;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpStatusUpdateToIsProcessing;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import dev.utils.common.DateUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class HelpDetailPrenster implements IPrenster, PoiSearch.OnPoiSearchListener {
    private IView iView;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    String location;
    HelpInfo helpInfo;
    RetrofitHelper retrofitHelper=new RetrofitHelper(MyApplication.getMyContext());
    Disposable updateStatusDisposable;
    Gson gson=new Gson();
    PostHelpAddStatus postHelpAddStatus;
    @Override
    public void attchView(IView view) {
        this.iView=view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void updateHelpStatusToIsProcessing(String UID) {
        StuInfo stuInfo =daoSession.getStuInfoDao().loadAll().get(0);
        HelpStatusUpdateToIsProcessing helpStatusUpdateToIsProcessing=new HelpStatusUpdateToIsProcessing(UID,stuInfo.getStuName(), DateUtils.getDateNow(),1,stuInfo.getStuID(),stuInfo.getMajor(),stuInfo.getStuGrade());
        Observable<ResponseBody> observable=retrofitHelper.getRetrofitService(MyApplication.getMyContext()).updateHelpStatus(helpStatusUpdateToIsProcessing);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        updateStatusDisposable=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            postHelpAddStatus=gson.fromJson(responseBody.string(), PostHelpAddStatus.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(postHelpAddStatus.getErrcode()==107){
                            iView.updateStatusToIsProcessingSuccess();
                        }else{
                            iView.updateStatusToIsProcessingError(postHelpAddStatus.getErrmsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iView.updateStatusToIsProcessingError("网路错误，请稍后重试");
                    }

                    @Override
                    public void onComplete() {
                        updateStatusDisposable.dispose();
                    }
                });
    }

    @Override
    public void searchDetailData(String UID) {
        List<HelpInfo> helpInfoList= daoSession.getHelpInfoDao().queryBuilder().where(HelpInfoDao.Properties.UID.eq(UID)).list();
        Log.d("queryGreendao",String.valueOf(helpInfoList.isEmpty()));
        helpInfo=helpInfoList.get(0);
        iView.showData(helpInfo);
    }

    @Override
    public void searchLocation() {
        PoiSearch.Query query = new PoiSearch.Query(helpInfo.getLocation(), "", "");
        query.setPageSize(10);
        query.setPageNum(1);
        PoiSearch poiSearch = new PoiSearch(MyApplication.getMyContext(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    /**
     * 根据关键词搜索地点
     * @param poiResult 搜索结果列表
     * @param i 错误码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        Log.d("ErrorCode",String.valueOf(i));
        if(i==1000){
            Log.d("SearchPoi","SarchSuccessfully");
            PoiItem poiItem=poiResult.getPois().get(0);
            iView.setAmapLocation(poiItem);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
