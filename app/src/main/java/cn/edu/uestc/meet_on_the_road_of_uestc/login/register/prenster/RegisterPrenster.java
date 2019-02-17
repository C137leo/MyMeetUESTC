package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.prenster;

import android.util.Log;

import cn.edu.uestc.meet_on_the_road_of_uestc.JMessageCallback.JMessageRegisterCallback;
import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.RegisterStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter.DataManager;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view.IView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPrenster implements IPrenster{
    IView iView;
    Disposable disposable;
    JMessageRegisterCallback jMessageRegisterCallback;
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }


    @Override
    public void registerccount(final StuInfo stuInfo) {
        DataManager dataManager=new DataManager(MyApplication.getMyContext());
        Observable<RegisterStatus>  observable=dataManager.registerAccount(stuInfo);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(RegisterStatus registerStatus) {
                        if(registerStatus.getErrcode()==105){
                            RegisterOptionalUserInfo registerOptionalUserInfo=new RegisterOptionalUserInfo();
                            registerOptionalUserInfo.setNickname(stuInfo.getNickName());
                            JMessageClient.register(stuInfo.getStuID(),stuInfo.getStuPassWord(),registerOptionalUserInfo, jMessageRegisterCallback);
                            iView.registerSuccess();
                        }else{
                            iView.registerError(registerStatus.getErrmsg());
                            Log.e("registerError",registerStatus.getErrmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iView.netWorkError();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }


}
