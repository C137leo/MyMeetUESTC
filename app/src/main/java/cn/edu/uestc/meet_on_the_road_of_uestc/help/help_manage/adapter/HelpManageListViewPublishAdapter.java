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
import cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities.ListViewData;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;

public class HelpManageListViewPublishAdapter extends RecyclerView.Adapter<HelpManageListViewPublishAdapter.myViewHolder> {
    List<ListViewData> listViewData;
    private Context context;

    public HelpManageListViewPublishAdapter(Context context,List<ListViewData> listViewData){
        this.context=context;
        this.listViewData=listViewData;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myViewHolder myViewHolder=new myViewHolder(LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_manage_publish_listview,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.publishHelpTitle.setText(listViewData.get(position).getPublishHelpTitle());
        holder.publishHelpOwner.setText(listViewData.get(position).getPublishHelpOwner());
        holder.publishHelpTime.setText(listViewData.get(position).getPublishHelpTime());
        if(listViewData.get(position).getPublishHelpAcceptStatus()==0){
            holder.publishHelpAcceptName.setText("还未接受");
        }else if(listViewData.get(position).getPublishHelpAcceptStatus()==1){
            holder.publishHelpAcceptName.setText(listViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptGrade.setText(String.valueOf(listViewData.get(position).getPublishHelpAcceptGrade()));
            holder.publishHelpAcceptMajor.setText(listViewData.get(position).getPublishHelpAcceptMajor());
            holder.publishHelpAcceptTime.setText(listViewData.get(position).getPublishHelpAcceptTime());
            holder.publishHelpAcceptStatus.setText("正在进行");
        }else if(listViewData.get(position).getPublishHelpAcceptStatus()==2){
            holder.publishHelpAcceptName.setText(listViewData.get(position).getPublishHelpAcceptName());
            holder.publishHelpAcceptGrade.setText(listViewData.get(position).getPublishHelpAcceptGrade());
            holder.publishHelpAcceptMajor.setText(String.valueOf(listViewData.get(position).getPublishHelpAcceptMajor()));
            holder.publishHelpAcceptTime.setText(listViewData.get(position).getPublishHelpAcceptTime());
            holder.publishHelpAcceptStatus.setText("已完成");
        }
    }

    @Override
    public int getItemCount() {
        return listViewData.size();
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
}
