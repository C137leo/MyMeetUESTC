package cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter;

import android.content.Context;
import android.provider.ContactsContract;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.service.RetrofitService;
import retrofit2.Call;

public class DataManager implements RetrofitService{

    private RetrofitService retrofitService=null;
    private Context mContext;

    public DataManager(Context context){
        this.retrofitService=RetrofitHelper.getInstance(MyApplication.getMyContext()).getRetrofitService();
    }

    @Override
    public Call getSearchStudent(String stuId, String password) {
        return retrofitService.getSearchStudent(stuId,password);
    }
}
