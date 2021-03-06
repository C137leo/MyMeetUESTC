package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.edu.uestc.meet_on_the_road_of_uestc.chat.JMessageCallback.JMessageLoginCallback;
import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.NetWorkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.PostLogin;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.model.LoginModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.view.IView;
import cn.jpush.im.android.api.JMessageClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StuInfoPrenster implements Prenster{
    DataManager dataManager=null;
    Context mContext;
    IView view;
    Disposable disposable;
    LoginModel loginModel=new LoginModel();
    @Override
    public void onCreate() {
        dataManager=new DataManager(MyApplication.getMyContext());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(IView view) {
        this.view=view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
    public void getStuInfo(final String StuId, final String password) {
        PostLogin postLogin=new PostLogin(StuId,password);
        onCreate();
        Observable<NetWorkStatus> observable=dataManager.getSearchStudent(postLogin);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetWorkStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(NetWorkStatus netWorkStatus) {
                        if(netWorkStatus.getCode().getErrcode()==100){
                            JMessageClient.login(StuId,password,new JMessageLoginCallback());
                            loginModel.writeDtabases(netWorkStatus);
                            view.loginSuccess();
                        }else{
                            Log.e("loginError", netWorkStatus.getCode().getErrmsg());
                            view.loginError(netWorkStatus.getCode().getErrmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.netWorkError();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    @Override
    public void getLoginStatus() {

    }
}
