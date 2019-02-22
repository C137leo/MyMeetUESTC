package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.DefaultUser;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.view.IView;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class ChatPresnter implements IPresnter{
    private IView iView;
    private String userName;
    private String appKey=MyApplication.getJiguangAppkey();
    private JMessageChatCallback jMessageChatCallback=new JMessageChatCallback();
    private String sendMessage;
    Conversation conversation;
    Disposable disposable;
    DefaultUser sendUser;
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }

    @Override
    public void startChat(final String userName) {
        sendUser=new DefaultUser(userName,userNickName(userName),null);
        Observable.create(new ObservableOnSubscribe<List<ChatMessage>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChatMessage>> emitter) throws Exception {
                conversation=JMessageClient.getSingleConversation(userName, appKey);
                if((conversation.getAllMessage()).isEmpty())
                {
                    Log.d("userName", userName);
                    Conversation.createSingleConversation(userName, MyApplication.getJiguangAppkey());
                }else{
                    List<ChatMessage> chatMessages=new ArrayList<>();
                    for(Message message:conversation.getAllMessage()){
                        if(message.getDirect()== MessageDirect.send) {
                            Log.d("conversation", conversation.getId());
                            if (message.getContentType() == ContentType.text) {
                                TextContent textContent = (TextContent) message.getContent();
                                ChatMessage chatMessage = new ChatMessage(textContent.getText(), IMessage.MessageType.SEND_TEXT.ordinal());
                                chatMessage.setUserInfo(sendUser);
                                chatMessages.add(chatMessage);
                            }
                        }else if(message.getDirect()==MessageDirect.receive){
                            DefaultUser reciverUser=new DefaultUser(message.getFromUser().getUserName(),message.getFromUser().getNickname(),null);
                            if (message.getContentType() == ContentType.text) {
                                TextContent textContent = (TextContent) message.getContent();
                                ChatMessage chatMessage = new ChatMessage(textContent.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                                chatMessage.setUserInfo(reciverUser);
                                chatMessages.add(chatMessage);
                            }
                        }
                    }
                    emitter.onNext(chatMessages);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChatMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(List<ChatMessage> chatMessages) {
                        Log.d("accept",String.valueOf(chatMessages.size()));
                        iView.updateExistConversationMessages(chatMessages);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.sendError("网络错误，请稍后重试");
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
        this.userName=userName;
    }

    @Override
    public String userNickName(String userName) {
        UserInfo userInfo= (UserInfo) conversation.getTargetInfo();
        String nickName=userInfo.getNickname();
        return nickName;
    }

    @Override
    public void sentSingleMessage(String singleMessage) {
        /**
         * 创建一条单聊文本消息，此方法是创建message的快捷接口，对于不需要关注会话实例的开发者可以使用此方法
         * 快捷的创建一条消息。其他的情况下推荐使用{@link Conversation#createSendMessage(MessageContent)}
         * 接口来创建消息
         *
         * @param username 聊天对象用户名
         * @param appKey   聊天对象所属应用的appKey
         * @param text     文本内容
         * @return 消息对象
         */
        Message singleTextMessage=JMessageClient.createSingleTextMessage(userName, appKey, singleMessage);
        sendMessageToServe(singleTextMessage);
        singleTextMessage.setOnSendCompleteCallback(jMessageChatCallback);
        sendMessage=singleMessage;
    }

    @Override
    public void sendLocation(double latitude, double longitude,String address) {
        /**
         * 创建一条单聊地理位置消息，此方法是创建message的快捷接口，对于不需要关注会话实例的开发者可以使用此方法
         * 快捷的创建一条消息。其他的情况下推荐使用{@link Conversation#createSendMessage(MessageContent)}
         * 接口来创建消息
         *
         * @param username  聊天对象的用户名
         * @param appKey    聊天对象所属应用的appKey
         * @param latitude  纬度信息
         * @param longitude 经度信息
         * @param scale     地图缩放比例
         * @param address   详细地址信息
         * @return 消息对象
         */
        Message message=JMessageClient.createSingleLocationMessage(userName, appKey, latitude, longitude, 1000, address);
        sendMessageToServe(message);
        message.setOnSendCompleteCallback(jMessageChatCallback);
    }

    @Override
    public void sendImage(File file) throws FileNotFoundException {
        /**
         * 创建一条单聊图片信息，此方法是创建message的快捷接口，对于不需要关注会话实例的开发者可以使用此方法
         * 快捷的创建一条消息。其他的情况下推荐使用{@link Conversation#createSendMessage(MessageContent)}
         * 接口来创建消息
         *
         * @param username  聊天对象的用户名
         * @param appKey    聊天对象所属应用的appKey
         * @param imageFile 图片文件
         * @return 消息对象
         * @throws FileNotFoundException
         */
        Message message=JMessageClient.createSingleImageMessage(userName, appKey, file);
        sendMessageToServe(message);
        message.setOnSendCompleteCallback(jMessageChatCallback);
    }

    @Override
    public void sendMessageToServe(Message message) {
        /**
         * 发送消息，使用默认发送配置参数.<br/>
         * 注意只有创建的消息和发送失败的消息可以被发送{@link MessageStatus}，<br/>
         * 发送成功或收到的消息再次发送需调用转发接口{@link JMessageClient#forwardMessage(Message, Conversation, MessageSendingOptions, RequestCallback)}.
         *
         * @param message 消息对象
         */
        JMessageClient.sendMessage(message);
    }

    @Override
    public void updateMessageAllList() {
        iView.updateConversationList(JMessageClient.getConversationList());
    }


    class JMessageChatCallback extends BasicCallback{
        @Override
        public void gotResult(int i, String s) {
            if(i==0){
                ChatMessage chatMessage=new ChatMessage(sendMessage, IMessage.MessageType.SEND_TEXT.ordinal());
                chatMessage.setUserInfo(sendUser);
                iView.updateSingleMessageInAdapter(chatMessage);
            }else{
                Log.d("jiguangIM",String.valueOf(i));
                iView.sendError(s);
            }
        }
    }
}
