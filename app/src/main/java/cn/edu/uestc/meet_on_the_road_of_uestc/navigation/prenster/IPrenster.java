package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.navigation.view.IVew;

public interface IPrenster {

    String getStuId();
    StuInfo searchStuInfo(String StuID);
    void startChat(String stuID);
    void attchView(IVew iVew);
}
