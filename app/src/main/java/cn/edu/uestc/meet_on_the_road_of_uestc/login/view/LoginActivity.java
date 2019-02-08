package cn.edu.uestc.meet_on_the_road_of_uestc.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;

import cn.edu.uestc.meet_on_the_road_of_uestc.MainActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter.StuInfoPrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view.RegisterActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity{
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button login;
    EditText login_account;
    EditText login_password;
    CheckBox remember_password;
    OkHttpClient mOkHttpClient;
    TextView registerAccount;
    String account;
    String password;
    DaoSession daoSession;
    StuInfo stuInfo;
    StuInfoPrenster stuInfoPrenster=new StuInfoPrenster();
    ProgressBar loginProgress;
    MediaType mediaTypeJson=MediaType.parse("application/json;charset=utf-8");
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CrashReport.initCrashReport(getApplicationContext());
        setContentView(R.layout.activity_login);
        daoSession= GreenDaoHelper.getDaoSession();
        login=findViewById(R.id.login_button);
        stuInfoPrenster.attachView(iView);
        login_account=findViewById(R.id.login_account);
        login_password=findViewById(R.id.login_password);
        remember_password=findViewById(R.id.remember_password);
        pref=getSharedPreferences("no-use",MODE_PRIVATE);
        loginProgress=findViewById(R.id.loginProgress);
        editor=pref.edit();
        Log.d("activity_login","This is a TEST");
        Boolean isRemember=pref.getBoolean("remember",false);
        registerAccount=findViewById(R.id.registerAccount);
        registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            login_account.setText(account);
            login_password.setText(password);
            remember_password.setChecked(true);
        }
        Login();
    }

    public void Login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = login_account.getText().toString();
                password = login_password.getText().toString();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("account", account);
                    editor.putString("password", password);
                    if (remember_password.isChecked()) {
                        editor.putBoolean("remember", true);
                    } else {
                        editor.putBoolean("remember", false);
                    }
                    loginProgress.setVisibility(View.VISIBLE);
                    stuInfoPrenster.getStuInfo(account,password);
                    editor.apply();
                }
            }
        });
    }

    private IView iView=new IView() {
        @Override
        public void loginSuccess() {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            loginProgress.setVisibility(View.GONE);
            onDestroy();
        }

        @Override
        public void loginError(String errmsg) {
            Toast.makeText(LoginActivity.this,errmsg,Toast.LENGTH_SHORT).show();
            loginProgress.setVisibility(View.GONE);
        }


        @Override
        public void netWorkError() {
            Toast.makeText(LoginActivity.this,"网路错误",Toast.LENGTH_SHORT).show();
            loginProgress.setVisibility(View.GONE);
        }
    };
}
