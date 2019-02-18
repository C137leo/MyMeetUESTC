package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.net.Uri;

import cn.edu.uestc.meet_on_the_road_of_uestc.MyApplication;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class ChatPresnter implements IPresnter{
    String userName;
    String appKey;
    @Override
    public void startChat(String userName) {
        Conversation.createSingleConversation(userName, MyApplication.getJiguangAppkey());
        this.userName=userName;
        this.appKey=MyApplication.getJiguangAppkey();
    }

    @Override
    public void senSingleMessage(String singleMessage) {
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
    }

    @Override
    public void sendLocation(double latitude, double longitude) {

    }

    @Override
    public void sendImage(Uri uri) {

    }

    @Override
    public void sendMessageToServe(Message message) {

    }
}
