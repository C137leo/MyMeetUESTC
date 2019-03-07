package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpInfoWithDistance;

public interface IVewNearBy {
    void updateData(List<HelpInfoWithDistance> helpInfoList);
    void hideRefershing();
}
