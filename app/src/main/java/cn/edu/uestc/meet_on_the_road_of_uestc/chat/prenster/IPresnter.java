package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.net.Uri;

import cn.jpush.im.android.api.model.Message;

public interface IPresnter {
    void startChat(String userName);

    void senSingleMessage(String singleMessage);

    void sendLocation(double latitude,double longitude);

    void sendImage(Uri uri);

    void sendMessageToServe(Message message);
}
