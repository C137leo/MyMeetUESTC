package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities;

public class PublishRecycleViewData {
    String UID;
    String publishHelpTitle;
    String publishHelpOwner;
    String publishHelpTime;
    String publishHelpAcceptTime;
    String imagePath;
    String publishHelpAcceptName;
    String publishHelpAcceptMajor;
    int publishHelpAcceptGrade;
    int publishHelpAcceptStatus;
    String publishHelpLocation;

    public PublishRecycleViewData(String UID, String publishHelpTitle, String publishHelpOwner, String publishHelpTime, String publishHelpAcceptTime, String imagePath, String publishHelpAcceptName, String publishHelpAcceptMajor, int publishHelpAcceptGrade, int publishHelpAcceptStatus, String publishHelpLocation) {
        this.UID = UID;
        this.publishHelpTitle = publishHelpTitle;
        this.publishHelpOwner = publishHelpOwner;
        this.publishHelpTime = publishHelpTime;
        this.publishHelpAcceptTime = publishHelpAcceptTime;
        this.imagePath = imagePath;
        this.publishHelpAcceptName = publishHelpAcceptName;
        this.publishHelpAcceptMajor = publishHelpAcceptMajor;
        this.publishHelpAcceptGrade = publishHelpAcceptGrade;
        this.publishHelpAcceptStatus = publishHelpAcceptStatus;
        this.publishHelpLocation = publishHelpLocation;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getPublishHelpTitle() {
        return publishHelpTitle;
    }

    public void setPublishHelpTitle(String publishHelpTitle) {
        this.publishHelpTitle = publishHelpTitle;
    }

    public String getPublishHelpOwner() {
        return publishHelpOwner;
    }

    public void setPublishHelpOwner(String publishHelpOwner) {
        this.publishHelpOwner = publishHelpOwner;
    }

    public String getPublishHelpTime() {
        return publishHelpTime;
    }

    public void setPublishHelpTime(String publishHelpTime) {
        this.publishHelpTime = publishHelpTime;
    }

    public String getPublishHelpAcceptTime() {
        return publishHelpAcceptTime;
    }

    public void setPublishHelpAcceptTime(String publishHelpAcceptTime) {
        this.publishHelpAcceptTime = publishHelpAcceptTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPublishHelpAcceptName() {
        return publishHelpAcceptName;
    }

    public void setPublishHelpAcceptName(String publishHelpAcceptName) {
        this.publishHelpAcceptName = publishHelpAcceptName;
    }

    public String getPublishHelpAcceptMajor() {
        return publishHelpAcceptMajor;
    }

    public void setPublishHelpAcceptMajor(String publishHelpAcceptMajor) {
        this.publishHelpAcceptMajor = publishHelpAcceptMajor;
    }

    public int getPublishHelpAcceptGrade() {
        return publishHelpAcceptGrade;
    }

    public void setPublishHelpAcceptGrade(int publishHelpAcceptGrade) {
        this.publishHelpAcceptGrade = publishHelpAcceptGrade;
    }

    public int getPublishHelpAcceptStatus() {
        return publishHelpAcceptStatus;
    }

    public void setPublishHelpAcceptStatus(int publishHelpAcceptStatus) {
        this.publishHelpAcceptStatus = publishHelpAcceptStatus;
    }

    public String getPublishHelpLocation() {
        return publishHelpLocation;
    }

    public void setPublishHelpLocation(String publishHelpLocation) {
        this.publishHelpLocation = publishHelpLocation;
    }
}
