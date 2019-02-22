package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;
import cn.jpush.im.android.api.model.Conversation;

public interface IView {
    void updateSingleMessageInAdapter(ChatMessage chatMessages);
    void sendError(String errMsg);
    void updateConversationList(List<Conversation> conversationList);
    void updateExistConversationMessages(List<ChatMessage> chatMessages);
}
