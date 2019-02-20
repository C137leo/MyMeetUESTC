package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter.ChatDetailAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.ChatPresnter;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;

public class ChatActivity extends ChatBaseActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView chat_options_button;
    LinearLayout otherOption;
    ImageView chatAlbum;
    ImageView chatLocation;
    int OPTION_STATUS=0; //0时其他选项为关闭，1时为展开
    RecyclerView chatMessageRecycle;
    List<ChatMessage> chatMessageList=new ArrayList<>();
    ChatDetailAdapter chatDetailAdapter=new ChatDetailAdapter(ChatActivity.this,chatMessageList);
    ChatPresnter chatPresnter=new ChatPresnter();
    EditText messageInput;
    ImageView sendMessageConfirm;
    String userID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        chatPresnter.attchView(iView);
        userID=getIntent().getStringExtra("StuID");
        chatPresnter.startChat(userID);
        setContentView(R.layout.activity_chat_detail);
        toolbar=findViewById(R.id.chat_detail_toolbar);
        toolbar.setTitle(chatPresnter.userNickName(userID));
        messageInput=findViewById(R.id.chat_input);
        sendMessageConfirm=findViewById(R.id.message_send);
        sendMessageConfirm.setOnClickListener(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //启动返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chat_options_button=findViewById(R.id.other_option_button);
        chat_options_button.setOnClickListener(this);
        otherOption=findViewById(R.id.other_option);
        chatAlbum=findViewById(R.id.chat_album);
        chatLocation=findViewById(R.id.chat_location);
        chatAlbum.setOnClickListener(this);
        chatLocation.setOnClickListener(this);
        chatMessageRecycle=findViewById(R.id.chat_details_recyclerView);
        chatMessageRecycle.setLayoutManager(new LinearLayoutManager(MyApplication.getMyContext()));
        chatMessageRecycle.setAdapter(chatDetailAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.other_option_button:
                if(OPTION_STATUS==0) {
                    otherOption.setVisibility(View.VISIBLE);
                    OPTION_STATUS=1;
                }else if(OPTION_STATUS==1){
                    otherOption.setVisibility(View.GONE);
                    OPTION_STATUS=0;
                }
            case R.id.chat_album:

            case R.id.chat_location:

            case R.id.message_send:
                if(TextUtils.isEmpty(messageInput.getText().toString())) {
                    Toast.makeText(ChatActivity.this,"不能输入空消息",Toast.LENGTH_SHORT).show();
                }else {
                    String message = messageInput.getText().toString();
                    chatPresnter.sentSingleMessage(message);
                }

        }
    }

    IView iView=new IView() {
        @Override
        public void updateSingleMessageInAdapter(List<ChatMessage> chatMessages) {
            messageInput.setText("");
            chatDetailAdapter.addMessage(chatMessages);
            chatMessageRecycle.scrollToPosition(chatDetailAdapter.getItemCount()-1);
        }

        @Override
        public void sendError(String errMsg) {
            Toast.makeText(MyApplication.getMyContext(),errMsg,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateConversationList(List<Conversation> conversationList) {

        }

        @Override
        public void updateExistConversationMessages(List<ChatMessage> chatMessages) {
            chatDetailAdapter.addMessage(chatMessages);
            chatMessageRecycle.scrollToPosition(chatDetailAdapter.getItemCount()-1);
        }
    };

    public void onEvent(MessageEvent event){
        if(chatPresnter==null){
            chatPresnter=new ChatPresnter();
        }
        switch (event.getMessage().getContentType()){
            case file:
            case text:
                TextContent textContent=(TextContent)event.getMessage().getContent();
                chatPresnter.updateMessageList(textContent.getText());
        }
    }
}
