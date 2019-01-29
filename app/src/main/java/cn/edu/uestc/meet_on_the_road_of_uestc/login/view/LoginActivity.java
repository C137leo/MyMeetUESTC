package cn.edu.uestc.meet_on_the_road_of_uestc.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;

import cn.edu.uestc.meet_on_the_road_of_uestc.MainActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.Stu;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.presenter.StuInfoPrenster;
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
    Stu stu;
    StuInfoPrenster stuInfoPrenster=new StuInfoPrenster();
    MediaType mediaTypeJson=MediaType.parse("application/json;charset=utf-8");
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CrashReport.initCrashReport(getApplicationContext());
        setContentView(R.layout.activity_login);
        daoSession= GreenDaoHelper.getDaoSession();
        login=findViewById(R.id.login_button);
        login_account=findViewById(R.id.login_account);
        login_password=findViewById(R.id.login_password);
        remember_password=findViewById(R.id.remember_password);
        pref=getSharedPreferences("no-use",MODE_PRIVATE);
        editor=pref.edit();
        Log.d("activity_login","This is a TEST");
        Boolean isRemember=pref.getBoolean("remember",false);
        registerAccount=findViewById(R.id.registerAccount);
        registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterService.class);
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
                editor.putString("account", account);
                editor.putString("password", password);
                if (remember_password.isChecked()) {
                    editor.putBoolean("remember", true);
                } else {
                    editor.putBoolean("remember", false);
                }
                editor.apply();
                stuInfoPrenster.attachView(iView);
                stuInfoPrenster.getStuInfo(account,password);
            }
        });
    }

    private IView iView=new IView() {
        @Override
        public void loginSuccess(Stu stu) {
            StuInfo stuInfo=new StuInfo(stu.getStuID(),stu.getStuName(),stu.getStuPassWord(),stu.getStuSignature(),stu.getStuGrade(),stu.getmLatitude(),stu.getmLontitude());
            daoSession.insertOrReplace(stuInfo);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            finish();
        }
    };
}
