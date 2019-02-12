package cn.edu.uestc.meet_on_the_road_of_uestc.me.view;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

public interface IVew {

    void searchInformationSuccess(StuInfo stuInfo);
    void searchInformationFail();
}
