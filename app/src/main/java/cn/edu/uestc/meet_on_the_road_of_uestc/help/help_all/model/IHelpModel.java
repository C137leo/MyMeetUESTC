package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.model;

import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;

public interface IHelpModel {
    void deleteData();
    void saveGoodsData(HelpInfo helpInfo);
}
