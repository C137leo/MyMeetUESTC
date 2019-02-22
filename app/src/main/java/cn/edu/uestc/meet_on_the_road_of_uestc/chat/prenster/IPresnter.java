package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.chat.view.IView;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public interface IPresnter {
    void startChat(String userName);

    void sentSingleMessage(String singleMessage);

    void sendLocation(double latitude,double longitude,String address);

    void sendImage(File file) throws FileNotFoundException;

    void sendMessageToServe(Message message);

    void attchView(IView iView);

    void updateMessageAllList();

    String userNickName(String userName);
}
