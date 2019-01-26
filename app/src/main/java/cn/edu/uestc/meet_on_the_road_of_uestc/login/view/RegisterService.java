package cn.edu.uestc.meet_on_the_road_of_uestc.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.registerInfo;
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
    private String registerAccount=null;
    private String registerName=null;
    private String registerStuID=null;
    private String registerPassword=null;
    private EditText ETregisterAccount;
    private EditText ETregisterName;
    private EditText ETregisterStuID;
    private EditText ETregisterPassword;
    private Button registerButton;
    private CheckBox agreeDocument;
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
        agreeDocument=findViewById(R.id.agreeCheckbox);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreeDocument.isChecked()) {
                    register();
                }else{
                    Toast.makeText(MyApplication.getMyContext(),"请阅读并同意《用户注册协议》",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(){
        registerAccount=ETregisterAccount.getText().toString();
        registerName=ETregisterName.getText().toString();
        registerStuID=ETregisterStuID.getText().toString();
        registerPassword=ETregisterPassword.getText().toString();
        Log.d("registerAccount",ETregisterAccount.getText().toString());
        if(!registerAccount.equals("") && !registerName.equals("") &&!registerPassword.equals("")&&!registerStuID.equals("")){
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
                    Intent intent=new Intent(RegisterService.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            if(registerAccount.isEmpty()){
                Toast.makeText(MyApplication.getMyContext(),"用户名为空",Toast.LENGTH_SHORT).show();
            }
            if(registerName.isEmpty()){
                Toast.makeText(MyApplication.getMyContext(),"姓名为空",Toast.LENGTH_SHORT).show();
            }
            if(registerStuID.isEmpty()){
                Toast.makeText(MyApplication.getMyContext(),"学号为空",Toast.LENGTH_SHORT).show();
            }

            if(registerPassword.isEmpty()){
                Toast.makeText(MyApplication.getMyContext(),"密码为空",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
