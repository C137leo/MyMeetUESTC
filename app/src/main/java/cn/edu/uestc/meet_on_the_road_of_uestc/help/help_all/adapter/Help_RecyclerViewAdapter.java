package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_all.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.help.entity.HelpInfo;

public class Help_RecyclerViewAdapter extends RecyclerView.Adapter<Help_RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<HelpInfo> mList;
    public Help_RecyclerViewAdapter(Context context,List<HelpInfo> mList){
        this.mContext=context;
        this.mList=mList;
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if(mList!=null) {
            myViewHolder.good_title.setText(mList.get(i).getGood_title());
            myViewHolder.publish_name.setText(mList.get(i).getOwner_name());
            myViewHolder.publish_time.setText(mList.get(i).getPublish_time());
            myViewHolder.distance.setText("20");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.adapter_help_recycleview,viewGroup,false));
        return myViewHolder;
    }

    public void updateDataInFragment(List<HelpInfo> helpInfoList){
        for(HelpInfo helpInfo:helpInfoList){
            mList.add(helpInfo);
        }
        notifyDataSetChanged();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView good_title;
        TextView publish_name;
        TextView distance;
        TextView publish_time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            good_title=itemView.findViewById(R.id.goods_title);
            publish_name=itemView.findViewById(R.id.publish_name);
            distance=itemView.findViewById(R.id.distance);
            publish_time=itemView.findViewById(R.id.publish_time);
        }
}
}
