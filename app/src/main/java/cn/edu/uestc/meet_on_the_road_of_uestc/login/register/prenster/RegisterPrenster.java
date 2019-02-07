package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.prenster;

import android.util.Log;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.RegisterStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter.DataManager;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view.IView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPrenster implements IPrenster{
    IView iView;
    Disposable disposable;
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }


    @Override
    public void registerccount(StuInfo stuInfo) {
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
                        if(registerStatus.getScode()==100){
                            iView.registerSuccess();
                        }else{
                            iView.registerError(registerStatus.getSmsg());
                            Log.e("registerError",registerStatus.getSmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }


}
