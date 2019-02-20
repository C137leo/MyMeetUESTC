package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.HelpStatusToFinish;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entities.PostHelpAddStatus;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewAcceptAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageListViewPublishAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter.HelpManageViewpagerAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.PublishRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.model.HelpManageModel;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManagePublishViewpagerFragment;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.IVew;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.service.RetrofitService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HelpManagePrenster implements IPrenster {
    List<PublishRecycleViewData> listViewDataPublishRecycle =new ArrayList<>();
    Context context;
    FragmentManager fragmentManager;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    PublishRecycleViewData publishRecycleViewData;
    List helpManageViewpagerActivity;
    List<AcceptRecycleViewData> acceptRecycleViewData=new ArrayList<>();
    RetrofitService retrofitService=RetrofitHelper.getInstance(MyApplication.getMyContext()).getRetrofitService(MyApplication.getMyContext());
    Disposable updateHelpStatusToFinish;
    IVew iVew;
    LatLng acceptDetailLatlng;
    String acceptDetailSnippet;
    String acceptDetailTitle;
    HelpManageListViewAcceptAdapter helpManageListViewAcceptAdapter;
    List acceptUIDlIST=new ArrayList();
    HelpManageModel helpManageModel=new HelpManageModel();
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
    public void attchView(IVew iVew) {
        this.iVew=iVew;
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
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getUID(),helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),"","","","",2018,0,helpInfo.getLocation());
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }else if(helpInfo.getIsFinish()==1){
                Log.d("isFinish","1");
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getUID(),helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,1,helpInfo.getLocation());
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }else if(helpInfo.getIsFinish()==2){
                Log.d("isFinish","2");
                publishRecycleViewData =new PublishRecycleViewData(helpInfo.getUID(),helpInfo.getGood_title(),helpInfo.getOwner_name(),helpInfo.getPublish_time(),helpInfo.getAcceptTime(),"",helpInfo.getWhoFinishIt(),"",2018,2,helpInfo.getLocation());
                listViewDataPublishRecycle.add(publishRecycleViewData);
            }
        }
        iVew.dismissSwipeRefrshLayout();
    }

    public List<View> getHelpManageViewpagerActivity() {
        return helpManageViewpagerActivity;
    }

    @Override
    public HelpManageListViewAcceptAdapter initHelpManageListViewAcceptAdapter() {
        getRecycleAcceptData();
        helpManageListViewAcceptAdapter=new HelpManageListViewAcceptAdapter(MyApplication.getMyContext(),acceptRecycleViewData);
        return helpManageListViewAcceptAdapter;
    }

    @Override
    public void getRecycleAcceptData() {
        List<HelpInfo> helpInfoList=daoSession.queryBuilder(HelpInfo.class).where(HelpInfoDao.Properties.WhoFinishIt.eq(daoSession.getStuInfoDao().loadAll().get(0).getStuName())).list();
        for(HelpInfo helpInfo:helpInfoList) {
            if (!(acceptUIDlIST.contains(helpInfo.getUID()))) {
                if (helpInfo.getIsFinish() == 0) {
                } else if (helpInfo.getIsFinish() == 1) {
                    acceptUIDlIST.add(helpInfo.getUID());
                    acceptRecycleViewData.add(new AcceptRecycleViewData(helpInfo.getUID(), helpInfo.getGood_title(),helpInfo.getOwner_name(), helpInfo.getStuID(), helpInfo.getPublish_time(), helpInfo.getAcceptTime(), helpInfo.getLocation()));
                } else if (helpInfo.getIsFinish() == 2) {
                    acceptUIDlIST.add(helpInfo.getUID());
                    acceptRecycleViewData.add(new AcceptRecycleViewData(helpInfo.getUID(), helpInfo.getGood_title(),helpInfo.getOwner_name(), helpInfo.getStuID(), helpInfo.getPublish_time(), helpInfo.getAcceptTime(), helpInfo.getLocation()));
                }
            }
        }
        iVew.dismissSwipeRefrshLayout();
    }

    @Override
    public void updateStatusToFinish(final String UID) {
        HelpStatusToFinish helpStatusToFinish=new HelpStatusToFinish(UID,2);
        Observable<PostHelpAddStatus> observable=retrofitService.updateHelpStatusToFinish(helpStatusToFinish);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostHelpAddStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        updateHelpStatusToFinish=d;
                    }

                    @Override
                    public void onNext(PostHelpAddStatus postHelpAddStatus) {
                        if(postHelpAddStatus.getErrcode()==109) {
                            helpManageModel.writeDatabase(2,UID);
                            iVew.updateStatusToSuccess();
                        }else{
                            iVew.updateStatusFailed(postHelpAddStatus.getErrmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        updateHelpStatusToFinish.dispose();
                    }
                });
    }

}
