package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.service.RetrofitHelper;
import dev.utils.app.PhoneUtils;
import dev.utils.app.UriUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class PiiEditPrenster implements IPrenster{
    RetrofitHelper retrofitHelper=new RetrofitHelper();
    Context context;
    public PiiEditPrenster(Context context){
        this.context=context;
    }
    @Override
    public void uploadImag(String path) {
        File file=new File(path);
        Log.d("file",file.getName());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"),file);
        Log.d("requestBody", String.valueOf(requestBody.contentType()));
        MultipartBody.Part uploadImage=MultipartBody.Part.createFormData("image", file.getName(),requestBody);
        Observable<ResponseBody> observable=retrofitHelper.initRetrofitService().uploadImage(uploadImage);
        Log.d("upload","upload");
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d("responseBody",responseBody.string().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
