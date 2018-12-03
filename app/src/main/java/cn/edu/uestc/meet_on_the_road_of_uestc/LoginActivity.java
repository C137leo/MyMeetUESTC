package cn.edu.uestc.meet_on_the_road_of_uestc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button login;
    EditText login_account;
    EditText login_password;
    CheckBox remember_password;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
            }
        });
    }

}
