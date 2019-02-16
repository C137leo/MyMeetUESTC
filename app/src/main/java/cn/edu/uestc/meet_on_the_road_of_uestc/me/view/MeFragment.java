package cn.edu.uestc.meet_on_the_road_of_uestc.me.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view.PiiEditActivity;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter.MePrenster;

public class MeFragment extends Fragment implements View.OnClickListener {
    MePrenster mePrenster=new MePrenster(getActivity());
    View view;
    TextView pii_name;
    TextView pii_major;
    TextView pii_grade;
    TextView pii_signature;
    TextView pii_nickname;
    ImageView pii_edit;
    CircleImageView circleImageView;
    ImageView meMenu;
    View convertView;
    LineChart firstChart;
    LineChart secondChart;
    TextView firstChartTitle;
    TextView secondChartTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=getLayoutInflater().inflate(R.layout.fragment_me,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        convertView=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.popupwindow_me_menu,null);
        pii_nickname=getActivity().findViewById(R.id.pii_nickname);
        pii_name=getActivity().findViewById(R.id.pii_name);
        pii_major=getActivity().findViewById(R.id.pii_major);
        pii_grade=getActivity().findViewById(R.id.pii_grade);
        pii_signature=getActivity().findViewById(R.id.pii_signature);
        firstChart=getActivity().findViewById(R.id.me_menu_chart_first);
        secondChart=getActivity().findViewById(R.id.me_menu_second_chart);
        firstChartTitle=getActivity().findViewById(R.id.me_menu_chart_first_title);
        secondChartTitle=getActivity().findViewById(R.id.me_menu_chart_second_title);
        pii_edit=getActivity().findViewById(R.id.pii_edit);
        meMenu=getActivity().findViewById(R.id.me_menu);
        meMenu.setOnClickListener(this);
        pii_edit.setOnClickListener(this);
        mePrenster.attchView(iView);
        circleImageView=getActivity().findViewById(R.id.pii_image);
        if(mePrenster.isImageChange()!=null){
            circleImageView.setImageURI(mePrenster.isImageChange());
        }
        mePrenster.getStuInfo();
        mePrenster.drawFirstChartWithIPublish();
        mePrenster.drawSecondChartWithIAccept();
    }

    IVew iView=new IVew() {
        @Override
        public void searchInformationSuccess(StuInfo stuInfo) {
            pii_nickname.setText(stuInfo.getNickName());
            pii_name.setText(stuInfo.getStuName());
            pii_grade.setText(String.valueOf(stuInfo.getStuGrade()));
            pii_major.setText(stuInfo.getMajor());
            pii_signature.setText(stuInfo.getStuSignature());
        }

        @Override
        public void searchInformationFail() {
            Toast.makeText(MyApplication.getMyContext(),"初始化个人信息失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void initFirstChart(final List firstChartData) {
            List<Entry> firstChartFormatData=new ArrayList<>();
            for (int i=0;i<firstChartData.size();i++){
                firstChartFormatData.add(new Entry(Float.valueOf((int)firstChartData.get(i)),i));
            }
            firstChart.setNoDataText("暂无数据，还需要加油哦(๑•̀ㅂ•́)و✧加油");
            LineDataSet lineData=new LineDataSet(firstChartFormatData,"myHelp");
            lineData.setDrawValues(false);
            XAxis xAxis=firstChart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter()
            {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int IValue = (int) value;
                    CharSequence format = DateFormat.format("MM/dd",
                            System.currentTimeMillis()-(long)(firstChartData.size()-IValue)*24*60*60*1000);
                    return format.toString();
                }
            });
            YAxis yAxis = firstChart.getAxisLeft();
        }

        @Override
        public void initSecondChart(final List secondChartData) {
            List<Entry> secondChartFormatData=new ArrayList<>();
            for (int i=0;i<secondChartData.size();i++){
                secondChartFormatData.add(new Entry(1,i));
            }
            secondChart.setNoDataText("暂无数据，还需要加油哦(๑•̀ㅂ•́)و✧加油");
            LineDataSet lineData=new LineDataSet(secondChartFormatData,"myHelp");
            lineData.setDrawValues(false);
            XAxis xAxis=secondChart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter()
            {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int IValue = (int) value;
                    CharSequence format = DateFormat.format("MM/dd",
                            System.currentTimeMillis()-(long)(secondChartData.size()-IValue)*24*60*60*1000);
                    return format.toString();
                }
            });
            YAxis yAxis = secondChart.getAxisLeft();
        }
    };

    @Override
    public void onResume() {
        if(mePrenster.isImageChange()!=null){
            circleImageView.setImageURI(mePrenster.isImageChange());
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pii_edit:
                Intent intent=new Intent(getActivity(), PiiEditActivity.class);
                startActivity(intent);
            case R.id.me_menu:
                final PopupWindow popupWindow = new PopupWindow(convertView,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAsDropDown(v);
        }
    }
}
