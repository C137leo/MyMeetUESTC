package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.FileNotFoundException;

import cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view.IView;

public interface IPrenster {

    void uploadImag(String path) ;
    Intent CutForPhoto(Uri uri);
    Intent CutForCamera(String camerapath, String imgname);
    void attchView(IView iView);
    void savePicture(String path);
    Uri isImageChange();
}
