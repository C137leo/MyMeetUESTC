package cn.edu.uestc.meet_on_the_road_of_uestc.login.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.NetWorkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.PostLogin;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * MVP模式封装Retrofit+RxJava网络框架
 */
public interface RetrofitService {
    @POST("check.php")
    Observable<NetWorkStatus> getSearchStudent(@Body PostLogin postLogin);

    @POST("register.php")
    Observable<NetWorkStatus> registerAccount(@Body StuInfo stuInfo);
}
