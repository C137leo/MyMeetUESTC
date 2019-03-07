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

import java.util.Collections;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;

public class HelpManageListViewAcceptAdapter extends RecyclerView.Adapter<HelpManageListViewAcceptAdapter.RecycleViewViewHolder> {
    View view;
    List<AcceptRecycleViewData> acceptRecycleViewData;
    Context context;
    MapView mapView;
    HelpManageListViewAcceptAdapter.RecycleViewViewHolder myHolder;
    AMap aMap;
    String location;
    RecycleViewViewHolder recycleViewViewHolder;
    OnImageviewClickListener onImageviewClickListener;
    public HelpManageListViewAcceptAdapter() {
    }

    public HelpManageListViewAcceptAdapter(Context context, List<AcceptRecycleViewData> acceptRecycleViewData){
        this.context=context;
        Collections.reverse(acceptRecycleViewData);
        this.acceptRecycleViewData=acceptRecycleViewData;
    }
    @NonNull
    @Override
    public RecycleViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_manage_accept_listview,parent,false);
        return new RecycleViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewViewHolder holder, final int position) {
        myHolder=holder;
        Log.d("onBindViewHolder","onBingViewHolder");
        holder.publishHelpTitle.setText(acceptRecycleViewData.get(position).getPublishHelpTitle());
        holder.publishHelpOwner.setText(acceptRecycleViewData.get(position).getPublishHelpOwner());
        holder.publishHelpAcceptTime.setText(acceptRecycleViewData.get(position).getPublishHelpAcceptTime());
        holder.publishHelpTime.setText(acceptRecycleViewData.get(position).getPublishHelpTime());
        holder.seeTheRouteHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageviewClickListener.onClick(acceptRecycleViewData.get(position).getPublishHelpOwnerStuID());
            }
        });
        location=acceptRecycleViewData.get(position).getPublishHelpLocation();
        if(holder.mapView.getParent()!=null){
            holder.mapView.removeAllViews();
        }
        holder.mapView.onCreate(new Bundle());
        aMap=holder.mapView.getMap();
        drawMarker(acceptRecycleViewData.get(position));
    }

    public void drawMarker(AcceptRecycleViewData acceptRecycleViewData){
        LatLng latLng=new LatLng(acceptRecycleViewData.getLatitude(),acceptRecycleViewData.getLontitude());
        final  Marker marker=aMap.addMarker(new MarkerOptions().position(latLng).title(acceptRecycleViewData.getPublishHelpTitle()).snippet(acceptRecycleViewData.getPublishHelpLocation()));
        aMap.moveCamera(CameraUpdateFactory.zoomBy(5));
        aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
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
        OnImageviewClickListener onImageviewClickListener;
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

    public interface OnImageviewClickListener{
        void onClick(String userID);
    }
    public void setOnClickListener(OnImageviewClickListener onImageviewClickListener){
        this.onImageviewClickListener=onImageviewClickListener;
    }
}
