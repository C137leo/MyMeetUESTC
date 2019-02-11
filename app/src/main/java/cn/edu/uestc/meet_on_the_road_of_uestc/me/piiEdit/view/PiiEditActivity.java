package cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.presenter.PiiEditPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.view.PopupWindowSetImage;
import dev.utils.app.UriUtils;

public class PiiEditActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ALBUM_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int CROP_PHOTO = 2;
    CircleImageView circleImageView;
    EditText edit_nickName;
    EditText edit_signature;
    ImageView edit_back;
    TextView pop_pic;
    TextView pop_camera;
    PopupWindowSetImage popupWindowSetImage;
    File tempFile;
    PiiEditPrenster piiEditPrenster=new PiiEditPrenster(PiiEditActivity.this);
    Uri contentUri;
    ImageView testIamgeview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pii_editor);
        edit_nickName=findViewById(R.id.pii_edit_nickname);
        edit_signature=findViewById(R.id.pii_edit_signature);
        edit_back=findViewById(R.id.pii_edit_back);
        circleImageView=findViewById(R.id.pii_edit_icon);
        testIamgeview=findViewById(R.id.test_pic);
        popupWindowSetImage=new PopupWindowSetImage(PiiEditActivity.this);
        edit_back.setOnClickListener(this);
        pop_pic=popupWindowSetImage.getContentView().findViewById(R.id.pop_pic);
        pop_camera=popupWindowSetImage.getContentView().findViewById(R.id.pop_camera);
        circleImageView.setOnClickListener(this);
        pop_camera.setOnClickListener(this);
        pop_pic.setOnClickListener(this);
        piiEditPrenster.attchView(iView);
        if(piiEditPrenster.isImageChange()!=null){
            circleImageView.setImageURI(piiEditPrenster.isImageChange());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pii_edit_back: {
                finish();
                break;
            }
            case R.id.pii_edit_icon:{
                popupWindowSetImage.showAtLocation(v,Gravity.BOTTOM,0,0);
                break;
            }
            case R.id.pop_camera:{
                //用于保存调用相机拍照后所生成的文件
                tempFile = new File(Environment.getExternalStorageDirectory().getPath(), GreenDaoHelper.getDaoSession().getStuInfoDao().loadAll().get(0).getStuID() + ".jpg");
                //跳转到调用系统相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //判断版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                    intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    contentUri = FileProvider.getUriForFile(PiiEditActivity.this, "cn.edu.uestc.meet_on_the_road_of_uestc", tempFile);
                    Log.d("contentURI",String.valueOf(contentUri));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {    //否则使用Uri.fromFile(file)方法获取Uri
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                }
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            }
            case R.id.pop_pic:{
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");

                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    startActivityForResult(piiEditPrenster.CutForCamera(tempFile.getPath(),tempFile.getName()),CROP_PHOTO);
                    popupWindowSetImage.dismiss();
                    break;
                }
            case ALBUM_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Log.d("uploadFile","uploadFile");
                    startActivityForResult(piiEditPrenster.CutForPhoto(data.getData()),CROP_PHOTO);
                    popupWindowSetImage.dismiss();
                    break;
                }
            case CROP_PHOTO:
                piiEditPrenster.uploadImag(Environment.getExternalStorageDirectory().getPath()+"/"+GreenDaoHelper.getDaoSession().getStuInfoDao().loadAll().get(0).getStuID()+".png");
        }
    }



    IView iView=new IView() {
        @Override
        public void setImage(Uri uri,String path) {
            circleImageView.setImageURI(uri);
            piiEditPrenster.savePicture(path);
        }
    };
}
