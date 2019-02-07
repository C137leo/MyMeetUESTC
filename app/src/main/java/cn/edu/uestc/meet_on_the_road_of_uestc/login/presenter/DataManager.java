package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.content.Context;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.NetWorkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.PostLogin;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.service.RetrofitService;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class DataManager implements RetrofitService{

    private RetrofitService retrofitService=null;
    private Context mContext;

    public DataManager(Context context){
        this.retrofitService=RetrofitHelper.getInstance(MyApplication.getMyContext()).getRetrofitService();
    }

    @Override
    public Observable<NetWorkStatus> getSearchStudent(PostLogin postLogin) {
        return retrofitService.getSearchStudent(postLogin);
    }

    @Override
    public Observable<NetWorkStatus> registerAccount(StuInfo stuInfo) {
        return retrofitService.registerAccount(stuInfo);
    }
}
