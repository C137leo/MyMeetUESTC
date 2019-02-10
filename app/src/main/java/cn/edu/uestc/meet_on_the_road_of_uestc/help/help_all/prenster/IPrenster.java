package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.view.IView;

public interface IPrenster {

    void getData();
    void attchView(IView iView);
    void adjustDataByTime(List<HelpInfo> helpInfoList);
}
