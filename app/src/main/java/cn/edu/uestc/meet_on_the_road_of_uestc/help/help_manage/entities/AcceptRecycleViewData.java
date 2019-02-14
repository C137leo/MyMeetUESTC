package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities;

public class AcceptRecycleViewData {
    String UID;
    String publishHelpTitle;
    String publishHelpOwner;
    String publishHelpTime;
    String publishHelpAcceptTime;
    String publishHelpLocation;

    public AcceptRecycleViewData(String UID, String publishHelpTitle, String publishHelpOwner, String publishHelpTime, String publishHelpAcceptTime, String publishHelpLocation) {
        this.UID = UID;
        this.publishHelpTitle = publishHelpTitle;
        this.publishHelpOwner = publishHelpOwner;
        this.publishHelpTime = publishHelpTime;
        this.publishHelpAcceptTime = publishHelpAcceptTime;
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

    public String getPublishHelpLocation() {
        return publishHelpLocation;
    }

    public void setPublishHelpLocation(String publishHelpLocation) {
        this.publishHelpLocation = publishHelpLocation;
    }
}
