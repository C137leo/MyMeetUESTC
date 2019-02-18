package cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;

public class ChatDetailAdapter extends RecyclerView.Adapter {
    int TYPE_ACCEPT=0;
    int TYPE_PUBLISH=1;
    Context context;
    List<String> message;
    public ChatDetailAdapter(Context context, List<String> message){
        this.context=context;
        this.message=message;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_ACCEPT){
            AcceptViewHolder acceptViewHolder=new AcceptViewHolder(LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.chat_accept,parent,false));
            return acceptViewHolder;
        }else if(viewType==TYPE_PUBLISH){
            PublishViewHolder publishViewHolder=new PublishViewHolder(LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.chat_publish,parent,false));
            return publishViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
