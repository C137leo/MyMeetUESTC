package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.service;


import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity.NetworkStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    @POST("yb/yb.php")
    Observable<NetworkStatus> addSingleAppointmentData(@Body AppointmentInfo appointmentInfo);

    @GET("yb/getAllData")
    Observable<ResponseBody> getAllAppointmentData();
}
