package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.model;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpStatusUpdateToIsProcessing;

public class HelpDetailsModel implements IModel{
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    @Override
    public void updateStatusInDatabases(String UID, HelpStatusUpdateToIsProcessing helpStatusUpdateToIsProcessing) {
        HelpInfo helpInfo=daoSession.getHelpInfoDao().load(UID);
        helpInfo.setIsFinish(1);
        helpInfo.setAcceptTime(helpStatusUpdateToIsProcessing.getAcceptTime());
        helpInfo.setWhoFinishIt(helpStatusUpdateToIsProcessing.getWhoFinishIt());
        helpInfo.setWhoFinishItStuGrade(helpStatusUpdateToIsProcessing.getWhoFinishItStuGrade());
        helpInfo.setWhoFinishItStuID(helpStatusUpdateToIsProcessing.getWhoFinishItStuID());
        helpInfo.setWhoFinishItStuMajor(helpStatusUpdateToIsProcessing.getWhoFinishItStuMajor());
    }
}
