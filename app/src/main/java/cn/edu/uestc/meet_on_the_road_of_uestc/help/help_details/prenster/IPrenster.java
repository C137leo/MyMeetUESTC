package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster;

import android.view.View;

import java.net.ContentHandler;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.IView;

public interface IPrenster {
    void attchView(IView view);
    void onCreate();
    void searchDetailData(Long UID);
    void searchLocation();
}
