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

public class HelpManageListViewPublishAdapter extends RecyclerView.Adapter<HelpManageListViewPublishAdapter.myViewHolder> {
    List<PublishRecycleViewData> publishRecycleViewData;
    private Context context;
    myViewHolder myViewHolder;
    OnItemClickListener onItemClickListener;
    public HelpManageListViewPublishAdapter(Context context,List<PublishRecycleViewData> publishRecycleViewData){
        this.context=context;
        this.publishRecycleViewData = publishRecycleViewData;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myViewHolder myViewHolder=new myViewHolder(LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_manage_publish_listview,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        this.myViewHolder=holder;
        holder.finishHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(publishRecycleViewData.get(position).getUID());
                holder.finishHelpButton.setText("帮帮已完成");
                holder.finishHelpButton.setClickable(false);
            }
        });
        holder.publishHelpTitle.setText(publishRecycleViewData.get(position).getPublishHelpTitle());
        holder.publishHelpOwner.setText(publishRecycleViewData.get(position).getPublishHelpOwner());
        holder.publishHelpTime.setText(publishRecycleViewData.get(position).getPublishHelpTime());
        if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==0){
            holder.publishHelpAcceptName.setText("待接受");
        }else if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==1){
            holder.publishHelpAcceptName.setText(publishRecycleViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptGrade.setText(String.valueOf(publishRecycleViewData.get(position).getPublishHelpAcceptGrade()));
            holder.publishHelpAcceptMajor.setText(publishRecycleViewData.get(position).getPublishHelpAcceptMajor());
            holder.publishHelpAcceptTime.setText(publishRecycleViewData.get(position).getPublishHelpAcceptTime());
            holder.publishHelpAcceptStatus.setText("正在进行帮帮");
        }else if(publishRecycleViewData.get(position).getPublishHelpAcceptStatus()==2){
            holder.publishHelpAcceptName.setText(publishRecycleViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptGrade.setText(String.valueOf(publishRecycleViewData.get(position).getPublishHelpAcceptGrade()));
            holder.publishHelpAcceptMajor.setText(publishRecycleViewData.get(position).getPublishHelpAcceptMajor());
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
        myViewHolder.finishHelpButton.setText("帮帮已完成");
        myViewHolder.finishHelpButton.setClickable(false);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        TextView publishHelpTitle;
        TextView publishHelpOwner;
        TextView publishHelpTime;
        Button finishHelpButton;
        TextView publishHelpAcceptTime;
        CircleImageView publishHelpAcceptImage;
        TextView publishHelpAcceptName;
        TextView publishHelpAcceptMajor;
        TextView publishHelpAcceptGrade;
        TextView publishHelpAcceptStatus;
        public OnItemClickListener onItemClickListener;
        public myViewHolder(View itemView) {
            super(itemView);
            Log.d("getView","getView");
            publishHelpTitle=itemView.findViewById(R.id.publish_help_title);
            publishHelpOwner=itemView.findViewById(R.id.publish_help_owner);
            publishHelpTime=itemView.findViewById(R.id.publish_help_time);
            finishHelpButton=itemView.findViewById(R.id.finish_help_button);
            publishHelpAcceptTime=itemView.findViewById(R.id.publish_help_accept_time);
            publishHelpAcceptImage=itemView.findViewById(R.id.publish_help_accept_image);
            publishHelpAcceptName=itemView.findViewById(R.id.publish_help_accept_name);
            publishHelpAcceptMajor=itemView.findViewById(R.id.publish_help_accept_major);
            publishHelpAcceptGrade=itemView.findViewById(R.id.publish_help_accept_grade);
            publishHelpAcceptStatus=itemView.findViewById(R.id.publish_help_accept_status);
        }
    }
    public void setOnClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void onClick(String UID);
    }

}
