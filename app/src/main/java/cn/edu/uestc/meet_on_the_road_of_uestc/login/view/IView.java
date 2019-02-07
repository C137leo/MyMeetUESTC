package cn.edu.uestc.meet_on_the_road_of_uestc.login.view;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public interface IView {
    void loginSuccess();
    void loginError(String errmsg);
}
