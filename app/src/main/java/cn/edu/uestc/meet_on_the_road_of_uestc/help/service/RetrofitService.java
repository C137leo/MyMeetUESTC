package cn.edu.uestc.meet_on_the_road_of_uestc.help.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    @POST("helpInfo/postHelpData")
    Observable<ResponseBody> postGoodData(@Body HelpInfo helpInfo);

    @GET("helpInfo/getData")
    Call<HelpInfo> getGoodsData();
}
