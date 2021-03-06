package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.DefaultUser;
import cn.edu.uestc.meet_on_the_road_of_uestc.chat.view.IView;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.GreenDaoHelper;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
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
    DaoSession daoSession=GreenDaoHelper.getDaoSession();
    private IView iView;
    private String userName;
    private String appKey=MyApplication.getJiguangAppkey();
    Conversation conversation;
    Disposable disposable;
    DefaultUser sendUser;
    String avatrPath="https://www.happydoudou.xyz/moou/login_part/image/";
    @Override
    public void attchView(IView iView) {
        this.iView=iView;
    }

    @Override
    public void startChat(final String userName) {
        conversation=JMessageClient.getSingleConversation(userName, appKey);
        sendUser=new DefaultUser(daoSession.loadAll(StuInfo.class).get(0).getStuID(),daoSession.loadAll(StuInfo.class).get(0).getNickName(),avatrPath+daoSession.loadAll(StuInfo.class).get(0).getStuID()+".png");
        Observable.create(new ObservableOnSubscribe<List<ChatMessage>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChatMessage>> emitter) throws Exception {
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
                                chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                chatMessage.setUserInfo(sendUser);
                                chatMessages.add(chatMessage);
                            }
                            if(message.getContentType()==ContentType.image){
                                ChatMessage chatMessage=new ChatMessage("",IMessage.MessageType.SEND_IMAGE.ordinal());
                                ImageContent imageContent= (ImageContent) message.getContent();
                                chatMessage.setMediaFilePath(imageContent.getLocalPath());
                                chatMessage.setUserInfo(sendUser);
                                chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                chatMessages.add(chatMessage);
                            }
                        }else if(message.getDirect()==MessageDirect.receive){
                            DefaultUser reciverUser=new DefaultUser(message.getFromUser().getUserName(),message.getFromUser().getNickname(),null);
                            if (message.getContentType() == ContentType.text) {
                                TextContent textContent = (TextContent) message.getContent();
                                ChatMessage chatMessage = new ChatMessage(textContent.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                                chatMessage.setMessageStatus(IMessage.MessageStatus.RECEIVE_SUCCEED);
                                chatMessage.setUserInfo(reciverUser);
                                chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                chatMessages.add(chatMessage);
                            }
                            if(message.getContentType()==ContentType.image){
                                ChatMessage chatMessage=new ChatMessage("",IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                                ImageContent imageContent= (ImageContent) message.getContent();
                                chatMessage.setMediaFilePath(imageContent.getLocalPath());
                                chatMessage.setUserInfo(reciverUser);
                                chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
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

    /**
     * 创建一条群聊video消息，此方法是创建message的快捷接口，对于不需要关注会话实例的开发者可以使用此方法
     * 接口来创建消息
     *
     * @param thumbImage    视频缩略图，可不填。
     * @param thumbFormat   视频缩略图格式名
     * @param videoFile     视频文件对象
     * @param videoFileName 视频文件名称，如果不填或为空，则默认使用文件原名
     * @param duration      视频时长
     * @return 消息对象
     * @throws IOException
     * @since 2.6.0
     */
    @Override
    public void sentVideo(android.graphics.Bitmap thumbImage,
                          java.lang.String thumbFormat,
                          java.io.File videoFile,
                          java.lang.String videoFileName,
                          int duration) throws IOException {
        Message videoMessage=JMessageClient.createSingleVideoMessage(userName, appKey, thumbImage,thumbFormat, videoFile, videoFileName, duration);
        sendMessageToServe(videoMessage);
        final ChatMessage chatMessage=new ChatMessage("",IMessage.MessageType.SEND_VIDEO.ordinal());
        chatMessage.setUserInfo(sendUser);
        chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
        chatMessage.setMediaFilePath(videoFile.getPath());
        Log.d("VideoPath",videoFile.getPath());
        iView.addSingleMessageInAdapter(chatMessage);
        videoMessage.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(i==0){
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                    iView.updateSingleMessageInAdapter(chatMessage);
                }else {
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                    iView.updateSingleMessageInAdapter(chatMessage);
                    iView.sendError("网络错误");
                }
            }
        });
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
        final ChatMessage chatMessage=new ChatMessage(singleMessage,IMessage.MessageType.SEND_TEXT.ordinal());
        chatMessage.setUserInfo(sendUser);
        iView.addSingleMessageInAdapter(chatMessage); //添加到聊天列表尾部
        chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
        singleTextMessage.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(i==0){
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                    iView.updateSingleMessageInAdapter(chatMessage);
                }else {
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                    iView.updateSingleMessageInAdapter(chatMessage);
                    iView.sendError("网络错误");
                }
            }
        });
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
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
            }
        });
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
        final ChatMessage chatMessage=new ChatMessage("",IMessage.MessageType.SEND_IMAGE.ordinal());
        chatMessage.setUserInfo(sendUser);
        chatMessage.setMediaFilePath(file.getPath());
        Log.d("ImagePath",file.getPath());
        chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
        iView.addSingleMessageInAdapter(chatMessage);
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(i==0){
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                    iView.updateSingleMessageInAdapter(chatMessage);
                }else {
                    chatMessage.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                    iView.sendError("网路错误");
                }
            }
        });
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

}
