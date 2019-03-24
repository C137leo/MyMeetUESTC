package cn.edu.uestc.meet_on_the_road_of_uestc.home.service;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("weather/now.json")
    Observable<WeatherService> getWeatherInfo(@Path("key") String key, @Path("location") String location);
}
