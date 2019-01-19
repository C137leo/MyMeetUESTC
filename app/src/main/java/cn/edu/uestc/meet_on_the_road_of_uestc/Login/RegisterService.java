package cn.edu.uestc.meet_on_the_road_of_uestc.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

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

        }
    }
}
