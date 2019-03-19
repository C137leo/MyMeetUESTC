package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.prenster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.model.AppointmentMeModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentMe.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.AppointmentInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
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
    List<AppointmentInfo> appointmentAllList=new ArrayList<>();
    List<AppointmentInfo> appointmentAcceptData=new ArrayList<>();
    List<AppointmentInfo> appointmentPublishData=new ArrayList<>();
    Observable<ResponseBody> loadAllAppointmentInfo;
    Disposable disposable;
    AppointmentMeModel appointmentMeModel=new AppointmentMeModel();
    JsonParser jsonParser;
    Gson gson;
    JsonArray appointmentInfoJsonArray;
    EventBus eventBus;
    Date date=new Date();
    int type; //0:Accept,1:Publish
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public AppointmentPrensterMe(Context context,int type) {
        this.context = context;
        this.type=type;
    }

    @Override
    public void attchView(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public List<AppointmentInfo> getAppointmentAcceptData() {
        List<AppointmentInfo> appointmentInfos=daoSession.getAppointmentInfoDao().loadAll();
        Log.d("appointmentInfosSize",String.valueOf(appointmentInfos.size()));
        for(AppointmentInfo appointmentInfo:appointmentInfos){
            Log.d("AppointmentSize", String.valueOf(appointmentInfo.getAppointmentStuInfoList().size()));
            for (StuInfo stuInfo:appointmentInfo.getAppointmentStuInfoList()){
                Log.d("stuInfo",daoSession.getStuInfoDao().loadAll().get(0).getStuID());
                if(TextUtils.equals(stuInfo.getStuID(),daoSession.getStuInfoDao().loadAll().get(0).getStuID()));
                {
                    appointmentPublishData.add(appointmentInfo);
                    Log.d("appointmentPublishAdd","appointmentPublishAdd");
                }
            }
        }
        iVew.setAppointmentMeAccept(appointmentAcceptData);
        return appointmentAcceptData;
    }

    @Override
    public List<AppointmentInfo> getAppointmentPublishData() {
        appointmentPublishData=daoSession.queryBuilder(AppointmentInfo.class).where(AppointmentInfoDao.Properties.WhoPublish.eq(daoSession.getStuInfoDao().loadAll().get(0).getNickName())).list();
        Log.d("appointment",String.valueOf(appointmentAcceptData.size()));
        iVew.setAppointmentMePublish(appointmentPublishData);
        return appointmentPublishData;
    }


    @Override
    public void appointmentLoadAllAppointmentInfo() {
        if(loadAllAppointmentInfo==null){
            loadAllAppointmentInfo= RetrofitHelper.getInstance().getRetrofitService().getAllAppointmentData();
        }
        loadAllAppointmentInfo.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        try {
                            for (JsonElement jsonElement : appointmentInfoJsonArray) {
                                AppointmentInfo appointmentInfo = gson.fromJson(jsonElement, AppointmentInfo.class);
                                appointmentAllList.add(appointmentInfo);
                            }
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        AppointmentInfo tmep = new AppointmentInfo("testUID",timeFormat.format(date), "测试约吧" , daoSession.getStuInfoDao().loadAll().get(0).getNickName(), daoSession.getStuInfoDao().loadAll().get(0).getStuID(),
                                daoSession.getStuInfoDao().loadAll().get(0).getStuGrade(), daoSession.getStuInfoDao().loadAll().get(0).getMajor(),
                                "电子科大图书馆", dateFormat.format(date), timeFormat.format(date), 0, 0, 2, 1, "约饭", 0, daoSession.getStuInfoDao().loadAll());
                        appointmentAllList.add(tmep);
                        appointmentMeModel.addAppointmentInfo(appointmentAllList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppointmentInfo tmep=new AppointmentInfo("testUID",timeFormat.format(date),"测试约吧",daoSession.getStuInfoDao().loadAll().get(0).getNickName(),daoSession.getStuInfoDao().loadAll().get(0).getStuID(),
                                daoSession.getStuInfoDao().loadAll().get(0).getStuGrade(),daoSession.getStuInfoDao().loadAll().get(0).getMajor(),
                                "电子科大图书馆",dateFormat.format(date),timeFormat.format(date),0,0,2,1,"约饭",0,daoSession.getStuInfoDao().loadAll());
                        appointmentAllList.add(tmep);
                        appointmentMeModel.addAppointmentInfo(appointmentAllList);
                        e.printStackTrace();
                        iVew.updateError("网络错误");
                        if(type==0) {
                            Log.d("getAppointmentAccept","getAppointmentAccept");
                            getAppointmentAcceptData();
                        }else if(type==1) {
                            Log.d("getAppointmentPublish","getAppointmentPublish");
                            getAppointmentPublishData();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(type==0) {
                            Log.d("getAppointmentAccept","getAppointmentAccept");
                            getAppointmentAcceptData();
                        }else if(type==1) {
                            Log.d("getAppointmentPublish","getAppointmentPublish");
                            getAppointmentPublishData();
                        }
                        iVew.hideRefreshing();
                    }
                });
    }
}
