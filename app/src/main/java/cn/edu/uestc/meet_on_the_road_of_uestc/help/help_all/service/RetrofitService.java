package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.service;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {
    @GET("helpInfo/getData")
    Call<HelpInfo> getGoodsData();

    @POST("helpInfo/postData")
    Call<HelpInfo> postGoodData(@Part("HelpInfo") HelpInfo helpInfo);

}
