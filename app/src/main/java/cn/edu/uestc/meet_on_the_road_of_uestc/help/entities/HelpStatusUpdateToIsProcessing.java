package cn.edu.uestc.meet_on_the_road_of_uestc.help.entities;

public class HelpStatusUpdateToIsProcessing {

    String UID;
    String whoFinishIt;
    String acceptTime;
    int isFinish;
    private String whoFinishItStuID; //完成人的学号
    private String whoFinishItStuMajor; //完成人的专业
    private int whoFinishItStuGrade; //完成人的年级

    public HelpStatusUpdateToIsProcessing(String UID, String whoFinishIt, String acceptTime, int isFinish, String whoFinishItStuID, String whoFinishItStuMajor, int whoFinishItStuGrade) {
        this.UID = UID;
        this.whoFinishIt = whoFinishIt;
        this.acceptTime = acceptTime;
        this.isFinish = isFinish;
        this.whoFinishItStuID = whoFinishItStuID;
        this.whoFinishItStuMajor = whoFinishItStuMajor;
        this.whoFinishItStuGrade = whoFinishItStuGrade;
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

    public String getWhoFinishItStuID() {
        return whoFinishItStuID;
    }

    public void setWhoFinishItStuID(String whoFinishItStuID) {
        this.whoFinishItStuID = whoFinishItStuID;
    }

    public String getWhoFinishItStuMajor() {
        return whoFinishItStuMajor;
    }

    public void setWhoFinishItStuMajor(String whoFinishItStuMajor) {
        this.whoFinishItStuMajor = whoFinishItStuMajor;
    }

    public int getWhoFinishItStuGrade() {
        return whoFinishItStuGrade;
    }

    public void setWhoFinishItStuGrade(int whoFinishItStuGrade) {
        this.whoFinishItStuGrade = whoFinishItStuGrade;
    }
}