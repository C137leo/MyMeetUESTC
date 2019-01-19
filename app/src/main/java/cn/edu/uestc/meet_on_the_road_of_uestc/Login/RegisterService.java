package cn.edu.uestc.meet_on_the_road_of_uestc.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.Login.bean.registerInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterService extends AppCompatActivity {
    private String registerAccount;
    private String registerName;
    private String registerStuID;
    private String registerPassword;
    private EditText ETregisterAccount;
    private EditText ETregisterName;
    private EditText ETregisterStuID;
    private EditText ETregisterPassword;
    private Button registerButton;
    String server="https://www.happydoudou.xyz";
    OkHttpClient okHttpClient;
    Gson gson;
    MediaType mediaTypeJson=MediaType.parse("application/json;charset=utf-8");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ETregisterAccount=findViewById(R.id.registerAccount);
        ETregisterName=findViewById(R.id.registerName);
        ETregisterPassword=findViewById(R.id.registerPassword);
        ETregisterStuID=findViewById(R.id.registerStuID);
        registerButton=findViewById(R.id.buttonSignUp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register(){
        registerAccount=ETregisterAccount.getText().toString();
        registerName=ETregisterName.getText().toString();
        registerStuID=ETregisterStuID.getText().toString();
        registerPassword=ETregisterPassword.getText().toString();
        if(registerAccount!=null&&registerName!=null&&registerPassword!=null&&registerStuID!=null){
            okHttpClient=new OkHttpClient();
            gson=new Gson();
            final registerInfo registerInfo=new registerInfo(registerAccount,registerName,registerStuID,registerPassword);
            String postInfo=gson.toJson(registerInfo);
            RequestBody requestBody=RequestBody.create(mediaTypeJson,postInfo);
            Request request=new Request.Builder()
                    .url(server)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("NetWorkError","注册失败");
                    Toast.makeText(MyApplication.getMyContext(),"网络错误，注册失败",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("Register Server",response.body().string());
                    Toast.makeText(MyApplication.getMyContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterService.this,LoginActivity.class);
                    startActivity(intent);
                    onDestroy();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
