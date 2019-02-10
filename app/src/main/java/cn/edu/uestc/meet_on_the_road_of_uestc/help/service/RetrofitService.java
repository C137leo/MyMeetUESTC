package cn.edu.uestc.meet_on_the_road_of_uestc.help.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    @POST("bbj/bbj_in.php")
    Observable<PostHelpAddStatus> postGoodData(@Body cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo helpInfo);

    @GET("bbj/bbj_out.php")
    Observable<ResponseBody> getGoodsData();
}
