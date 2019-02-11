package cn.edu.uestc.meet_on_the_road_of_uestc.me.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {
    @POST("pii/post.php")
    @Multipart
    Observable<ResponseBody> uploadImage(@Part MultipartBody.Part requestBody);
}
