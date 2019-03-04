package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.prenster;

import android.content.Context;
import android.util.AndroidException;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity.NetworkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.IPresnter;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import dev.utils.common.AssistUtils;
import dev.utils.common.DateUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppointmentAddPrenster implements IPrenster {
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    Disposable disposable;
    Context context;
    AppointmentInfo appointmentInfo;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    IVew iVew;
    public AppointmentAddPrenster(Context context) {
        this.context = context;
    }

    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public void initPublishData(int type, int number, String dateTime, String location, String introduction) {
        String appointmentUID= AssistUtils.getRandomUUID();
        StuInfo stuInfo=daoSession.getStuInfoDao().loadAll().get(0);
        appointmentInfo=new AppointmentInfo(appointmentUID,introduction, DateUtils.getDateNow(),stuInfo.getStuName(),stuInfo.getStuID(),
                stuInfo.getStuGrade(),stuInfo.getMajor(),location,dateTime,0,0,number,type,"",0,null);
        addSingleAppointmentData(appointmentInfo);
    }

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
