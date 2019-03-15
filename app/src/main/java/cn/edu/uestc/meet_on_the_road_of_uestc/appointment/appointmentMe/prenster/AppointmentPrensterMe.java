package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;


import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.model.AppointmentMeModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.AppointmentInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AppointmentPrensterMe implements IPrenster{
    Context context;
    IVew iVew;
    DaoSession daoSession=GreenDaoHelper.getDaoSession();
    List<AppointmentInfo> appointmentAllList;
    List<AppointmentInfo> appointmentAcceptData;
    List<AppointmentInfo> appointmentPublishData;
    Observable<ResponseBody> loadAllAppointmentInfo;
    Disposable disposable;
    AppointmentMeModel appointmentMeModel;
    JsonParser jsonParser;
    Gson gson;
    JsonArray appointmentInfoJsonArray;
    EventBus eventBus;
    public AppointmentPrensterMe(Context context) {
        this.context = context;
    }

    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public List<AppointmentInfo> getAppointmentAcceptData() {
        for(AppointmentInfo appointmentInfo:daoSession.getAppointmentInfoDao().loadAll()){
            for (StuInfo stuInfo:appointmentInfo.getAppointmentStuInfoList()){
                if(TextUtils.equals(stuInfo.getStuID(),daoSession.getStuInfoDao().loadAll().get(0).getStuID()));
                {
                    appointmentPublishData.add(appointmentInfo);
                    break;
                }
            }
        }
        return appointmentAcceptData;
    }

    @Override
    public List<AppointmentInfo> getAppointmentPublishData() {
        appointmentPublishData=daoSession.queryBuilder(AppointmentInfo.class).where(AppointmentInfoDao.Properties.WhoPublish.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        return appointmentPublishData;
    }


    @Override
    public void appointmentLoadAllAppointmentInfo() {
        if(loadAllAppointmentInfo==null){
            loadAllAppointmentInfo= RetrofitHelper.getInstance().getRetrofitService().getAllAppointmentData();
        }
        loadAllAppointmentInfo.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            appointmentInfoJsonArray=jsonParser.parse(responseBody.string()).getAsJsonArray();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for(JsonElement jsonElement:appointmentInfoJsonArray){
                            AppointmentInfo appointmentInfo=gson.fromJson(jsonElement,AppointmentInfo.class);
                            appointmentAllList.add(appointmentInfo);
                        }
                        appointmentMeModel.addAppointmentInfo(appointmentAllList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iVew.updateError("网络错误");
                    }

                    @Override
                    public void onComplete() {
                        getAppointmentAcceptData();
                        getAppointmentPublishData();
                        iVew.hideRefreshing();
                    }
                });
    }
}
