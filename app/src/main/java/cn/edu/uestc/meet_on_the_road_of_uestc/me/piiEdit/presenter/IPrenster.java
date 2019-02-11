package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.FileNotFoundException;

public interface IPrenster {

    void uploadImag(String path) throws FileNotFoundException;
}
