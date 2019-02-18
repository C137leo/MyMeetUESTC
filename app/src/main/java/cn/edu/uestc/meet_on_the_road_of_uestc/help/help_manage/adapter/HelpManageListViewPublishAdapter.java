package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.PublishRecycleViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;

import static cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.HelpInfoDao.Properties.UID;

public class HelpManageListViewPublishAdapter extends RecyclerView.Adapter<HelpManageListViewPublishAdapter.MyViewHolder> {
    List<PublishRecycleViewData> publishRecycleViewData;
    private Context context;
    HelpManageListViewPublishAdapter.MyViewHolder myViewHolder;
    OnItemClickListener onItemClickListener;
    public HelpManageListViewPublishAdapter(Context context,List<PublishRecycleViewData> publishRecycleViewData){
        this.context=context;
        this.publishRecycleViewData = publishRecycleViewData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_manage_publish_listview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        this.myViewHolder=holder;
        holder.finishHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(publishRecycleViewData.get(position).getUID());
                holder.finishHelpButton.setText("帮帮已完成");
                myViewHolder.publishHelpAcceptStatus.setText("已完成帮帮");
                holder.finishHelpButton.setClickable(false);
            }
        });
        holder.publishHelpLocation.setText(publishRecycleViewData.get(position).getPublishHelpLocation());
        holder.publishHelpTitle.setText(publishRecycleViewData.get(position).getPublishHelpTitle());
        holder.publishHelpTime.setText(publishRecycleViewData.get(position).getPublishHelpTime());
        if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==0){
            holder.publishHelpAcceptName.setText("待接受");
            holder.publishHelpAcceptStatus.setText("待接受");
            holder.publishHelpAcceptTime.setText("");
        }else if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==1){
            holder.publishHelpAcceptName.setText(publishRecycleViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptTime.setText(publishRecycleViewData.get(position).getPublishHelpAcceptTime());
            holder.publishHelpAcceptStatus.setText("正在进行帮帮");
        }else if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==2){
            holder.publishHelpAcceptName.setText(publishRecycleViewData.get(position).getPublishHelpAcceptName());
            Log.d("AcceptName",publishRecycleViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptTime.setText(publishRecycleViewData.get(position).getPublishHelpAcceptTime());
            holder.finishHelpButton.setText("帮帮已完成");
            holder.finishHelpButton.setClickable(false);
            holder.publishHelpAcceptStatus.setText("已完成帮帮");
        }
    }

    @Override
    public int getItemCount() {
        return publishRecycleViewData.size();
    }


    public void updateStatusToFinish(){
        myViewHolder.publishHelpAcceptStatus.setText("已完成帮帮");
        myViewHolder.finishHelpButton.setText("帮帮已完成");
        myViewHolder.finishHelpButton.setClickable(false);
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView publishHelpLocation;
        TextView publishHelpTitle;
        TextView publishHelpTime;
        Button finishHelpButton;
        TextView publishHelpAcceptTime;
        CircleImageView publishHelpAcceptImage;
        TextView publishHelpAcceptName;
        TextView publishHelpAcceptStatus;
        public OnItemClickListener onItemClickListener;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.d("getView","getView");
            publishHelpTitle=itemView.findViewById(R.id.publish_help_title);
            publishHelpTime=itemView.findViewById(R.id.publish_help_time);
            finishHelpButton=itemView.findViewById(R.id.finish_help_button);
            publishHelpAcceptTime=itemView.findViewById(R.id.publish_help_accept_time);
            publishHelpAcceptImage=itemView.findViewById(R.id.publish_help_accept_image);
            publishHelpAcceptName=itemView.findViewById(R.id.publish_help_accept_name);
            publishHelpAcceptStatus=itemView.findViewById(R.id.publish_help_accept_status);
            publishHelpLocation=itemView.findViewById(R.id.publish_help_location);
            publishHelpLocation.setSelected(true);
        }
    }
    public void setOnClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void onClick(String UID);
    }

}
