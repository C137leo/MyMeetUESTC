package cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.layout.CircleImageView;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

public class MessageListAdapter extends BaseAdapter {
    Context context;
    List<Conversation> conversationList;
    public MessageListAdapter(Context context, List<Conversation> conversationList){
        this.context=context;
        this.conversationList=conversationList;
    }
    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            holder=new Holder();
            convertView= LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.activity_message_list,parent,false);
            holder.chatUserName=convertView.findViewById(R.id.chat_username);
            holder.circleImageView=convertView.findViewById(R.id.user_image);
            holder.messageDetail=convertView.findViewById(R.id.message_detail);
            convertView.setTag(holder);
        }else {
            holder=(Holder) convertView.getTag();
        }
        UserInfo userInfo=(UserInfo) conversationList.get(position).getTargetInfo();
        holder.chatUserName.setText(userInfo.getNickname());
        if(conversationList.get(position).getLatestMessage().getContentType()== ContentType.text){
            TextContent textContent=(TextContent)conversationList.get(position).getLatestMessage().getContent();
            holder.messageDetail.setText(textContent.getText());
        }
        return convertView;
    }

    class Holder{
        CircleImageView circleImageView;
        TextView chatUserName;
        TextView messageDetail;
    }
}
