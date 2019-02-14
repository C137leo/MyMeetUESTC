package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.prenster.HelpManagePrenster;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;

public class HelpManageListViewAcceptAdapter extends RecyclerView.Adapter<HelpManageListViewAcceptAdapter.RecycleViewViewHolder> implements PoiSearch.OnPoiSearchListener {
    View view;
    List<AcceptRecycleViewData> acceptRecycleViewData;
    Context context;
    MapView mapView;
    RecycleViewViewHolder myHolder;
    AMap aMap;
    LatLng latLng;
    String title;
    String snippet;
    String location;
    public HelpManageListViewAcceptAdapter() {
    }

    public HelpManageListViewAcceptAdapter(Context context, List<AcceptRecycleViewData> acceptRecycleViewData){
        this.context=context;
        this.acceptRecycleViewData=acceptRecycleViewData;
    }
    @NonNull
    @Override
    public RecycleViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_manage_accept_listview,parent,false);
        RecycleViewViewHolder recycleViewViewHolder=new RecycleViewViewHolder(view);
        return recycleViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewViewHolder holder, int position) {
        myHolder=holder;
        Log.d("onBindViewHolder","onBingViewHolder");
        holder.publishHelpTitle.setText(acceptRecycleViewData.get(position).getPublishHelpTitle());
        holder.publishHelpOwner.setText(acceptRecycleViewData.get(position).getPublishHelpOwner());
        holder.publishHelpAcceptTime.setText(acceptRecycleViewData.get(position).getPublishHelpAcceptTime());
        holder.publishHelpTime.setText(acceptRecycleViewData.get(position).getPublishHelpTime());
        location=acceptRecycleViewData.get(position).getPublishHelpLocation();
        holder.mapView.onCreate(new Bundle());
        aMap=holder.mapView.getMap();
        initMapViewMaker();
    }
    public void initMapViewMaker(){
        Log.d("SearchPoi","SearchPoi");
        PoiSearch.Query query=new PoiSearch.Query(location,"","");
        PoiSearch poiSearch=new PoiSearch(context,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    public void drawMarker(){
        final  Marker marker=aMap.addMarker(new MarkerOptions().position(latLng).title(title).snippet(snippet));
        aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        aMap.moveCamera(CameraUpdateFactory.zoomBy(15));
    }
    @Override
    public int getItemCount() {
        return acceptRecycleViewData.size();
    }

    public MapView getAcceptMapView(){
        return mapView;
    }

    class RecycleViewViewHolder extends RecyclerView.ViewHolder {
        TextView publishHelpTitle;
        TextView publishHelpOwner;
        TextView publishHelpTime;
        Button seeTheRouteHelp;
        TextView publishHelpAcceptTime;
        MapView mapView;
        public RecycleViewViewHolder(View itemView) {
            super(itemView);
            publishHelpTitle=itemView.findViewById(R.id.accept_help_title);
            publishHelpOwner=itemView.findViewById(R.id.accept_help_owner);
            publishHelpTime=itemView.findViewById(R.id.accept_publish_help_time);
            seeTheRouteHelp=itemView.findViewById(R.id.see_the_route_help);
            publishHelpAcceptTime=itemView.findViewById(R.id.accept_help_accept_time);
            mapView=itemView.findViewById(R.id.accept_help_map);
        }
    }

    /**
     * POI数据获取
     * @param poiResult POI数据列表
     * @param i 状态码，1000即为成功
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        Log.d("poiSearch",String.valueOf(i));
        PoiItem poiItem=poiResult.getPois().get(0);
        latLng=new LatLng(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
        title=poiItem.getTitle();
        snippet=poiItem.getSnippet();
        drawMarker();
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
