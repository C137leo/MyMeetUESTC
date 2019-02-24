package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity.NetworkStatus;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("yb/postData")
    Observable<NetworkStatus> addSingleAppointmentData(AppointmentInfo appointmentInfo);

    @GET("yb/getAllData")
    Observable<ResponseBody> getAllAppointmentData();
}
