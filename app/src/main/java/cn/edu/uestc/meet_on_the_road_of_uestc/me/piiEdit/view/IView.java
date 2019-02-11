package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view;

import android.net.Uri;

public interface IView {

    void setImage(Uri uri,String path);
    void changeSuccess();
    void changeError(String errMsg);
}
