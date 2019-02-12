package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.ListViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManagePublishViewpagerFragment;

public class HelpManagePrenster implements IPrenster{
    List<ListViewData> listViewDataList=new ArrayList<>();
    Context context;
    FragmentManager fragmentManager;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    ListViewData listViewData;
    List helpManageViewpagerActivity;
    public HelpManagePrenster(Context context, FragmentManager fragmentManager){
        this.context=context;
        this.fragmentManager=fragmentManager;
    }
    public HelpManagePrenster(Context context){
        this.context=context;
    }
    public HelpManagePrenster(){

    }

    @Override
    public HelpManageViewpagerAdapter initViewPagerAdapter() {
        if(helpManageViewpagerActivity==null){
            helpManageViewpagerActivity=new ArrayList<>();
        }
        helpManageViewpagerActivity.add(new HelpManagePublishViewpagerFragment());
        helpManageViewpagerActivity.add(new HelpManageAcceptViewpagerFragment());
        List<String> viewpagerTitle=new ArrayList();
        viewpagerTitle.add("我接受的");
        viewpagerTitle.add("我发布的");
        HelpManageViewpagerAdapter helpManageViewpagerAdapter =new HelpManageViewpagerAdapter(fragmentManager,helpManageViewpagerActivity,viewpagerTitle);
        return helpManageViewpagerAdapter;
    }

    @Override
    public HelpManageListViewPublishAdapter initHelpManageListViewPublishAdapter() {
        getListViewDataPublish();
        HelpManageListViewPublishAdapter helpManageListViewPublishAdapter=new HelpManageListViewPublishAdapter(context,listViewDataList);
        return helpManageListViewPublishAdapter;
    }

    @Override
    public void getListViewDataPublish() {
        HashSet hashSet=new HashSet(listViewDataList);
        listViewDataList.clear();
        listViewDataList.addAll(listViewDataList);
        List<HelpInfo> helpInfoList= daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.Owner_name.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        Log.d("listViewSize", String.valueOf(helpInfoList.size()));
        for(HelpInfo helpInfo:helpInfoList){
            if(helpInfo.getIsFinish()==0){
                Log.d("isFinish","0");
                listViewData=new ListViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),"","","","",2018,0);
                listViewDataList.add(listViewData);
            }else if(helpInfo.getIsFinish()==1){
                Log.d("isFinish","1");
                listViewData=new ListViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,1);
                listViewDataList.add(listViewData);
            }else if(helpInfo.getIsFinish()==2){
                Log.d("isFinish","2");
                listViewData=new ListViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,2);
                listViewDataList.add(listViewData);
            }
        }
    }

    public List<View> getHelpManageViewpagerActivity() {
        return helpManageViewpagerActivity;
    }
}
