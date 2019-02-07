package cn.edu.uestc.meet_on_the_road_of_uestc.login.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.NetWorkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.PostLogin;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.RegisterStatus;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * MVP模式封装Retrofit+RxJava网络框架
 */
public interface RetrofitService {
    @POST("login_part/check.php")
    Observable<NetWorkStatus> getSearchStudent(@Body PostLogin postLogin);

    @POST("login_part/register.php")
    Observable<RegisterStatus> registerAccount(@Body StuInfo stuInfo);
}
