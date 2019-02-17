package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import com.amap.api.maps.model.LatLng;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpInfoWithDistance;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IViewLatest;

public interface IPrenster {

    void getData();
    void attchViewLatest(IViewLatest iViewLatest);
    void getDataFromDatabases();
    void calculateDistance(LatLng latLng1);
    void getLocation();
}
