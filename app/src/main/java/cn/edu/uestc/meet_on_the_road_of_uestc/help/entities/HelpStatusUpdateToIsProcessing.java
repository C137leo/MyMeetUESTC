package cn.edu.uestc.meet_on_the_road_of_uestc.help.entities;

public class HelpStatusUpdateToIsProcessing {

    String UID;
    String whoFinishIt;
    String acceptTime;
    int isFinish;

    public HelpStatusUpdateToIsProcessing(String UID, String whoFinishIt, String acceptTime, int isFinish) {
        this.UID = UID;
        this.whoFinishIt = whoFinishIt;
        this.acceptTime = acceptTime;
        this.isFinish = isFinish;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getWhoFinishIt() {
        return whoFinishIt;
    }

    public void setWhoFinishIt(String whoFinishIt) {
        this.whoFinishIt = whoFinishIt;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
}