package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoMaster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.IView;


public class HelpDetailPrenster implements IPrenster{
    private IView iView;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    @Override
    public void attchView(IView view) {
        this.iView=view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void searchDetailData(Long UID) {
        List<HelpInfo> helpInfoList= daoSession.getHelpInfoDao().queryBuilder().where(HelpInfoDao.Properties.UID.eq(UID)).list();
        iView.showData(helpInfoList.get(0));
    }
}
