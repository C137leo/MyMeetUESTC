package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster.HelpDetailPrenster;

public class HelpDetailActivity extends AppCompatActivity {
    HelpDetailPrenster helpDetailPrenster=new HelpDetailPrenster();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        helpDetailPrenster.attchView(iView);
    }

    IView iView=new IView() {
        @Override
        public void showData() {

        }

        @Override
        public void searchData() {

        }
    };
}
