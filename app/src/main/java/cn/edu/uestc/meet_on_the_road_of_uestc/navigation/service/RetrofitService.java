package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("stuInfo")
    Observable<StuInfo> getStuInfo(@Field("StuID") String StuID);
}
