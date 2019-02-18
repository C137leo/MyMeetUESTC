package cn.edu.uestc.meet_on_the_road_of_uestc.chat.view;

import cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity.ChatMessage;

public interface IView {
    void updateMessageInAdapter(ChatMessage chatMessage);
    void sendError(String errMsg);
}
