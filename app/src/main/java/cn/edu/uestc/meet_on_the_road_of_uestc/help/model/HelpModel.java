package cn.edu.uestc.meet_on_the_road_of_uestc.help.model;


import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;

public class HelpModel implements IHelpModel{
    DaoSession daoSession;
    MyApplication myApplication;

    public HelpModel() {
        daoSession = GreenDaoHelper.getDaoSession();
    }

    public void setMyApplication(MyApplication myApplication){
        this.myApplication=myApplication;
    }
    @Override
    public void deleteData() {

    }

    /**
     * 保存需要帮助的信息
     * @param helpInfo  需要保存的信息
     */
    @Override
    public void saveGoodsData(HelpInfo helpInfo) {
        daoSession.insertOrReplace(helpInfo);
    }
}
