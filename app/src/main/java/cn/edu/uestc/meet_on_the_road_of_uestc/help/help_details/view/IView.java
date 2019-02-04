package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view;

import com.amap.api.maps.model.LatLng;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;

public interface IView {

    void showData(HelpInfo helpInfo);
    void searchData();
    void setAmapLocation(LatLng latLng);
}
