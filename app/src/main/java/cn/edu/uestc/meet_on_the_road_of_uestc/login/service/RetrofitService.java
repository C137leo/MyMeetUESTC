package cn.edu.uestc.meet_on_the_road_of_uestc.login.service;

import java.util.Observable;

import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * MVP模式封装Retrofit+RxJava网络框架
 */
public interface RetrofitService {
    @GET("Stu/search")
    Call<Stu> getSearchStudent(@Query("stuId") String stuId,
                          @Query("password") String password);
}
