package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster;

import android.content.Context;
import android.view.View;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.IView;


public class HelpDetailPrenster implements IPrenster{
    private IView iView;

    @Override
    public void attchView(IView view) {
        this.iView=view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void searchDetailData(Long UID) {

    }
}
