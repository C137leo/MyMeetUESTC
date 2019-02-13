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

import com.amap.api.maps.MapView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.AcceptRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.view.HelpManageAcceptViewpagerFragment;

public class HelpManageListViewAcceptAdapter extends RecyclerView.Adapter<HelpManageListViewAcceptAdapter.RecycleViewViewHolder> {
    View view;
    List<AcceptRecycleViewData> acceptRecycleViewData;
    Context context;
    MapView mapView;
    public HelpManageListViewAcceptAdapter(Context context,List<AcceptRecycleViewData> acceptRecycleViewData){
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
        Log.d("onBindViewHolder","onBingViewHolder");
        holder.publishHelpTitle.setText(acceptRecycleViewData.get(position).getPublishHelpTitle());
        holder.publishHelpOwner.setText(acceptRecycleViewData.get(position).getPublishHelpOwner());
        holder.publishHelpAcceptTime.setText(acceptRecycleViewData.get(position).getPublishHelpAcceptTime());
        holder.publishHelpTime.setText(acceptRecycleViewData.get(position).getPublishHelpTime());
        holder.mapView.onCreate(new Bundle());
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
}
