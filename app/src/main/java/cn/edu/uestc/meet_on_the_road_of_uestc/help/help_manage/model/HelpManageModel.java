package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.model;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;

public class HelpManageModel implements IModel{
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    @Override
    public void writeDatabase(int status,String UID) {
        switch (status) {
            case 2:
                HelpInfo helpInfo=daoSession.getHelpInfoDao().load(UID);
                helpInfo.setIsFinish(2);
                daoSession.insertOrReplace(helpInfo);
        }

    }
}
