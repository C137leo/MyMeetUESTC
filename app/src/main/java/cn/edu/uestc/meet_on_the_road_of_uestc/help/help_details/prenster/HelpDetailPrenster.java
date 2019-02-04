package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.prenster;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoMaster;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_details.view.IView;


public class HelpDetailPrenster implements IPrenster, PoiSearch.OnPoiSearchListener {
    private IView iView;
    DaoSession daoSession= GreenDaoHelper.getDaoSession();
    String location;
    HelpInfo helpInfo;
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
        helpInfo=helpInfoList.get(0);
        iView.showData(helpInfo);
    }

    @Override
    public void searchLocation() {
        PoiSearch.Query query = new PoiSearch.Query(helpInfo.getLocation(), "", "");
        query.setPageSize(10);
        query.setPageNum(1);
        PoiSearch poiSearch = new PoiSearch(MyApplication.getMyContext(), query);
        poiSearch.setOnPoiSearchListener(this);
    }

    /**
     * 根据关键词搜索地点
     * @param poiResult 搜索结果列表
     * @param i 错误码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if(i==1000){
            PoiItem poiItem=poiResult.getPois().get(0);
            iView.setAmapLocation(new LatLng(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude()));
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
