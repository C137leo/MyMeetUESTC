package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.view.NavFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster.NavPrenster;

public class InfoWindowAdapter implements AMap.InfoWindowAdapter {
    StuInfo stuInfo;
    String stuID;
    NavPrenster navPrenster=new NavPrenster();
    TextView infowindow_stuName;
    TextView infowindow_grade;
    TextView infowindow_major;
    TextView infowindow_sign;
    TextView infowindow_distance;
    CircleImageView infowindow_icon;
    TextView custom_info_title;
    TextView custom_info_snippet;
    View view;
    View customView;
    Button sayHello;
    OnSayHelloClickListener onSayHelloClickListener;
    @Override
    public View getInfoWindow(Marker marker) {
        initView();
        if(NavFragment.getMarketId().containsKey(marker.getTitle())) {
            getStuInfo();
            Log.d("NearByMarket", "showNearByMarket");
            initData(marker);
            return view;
        }else{
            initCustomData(marker);
            return customView;
        }
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    public void getStuInfo(){
       stuInfo=navPrenster.searchStuInfo(stuID);
    }
    private void initCustomData(Marker marker){
        custom_info_title.setText(marker.getTitle());
        custom_info_snippet.setText(marker.getSnippet());
    }

    private void initData(Marker marker){
        infowindow_stuName.setText(stuInfo.getStuName());
        infowindow_sign.setText(stuInfo.getStuSignature());
        infowindow_grade.setText(String.valueOf(stuInfo.getStuGrade()));
    }
    private void initView(){
        view= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.navigation_infowindow,null);
        customView=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.custome_infowindow,null);
        infowindow_stuName=view.findViewById(R.id.infowindow_stuname);
        infowindow_distance=view.findViewById(R.id.infowindow_distance);
        infowindow_grade=view.findViewById(R.id.infowindow_grade);
        infowindow_major=view.findViewById(R.id.infowindow_major);
        infowindow_sign=view.findViewById(R.id.infowindow_signature);
        infowindow_icon=view.findViewById(R.id.infowindow_image);
        custom_info_snippet=customView.findViewById(R.id.custom_info_snippet);
        custom_info_title=customView.findViewById(R.id.custom_info_title);
        sayHello=view.findViewById(R.id.say_hello);
        sayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSayHelloClickListener.onClick(stuInfo.getStuID());
            }
        });
    }

    public interface OnSayHelloClickListener{
        void onClick(String stuID);
    }

    public void setOnSayHelloClickListener(OnSayHelloClickListener onSayHelloClickListener){
        this.onSayHelloClickListener=onSayHelloClickListener;
    }
}
