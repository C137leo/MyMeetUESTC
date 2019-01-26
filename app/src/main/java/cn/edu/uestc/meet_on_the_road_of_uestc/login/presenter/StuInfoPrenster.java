package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuInfoPrenster implements Prenster{
    DataManager dataManager=null;
    Context mContext;
    Stu stu=null;
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
    public void attachView(View view) {

    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
    public void getStuInfo(String StuId,String password){
        dataManager.getSearchStudent(StuId,password).enqueue(new Callback<Stu>(){
            @Override
            public void onResponse(Call<Stu> call, Response<Stu> response) {
                stu=response.body();
            }

            @Override
            public void onFailure(Call<Stu> call, Throwable t) {

            }
        });
    }

    public Stu getDataInDatabase(){
        return stu;
    }
}
