package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;

public interface IViewLatest {

    void updateData(List<HelpInfo> helpInfoList);
    void hideRefershing();
}
