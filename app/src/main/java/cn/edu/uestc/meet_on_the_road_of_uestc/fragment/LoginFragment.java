package cn.edu.uestc.meet_on_the_road_of_uestc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class LoginFragment extends Fragment {
    EditText login_account=getActivity().findViewById(R.id.login_account);
    EditText login_password=getActivity().findViewById(R.id.login_password);
    Button login=getActivity().findViewById(R.id.login_button);
    String account;
    String password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getLayoutInflater().inflate(R.layout.fragment_login,container,false);
        Login();
        return view;
    }

    public void Login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit= MyApplication.getMyContext().getSharedPreferences("account",Context.MODE_PRIVATE).edit();
                String account=login_account.getText().toString();
                String password=login_password.getText().toString();
                edit.putString("account",account);
                edit.putString("passowrd",password);
                edit.apply();
            }
        });
    }
}
