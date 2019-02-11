package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.service.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.Observer;
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
    private File cutfile;
    private Uri mCutUri;
    IView iView;
    public PiiEditPrenster(Context context){
        this.context=context;
    }

    @Override
    public void attchView(IView iView) {
        this.iView=iView;
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
                        iView.setImage(FileProvider.getUriForFile(context,"cn.edu.uestc.meet_on_the_road_of_uestc",cutfile),cutfile.getPath());
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

    /**
     * 拍照之后，启动裁剪
     * @param camerapath 路径
     * @param imgname img 的名字
     * @return
     */
    @NonNull
    public Intent CutForCamera(String camerapath, String imgname) {
        try {

            //设置裁剪之后的图片路径文件
            cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    GreenDaoHelper.getDaoSession().getStuInfoDao().loadAll().get(0).getStuID()+".png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context,
                        "cn.edu.uestc.meet_on_the_road_of_uestc",
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);
            //把这个 uri 提供出去，就可以解析成 bitmap了
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY",200);
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片裁剪
     * @param uri
     * @return
     */
    @NonNull
    public Intent CutForPhoto(Uri uri) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    GreenDaoHelper.getDaoSession().getStuInfoDao().loadAll().get(0).getStuID()+".png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            outputUri = Uri.fromFile(cutfile);
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", 200); //200dp
            intent.putExtra("outputY",200);
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void savePicture(String path) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("ImageSave",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isChange",true);
        editor.putString("ImagePath",path);
        editor.apply();
    }

    @Override
    public Uri isImageChange() {
        SharedPreferences sharedPreferences=context.getSharedPreferences("ImageSave",Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("isChange",false)){
            return FileProvider.getUriForFile(context,"cn.edu.uestc.meet_on_the_road_of_uestc",new File(Environment.getExternalStorageDirectory().getPath(),
                    GreenDaoHelper.getDaoSession().getStuInfoDao().loadAll().get(0).getStuID()+".png"));
        }else {
            return null;
        }
    }
}
