package cn.edu.uestc.meet_on_the_road_of_uestc.me.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public interface IVew {

    void searchInformationSuccess(StuInfo stuInfo);
    void searchInformationFail();
    void initFirstChart(List firstChartData);
    void initSecondChart(List secondChartData);
}
