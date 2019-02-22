package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.R;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.DefaultUser;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster.ChatPresnter;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.me.piiEdit.view.PiiEditActivity;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;

public class ChatActivity extends ChatBaseActivity implements View.OnClickListener, OnMenuClickListener {
    Toolbar toolbar;
    List<ChatMessage> chatMessageList=new ArrayList<>();
    ChatPresnter chatPresnter=new ChatPresnter();
    String userID;
    ChatInputView chatInputView;
    int IMAGE_PICK_REQUEST_CODE=0;
    int IMAGE_SHOOT_REQUEST_CODE=1;
    MessageList messageList;
    MsgListAdapter adapter = new MsgListAdapter<>(userID, null, null);
    Uri contentUri;
    File tempFile;
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
        messageList=findViewById(R.id.msg_list);
        messageList.setShowSenderDisplayName(true); //设置发送方显示昵称
        messageList.setShowReceiverDisplayName(true); //设置接收方显示昵称
        messageList.setAdapter(adapter);
        messageList.forbidScrollToRefresh(true);
        chatInputView=findViewById(R.id.chat_input);
        chatInputView.setMenuClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    IView iView=new IView() {
        @Override
        public void updateSingleMessageInAdapter(ChatMessage chatMessage) {
            adapter.addToStart(chatMessage, true);
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
            for(ChatMessage chatMessage:chatMessages){
                adapter.addToStart(chatMessage,true);
            }
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
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath());
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            contentUri = FileProvider.getUriForFile(ChatActivity.this, "cn.edu.uestc.meet_on_the_road_of_uestc", tempFile);
            Log.d("contentURI",String.valueOf(contentUri));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, IMAGE_SHOOT_REQUEST_CODE);
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
                    break;
                }
            case 1:
                if(resultCode==RESULT_OK){
                    try {
                        chatPresnter.sendImage(tempFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

        }
    }

    /**
     * 在线消息下发
     * @param event 在线消息下发事件
     */
    public void onEvent(MessageEvent event){
        DefaultUser reciverUser=new DefaultUser(event.getMessage().getFromUser().getUserName(),event.getMessage().getFromUser().getDisplayName(),null);
        switch (event.getMessage().getContentType()){
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) event.getMessage().getContent();
                String reciverImageLocalPath=imageContent.getLocalPath();//图片本地地址
                String receiveImageThumbnailPath=imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                ChatMessage chatImageMessage=new ChatMessage("",IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                chatImageMessage.setMediaFilePath(reciverImageLocalPath);
                chatImageMessage.setUserInfo(reciverUser);
                adapter.addToStart(chatImageMessage,true);
                break;
            case text:
                TextContent textContent=(TextContent)event.getMessage().getContent();
                ChatMessage chatTextMessage=new ChatMessage(textContent.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                chatTextMessage.setUserInfo(reciverUser);
                adapter.addToStart(chatTextMessage,true);
                break;
            case video:
                ImageContent videoContent = (ImageContent) event.getMessage().getContent();
                String reciverVideoLocalPath=videoContent.getLocalPath();//图片本地地址
                String receiveVideoThumbnailPath=videoContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                ChatMessage receiveVideoMessage=new ChatMessage("",IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                receiveVideoMessage.setMediaFilePath(reciverVideoLocalPath);
                receiveVideoMessage.setUserInfo(reciverUser);
                adapter.addToStart(receiveVideoMessage,true);
        }
    }
}
