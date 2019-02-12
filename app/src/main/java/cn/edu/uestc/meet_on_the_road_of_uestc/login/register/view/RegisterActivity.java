package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.RegisterStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.register.prenster.RegisterPrenster;

public class RegisterActivity extends AppCompatActivity {
    RegisterPrenster registerPrenster=new RegisterPrenster();
    TextView registerStuID;
    TextView registerPassword;
    TextView registerStuName;
    TextView registerNickName;
    Button registerButton;
    CheckBox registerDocument;
    ImageView registerBack;
    ProgressBar registerProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPrenster.attchView(iView);
        registerProgress=findViewById(R.id.registerProgreeBar);
        registerStuID=findViewById(R.id.registerStuID);
        registerPassword=findViewById(R.id.registerPassword);
        registerStuName=findViewById(R.id.registerName);
        registerNickName=findViewById(R.id.registerAccount);
        registerButton=findViewById(R.id.buttonSignUp);
        registerDocument=findViewById(R.id.agreeCheckbox);
        registerBack=findViewById(R.id.registerBack);
        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registerDocument.isChecked()) {
                    Log.d("register","Register");
                    registerPrenster.registerccount(new StuInfo(registerStuID.getText().toString(), registerStuName.getText().toString(), registerPassword.getText().toString(), "", 0, registerNickName.getText().toString(),""));
                    registerProgress.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(RegisterActivity.this,"请阅读并同意隐私政策",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    IView iView=new IView() {
        @Override
        public void registerSuccess() {
            Log.d("RegisterSuccess","RegisterSuccessfully");
            registerProgress.setVisibility(View.GONE);
            finish();
        }

        @Override
        public void registerError(String errmsg) {
            Toast.makeText(RegisterActivity.this,errmsg,Toast.LENGTH_SHORT).show();
            registerProgress.setVisibility(View.GONE);
        }

        @Override
        public void netWorkError() {
            Toast.makeText(RegisterActivity.this,"网路错误",Toast.LENGTH_SHORT).show();
            registerProgress.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
