package cn.edu.uestc.meet_on_the_road_of_uestc.help.help_manage.entities;

public class AcceptRecycleViewData {
    String publishHelpTitle;
    String publishHelpOwner;
    String publishHelpTime;
    String publishHelpAcceptTime;

    public AcceptRecycleViewData(String publishHelpTitle, String publishHelpOwner, String publishHelpTime, String publishHelpAcceptTime) {
        this.publishHelpTitle = publishHelpTitle;
        this.publishHelpOwner = publishHelpOwner;
        this.publishHelpTime = publishHelpTime;
        this.publishHelpAcceptTime = publishHelpAcceptTime;
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
}
