package cn.edu.uestc.meet_on_the_road_of_uestc.home.service;


import java.util.Map;

import cn.edu.uestc.meet_on_the_road_of_uestc.home.entitiy.WeatherEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WeatherService {
    @GET("weather/now.json")
    Observable<WeatherEntity> getWeatherInfo(@QueryMap Map<String,String> map);
}
