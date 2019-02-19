package cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;

public class ChatDetailAdapter extends RecyclerView.Adapter {
    int TYPE_ACCEPT=0;
    int TYPE_PUBLISH=1;
    Context context;
    List<ChatMessage> message=new ArrayList<>();
    public ChatDetailAdapter(Context context, List<ChatMessage> message){
        this.context=context;
        this.message=message;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(message.get(position).getChatType()==0){
            return 0;
        }else if(message.get(position).getChatType()==1){
            return 1;
        }
        return -1;
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
        if(holder instanceof AcceptViewHolder){
            ((AcceptViewHolder) holder).acceptMessage.setText(message.get(position).getContent());
        }else if(holder instanceof PublishViewHolder){
            ((PublishViewHolder) holder).publishMessge.setText(message.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public void addMessage(ChatMessage chatMessage){
        message.add(chatMessage);
        notifyItemRangeInserted(message.size(),1);
        notifyItemRangeChanged(message.size(),1);
        Log.d("addMessage",String.valueOf(message.size()));

    }
}
