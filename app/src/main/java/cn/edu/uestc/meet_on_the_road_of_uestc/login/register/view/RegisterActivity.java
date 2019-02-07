package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPrenster.attchView(iView);
        registerStuID=findViewById(R.id.registerStuID);
        registerPassword=findViewById(R.id.registerPassword);
        registerStuName=findViewById(R.id.registerName);
        registerNickName=findViewById(R.id.registerAccount);
        registerButton=findViewById(R.id.buttonSignUp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPrenster.registerccount(new StuInfo(registerStuID.getText().toString(),registerStuName.getText().toString(),registerPassword.getText().toString(),"",0,registerNickName.getText().toString()));
            }
        });
    }

    IView iView=new IView() {
        @Override
        public void registerSuccess() {
            finish();
        }

        @Override
        public void registerError(String errmsg) {
            Toast.makeText(RegisterActivity.this,errmsg,Toast.LENGTH_SHORT).show();
        }
    };
}
