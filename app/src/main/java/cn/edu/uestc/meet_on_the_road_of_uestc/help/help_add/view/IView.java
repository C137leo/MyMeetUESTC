package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_add.view;

import com.amap.api.services.help.Tip;

import java.util.List;

public interface IView {
    void addSuccess();
    void getInputData();
    void updateLocationEdittext(String location);
    void listenLocationEdittext();
    void getTipList(List<Tip> tipList);
}
