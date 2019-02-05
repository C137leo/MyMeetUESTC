package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.prenster;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view.IView;

public interface IPrenster {
    void attchView(IView iView);
    void postData();
    void getLatLngFromView(double latitude,double longitude);
    void getPoiSearch(String location);
    void initPostInfo(String good_title, String good_detail, String publish_location);
}
