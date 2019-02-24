package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.prenster;

import android.util.AndroidException;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity.NetworkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.IPresnter;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppointmentAddPrenster implements IPrenster {
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    Disposable disposable;
    @Override
    public void addSingleAppointmentData(AppointmentInfo appointmentInfo) {
        Observable<NetworkStatus> addSingleAppointmentService=retrofitHelper.getRetrofitService().addSingleAppointmentData(appointmentInfo);
        addSingleAppointmentService.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetworkStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(NetworkStatus networkStatus) {

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
