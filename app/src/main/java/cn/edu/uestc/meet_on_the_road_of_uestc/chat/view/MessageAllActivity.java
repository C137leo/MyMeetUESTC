package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter.MessageListAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.ChatPresnter;
import cn.jpush.im.android.api.model.Conversation;

public class MessageAllActivity extends AppCompatActivity {
    ListView messageAllList;
    List<Conversation> conversationList=new ArrayList<>();
    ChatPresnter chatPresnter=new ChatPresnter();
    MessageListAdapter messageListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        messageAllList=findViewById(R.id.message_all_listview);
        chatPresnter.attchView(iView);
        messageListAdapter=new MessageListAdapter(MessageAllActivity.this,conversationList);
        messageAllList.setAdapter(messageListAdapter);
        chatPresnter.updateMessageAllList();
    }

    IView iView=new IView() {
        @Override
        public void updateSingleMessageInAdapter(ChatMessage chatMessage) {

        }

        @Override
        public void sendError(String errMsg) {

        }

        @Override
        public void updateConversationList(List<Conversation> conversationList) {
            messageListAdapter.updateConversationList(conversationList);
        }
    };
}
