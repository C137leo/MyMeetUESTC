package cn.edu.uestc.meet_on_the_road_of_uestc.login.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.PostLogin;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * MVP模式封装Retrofit+RxJava网络框架
 */
public interface RetrofitService {
    @POST("Stu/search")
    Observable<Stu> getSearchStudent(@Body PostLogin postLogin);
}
