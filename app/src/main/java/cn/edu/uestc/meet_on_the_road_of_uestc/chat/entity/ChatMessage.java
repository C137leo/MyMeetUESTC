package cn.edu.uestc.meet_on_the_road_of_uestc.chat.entity;

import java.util.HashMap;
import java.util.UUID;

import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.commons.models.IUser;

public class ChatMessage implements IMessage {
    private long id;
    private String text;
    private String timeString;
    private int type;
    private IUser user;
    private String contentFile;
    private long duration;
    private MessageStatus mMsgStatus = MessageStatus.CREATED;


    public ChatMessage(String text, int type) {
        this.text = text;
        this.type = type;
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }
    @Override
    public String getMsgId() {
        return String.valueOf(id);
    }

    @Override
    public IUser getFromUser() {
        if (user == null) {
            return new DefaultUser("0", "user1", null);
        }
        return user;
    }

    public void setUserInfo(IUser user) {
        this.user = user;
    }

    public void setMediaFilePath(String path) {
        this.contentFile = path;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public String getProgress() {
        return null;
    }

    /**
     * Return extra key value of message
     *
     * @return {@link HashMap<>}
     */
    @Override
    public HashMap<String, String> getExtras() {
        return null;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String getTimeString() {
        return timeString;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public MessageStatus getMessageStatus() {
        return this.mMsgStatus;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getMediaFilePath() {
        return contentFile;
    }

    /**
     * Set Message status. After sending Message, change the status so that the progress bar will dismiss.
     * @param messageStatus {@link cn.jiguang.imui.commons.models.IMessage.MessageStatus}
     */
    public void setMessageStatus(MessageStatus messageStatus) {
        this.mMsgStatus = messageStatus;
    }

}
