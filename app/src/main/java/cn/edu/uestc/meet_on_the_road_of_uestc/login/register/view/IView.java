package cn.edu.uestc.meet_on_the_road_of_uestc.login.register.view;

import cn.edu.uestc.meet_on_the_road_of_uestc.login.entity.RegisterStatus;

public interface IView {

    void registerSuccess();
    void registerError(String errmsg);
    void netWorkError();
}
