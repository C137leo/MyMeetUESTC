package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;
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
    @Override
    public View getInfoWindow(Marker marker) {
        View view=initView();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        stuID=marker.getTitle();
        return null;
    }

    public void getStuInfo(){
       stuInfo=navPrenster.searchStuInfo(stuID);
    }

    private View initView(){
        View view= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.navigation_infowindow,null);
        infowindow_stuName=view.findViewById(R.id.infowindow_stuname);
        infowindow_distance=view.findViewById(R.id.infowindow_distance);
        infowindow_grade=view.findViewById(R.id.infowindow_grade);
        infowindow_major=view.findViewById(R.id.infowindow_major);
        infowindow_sign=view.findViewById(R.id.infowindow_signature);
        infowindow_icon=view.findViewById(R.id.infowindow_image);
        return view;
    }
}
