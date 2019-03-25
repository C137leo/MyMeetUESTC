package cn.edu.uestc.meet_on_the_road_of_uestc.home.prenster;

import java.util.HashMap;
import java.util.Map;

import cn.edu.uestc.meet_on_the_road_of_uestc.home.entitiy.WeatherEntity;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.service.WeatherService;
import cn.edu.uestc.meet_on_the_road_of_uestc.home.view.IVew;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePrenster implements IPrenster{
    RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    String key="v5gc3jwrth0lg92s";
    IVew iVew;
    Map map=new HashMap<String,String>();
    @Override
    public void attchIVew(IVew iVew) {
        this.iVew=iVew;
    }

    @Override
    public void getWeatherData() {
        map.put("key",key);
        map.put("location","ip");
        Observable<WeatherEntity> weatherEntityObservable=retrofitHelper.getService().getWeatherInfo(map);
        weatherEntityObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherEntity>() {
                    @Override
                    public void accept(WeatherEntity weatherEntity) throws Exception {
                        iVew.setLocation(weatherEntity.getResults().get(0).getLocation().getName());
                        iVew.setTemperature(weatherEntity.getResults().get(0).getNow().getTemperature());
                    }
                });
    }
}
