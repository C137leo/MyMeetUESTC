package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Tip;

import java.util.List;

public interface IView {
    void addSuccess();
    void updateLocationEdittext(String location);
    void getTipList(List<Tip> tipList);
    void setPoiSearchLocation(PoiItem poiItem);
    void showInProgress();
    void addError(String errMsg);
    void getLocationError();
}
