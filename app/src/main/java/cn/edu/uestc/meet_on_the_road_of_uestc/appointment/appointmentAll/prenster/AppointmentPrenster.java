package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.prenster;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAll.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AppointmentPrenster implements IPrenster {
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    Disposable disposable;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    Gson gson;
    List<AppointmentInfo> appointmentInfoList;
    IView iVew;

    @Override
    public void attchView(IView iVew) {
        this.iVew=iVew;
    }

    @Override
    public void initAppointmentData() {
        final Observable<ResponseBody> getAllAppointmentData=retrofitHelper.getRetrofitService().getAllAppointmentData();
        getAllAppointmentData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if(appointmentInfoList==null){
                            appointmentInfoList=new ArrayList<>();
                        }
                        appointmentInfoList.clear();
                        JsonParser jsonParser=new JsonParser();
                        try {
                            JsonArray jsonElements=jsonParser.parse(responseBody.string()).getAsJsonArray();
                            for (JsonElement jsonElement:jsonElements){
                                if(gson==null){
                                    gson=new Gson();
                                }
                                AppointmentInfo appointmentInfo=gson.fromJson(jsonElement,AppointmentInfo.class);
                                appointmentInfoList.add(appointmentInfo);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }
                        appointmentInfoList=daoSession.getAppointmentInfoDao().loadAll();
                        iVew.updateAppointmentData(appointmentInfoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        appointmentInfoList=daoSession.getAppointmentInfoDao().loadAll();
                        iVew.updateAppointmentData(appointmentInfoList);
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }
}
