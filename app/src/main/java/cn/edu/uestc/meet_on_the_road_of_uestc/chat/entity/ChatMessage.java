package cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity;

public class ChatMessage {
    private String content;
    private int chatType;
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SENT=1;

    public ChatMessage(String content, int chatType) {
        this.content = content;
        this.chatType = chatType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }
}
