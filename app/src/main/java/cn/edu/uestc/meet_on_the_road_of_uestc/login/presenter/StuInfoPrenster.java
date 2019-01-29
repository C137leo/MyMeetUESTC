package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.view.LoginActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuInfoPrenster implements Prenster{
    DataManager dataManager=null;
    Context mContext;
    Stu stu=null;
    IView view;
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
    public void getStuInfo(String StuId,String password) {
        onCreate();
        Observable<Stu> observable=dataManager.getSearchStudent(StuId,password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Stu>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        d.dispose();
                    }

                    @Override
                    public void onNext(Stu stu) {
                        view.loginSuccess(stu);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
