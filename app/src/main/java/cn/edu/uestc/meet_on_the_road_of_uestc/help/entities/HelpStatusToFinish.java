package cn.edu.uestc.meet_on_the_road_of_uestc.help.entities;

public class HelpStatusToFinish {
    String UID;
    int isFinish;

    public HelpStatusToFinish(String UID, int isFinish) {
        this.UID = UID;
        this.isFinish = isFinish;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
}
