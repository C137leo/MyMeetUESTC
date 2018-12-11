package cn.edu.uestc.meet_on_the_road_of_uestc.adapter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;

public class mOnPoiSearchListener implements PoiSearch.OnPoiSearchListener {
    PoiSearch poiSearch;
    PoiSearch.Query query;
    String keywords;
    String ctgr;
    String city;
    ArrayList<LatLng> array;

    public ArrayList<LatLng> getArray() {
        return array;
    }

    public void setArray(ArrayList<LatLng> array) {
        this.array = array;
    }

    public mOnPoiSearchListener(String query, String ctgr, String city){
        this.city=city;
        this.ctgr=ctgr;
        this.keywords=query;
    }
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        query = new PoiSearch.Query(keywords, "","郫都区");
        query.setPageSize(10);
        poiSearch = new PoiSearch(MyApplication.getMyContext(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        for(int m=0;m<i;m++){
            LatLng mLatLng=new LatLng(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
            array.add(mLatLng);
        }
    }
}
