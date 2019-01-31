package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster;

import android.view.View;

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

public class HelpAddPrenster implements IPrenster {
    HelpInfo helpInfo;
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance(MyApplication.getMyContext());
    IView iView;
    private int isPay;
    private String stuID;
    private String owner_name;
    private String good_title;
    private String publish_time;
    private double latitude;
    private double lontitude;
    private String good_detail;
    private String publish_location;
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
}

