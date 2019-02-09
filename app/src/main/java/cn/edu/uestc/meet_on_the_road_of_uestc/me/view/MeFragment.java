package cn.edu.uestc.meet_on_the_road_of_uestc.me.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.presenter.MePrenster;

public class MeFragment extends Fragment {
    MePrenster mePrenster=new MePrenster();
    View view;
    TextView pii_name;
    TextView pii_major;
    TextView pii_grade;
    TextView pii_signature;
    TextView pii_nickname;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=getLayoutInflater().inflate(R.layout.fragment_me,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pii_nickname=getActivity().findViewById(R.id.pii_nickname);
        pii_name=getActivity().findViewById(R.id.pii_name);
        pii_major=getActivity().findViewById(R.id.pii_major);
        pii_grade=getActivity().findViewById(R.id.pii_grade);
        pii_signature=getActivity().findViewById(R.id.pii_signature);
        mePrenster.attchView(iView);
        mePrenster.getStuInfo();
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

        }
    };
}
