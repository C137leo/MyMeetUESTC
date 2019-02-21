package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.adapter.ChatDetailAdapter;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.ChatPresnter;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;

public class ChatActivity extends ChatBaseActivity implements View.OnClickListener, OnMenuClickListener {
    Toolbar toolbar;
    RecyclerView chatMessageRecycle;
    List<ChatMessage> chatMessageList=new ArrayList<>();
    ChatDetailAdapter chatDetailAdapter=new ChatDetailAdapter(ChatActivity.this,chatMessageList);
    ChatPresnter chatPresnter=new ChatPresnter();
    String userID;
    ChatInputView chatInputView;
    int IMAGE_PICK_REQUEST_CODE=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        chatPresnter.attchView(iView);
        userID=getIntent().getStringExtra("StuID");
        chatPresnter.startChat(userID);
        setContentView(R.layout.activity_chat_detail);
        toolbar=findViewById(R.id.chat_detail_toolbar);
        toolbar.setTitle(chatPresnter.userNickName(userID));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //启动返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chatMessageRecycle=findViewById(R.id.chat_details_recyclerView);
        chatMessageRecycle.setLayoutManager(new LinearLayoutManager(MyApplication.getMyContext()));
        chatMessageRecycle.setAdapter(chatDetailAdapter);
        chatInputView=findViewById(R.id.chat_input);
        chatInputView.setMenuClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    IView iView=new IView() {
        @Override
        public void updateSingleMessageInAdapter(List<ChatMessage> chatMessages) {
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

    @Override
    public boolean onSendTextMessage(CharSequence input) {
        // 输入框输入文字后，点击发送按钮事件
        if(TextUtils.isEmpty(input.toString())) {
            Toast.makeText(ChatActivity.this,"不能输入空消息",Toast.LENGTH_SHORT).show();
        }else {
            String message = input.toString();
            chatPresnter.sentSingleMessage(message);
        }
        return true;
    }

    @Override
    public void onSendFiles(List<FileItem> list) {
        // 选中文件或者录制完视频后，点击发送按钮触发此事件


    }

    @Override
    public boolean switchToMicrophoneMode() {
        // 点击语音按钮触发事件，显示录音界面前触发此事件
        // 返回 true 表示使用默认的界面，若返回 false 应该自己实现界面
        return false;
    }

    @Override
    public boolean switchToGalleryMode() {
        // 点击图片按钮触发事件，显示图片选择界面前触发此事件
        // 返回 true 表示使用默认的界面
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMAGE_PICK_REQUEST_CODE);
        return false;
    }

    @Override
    public boolean switchToCameraMode() {
        // 点击拍照按钮触发事件，显示拍照界面前触发此事件
        // 返回 true 表示使用默认的界面
        return false;
    }

    @Override
    public boolean switchToEmojiMode() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(resultCode==RESULT_OK){
                    Uri imageUri=data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor=getContentResolver().query(imageUri,filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imagePath=cursor.getString(columnIndex); //照片路径
                    cursor.close();
                    File file=new File(imagePath);
                    try {
                        chatPresnter.sendImage(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

        }
    }

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
