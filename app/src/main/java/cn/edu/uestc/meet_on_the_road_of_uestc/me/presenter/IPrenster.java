package cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter;

import android.net.Uri;

import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.IVew;

public interface IPrenster {

    void getStuInfo();
    void attchView(IVew iVew);
    Uri isImageChange();
}
