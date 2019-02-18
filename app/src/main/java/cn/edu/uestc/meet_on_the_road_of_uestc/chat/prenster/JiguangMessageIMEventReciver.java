package cn.edu.uestc.meet_on_the_road_of_uestc.chat.prenster;

import android.os.Message;

import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;

public class JiguangMessageIMEventReciver {
    ChatPresnter chatPresnter;
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
