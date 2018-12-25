package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.IOException;

import cn.edu.uestc.meet_on_the_road_of_uestc.bean.Stu;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button login;
    EditText login_account;
    EditText login_password;
    CheckBox remember_password;
    OkHttpClient mOkHttpClient;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CrashReport.initCrashReport(getApplicationContext());
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login_button);
        login_account=findViewById(R.id.login_account);
        login_password=findViewById(R.id.login_password);
        remember_password=findViewById(R.id.remember_password);
        pref=getSharedPreferences("no-use",MODE_PRIVATE);
        editor=pref.edit();
        Log.d("activity_login","This is a TEST");
        Boolean isRemember=pref.getBoolean("remember",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            login_account.setText(account);
            login_password.setText(password);
        }
        Login();
    }

    public void Login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=login_account.getText().toString();
                String password=login_password.getText().toString();
                editor.putString("account",account);
                editor.putString("password",password);
                editor.putBoolean("remember",false);
                if(remember_password.isChecked()){
                    editor.putBoolean("remember",true);
                }
                editor.apply();
                sendUserId(new okhttp3.Callback(){
                               @Override
                               public void onResponse(Call call, Response response) throws IOException {
                                   String responDat=response.body().string();
                               }

                               @Override
                               public void onFailure(Call call, IOException e) {
                                   Log.d("postFaulier","posterror");
                               }
                           });
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendUserId(okhttp3.Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                Stu stu=new Stu();
                stu.setUsername(login_account.getText().toString());
                stu.setmPassword(login_password.getText().toString());
                String stu_json=gson.toJson(stu);
                mOkHttpClient = new OkHttpClient();
                RequestBody data = FormBody.create(MediaType.parse("application/json;charset=utf-8"),stu_json);
                Request request = new Request.Builder().url("http://47.107.162.132:80/conn_mysql.php").post(data).build();
                mOkHttpClient.newCall(request).enqueue(callback);
            }
        }).start();
    }
}
