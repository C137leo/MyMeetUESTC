package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.MapView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewAcceptAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.PublishRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManagePublishViewpagerFragment;

public class HelpManagePrenster implements IPrenster{
    List<PublishRecycleViewData> listViewDataPublishRecycle =new ArrayList<>();
    Context context;
    FragmentManager fragmentManager;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    PublishRecycleViewData publishRecycleViewData;
    List helpManageViewpagerActivity;
    List<AcceptRecycleViewData> acceptRecycleViewData;
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
        helpManageViewpagerActivity.add(new HelpManageAcceptViewpagerFragment());
        helpManageViewpagerActivity.add(new HelpManagePublishViewpagerFragment());
        List<String> viewpagerTitle=new ArrayList();
        viewpagerTitle.add("我接受的");
        viewpagerTitle.add("我发布的");
        HelpManageViewpagerAdapter helpManageViewpagerAdapter =new HelpManageViewpagerAdapter(fragmentManager,helpManageViewpagerActivity,viewpagerTitle);
        return helpManageViewpagerAdapter;
    }

    @Override
    public HelpManageListViewPublishAdapter initHelpManageListViewPublishAdapter() {
        getListViewDataPublish();
        HelpManageListViewPublishAdapter helpManageListViewPublishAdapter=new HelpManageListViewPublishAdapter(context, listViewDataPublishRecycle);
        return helpManageListViewPublishAdapter;
    }

    @Override
    public void getListViewDataPublish() {
        HashSet hashSet=new HashSet(listViewDataPublishRecycle);
        listViewDataPublishRecycle.clear();
        listViewDataPublishRecycle.addAll(listViewDataPublishRecycle);
        List<HelpInfo> helpInfoList= daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.Owner_name.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        Log.d("listViewSize", String.valueOf(helpInfoList.size()));
        for(HelpInfo helpInfo:helpInfoList){
            if(helpInfo.getIsFinish()==0){
                Log.d("isFinish","0");
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),"","","","",2018,0);
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }else if(helpInfo.getIsFinish()==1){
                Log.d("isFinish","1");
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,1);
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }else if(helpInfo.getIsFinish()==2){
                Log.d("isFinish","2");
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,2);
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }
        }
    }

    public List<View> getHelpManageViewpagerActivity() {
        return helpManageViewpagerActivity;
    }

    @Override
    public HelpManageListViewAcceptAdapter initHelpManageListViewAcceptAdapter() {
        getRecycleAcceptData();
        HelpManageListViewAcceptAdapter helpManageListViewAcceptAdapter=new HelpManageListViewAcceptAdapter(MyApplication.getMyContext(),acceptRecycleViewData);
        return null;
    }

    @Override
    public void getRecycleAcceptData() {
        HashSet set=new HashSet(acceptRecycleViewData);
        acceptRecycleViewData.clear();
        acceptRecycleViewData.addAll(set);
        List<HelpInfo> helpInfoList=daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.WhoFinishIt.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        for(HelpInfo helpInfo:helpInfoList){
            if(helpInfo.getIsFinish()==0){

            }else if(helpInfo.getIsFinish()==1){
                acceptRecycleViewData.add(new AcceptRecycleViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime()));
            }else if(helpInfo.getIsFinish()==2){
                acceptRecycleViewData.add(new AcceptRecycleViewData(helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime()));
            }
        }
    }
}
