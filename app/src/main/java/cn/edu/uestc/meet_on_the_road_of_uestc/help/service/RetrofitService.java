package cn.edu.uestc.meet_on_the_road_of_uestc.help.service;

import org.greenrobot.greendao.annotation.Property;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("helpInfo/getData")
    Call<HelpInfo> getGoodsData();

    @POST("helpInfo/postData")
    Call<HelpInfo> postGoodData(@Part("HelpInfo") HelpInfo helpInfo);

}
