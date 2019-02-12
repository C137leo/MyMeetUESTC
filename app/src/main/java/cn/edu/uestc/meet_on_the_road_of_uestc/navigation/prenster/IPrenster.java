package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public interface IPrenster {

    String getStuId();
    StuInfo searchStuInfo(String StuID);
}
