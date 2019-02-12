package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.prenster;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view.IView;

public interface IPrenster {

    void registerccount(StuInfo stuInfo);
    void attchView(IView iView);
}
