package cn.edu.uestc.meet_on_the_road_of_uestc.help.entity;

import org.greenrobot.greendao.annotation.Property;

public class HelpInfo {
    Long UID;
    String StuID;
    String location;
    String owner_name;
    String good_title;
    String publish_time;
    int isPay;
    String good_detail;

    public HelpInfo() {
    }

    public HelpInfo(Long UID, String stuID, String location, String owner_name, String good_title, String publish_time, int isPay, String good_detail) {
        this.UID = UID;
        StuID = stuID;
        this.location = location;
        this.owner_name = owner_name;
        this.good_title = good_title;
        this.publish_time = publish_time;
        this.isPay = isPay;
        this.good_detail = good_detail;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getStuID() {
        return StuID;
    }

    public void setStuID(String stuID) {
        StuID = stuID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getGood_title() {
        return good_title;
    }

    public void setGood_title(String good_title) {
        this.good_title = good_title;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public String getGood_detail() {
        return good_detail;
    }

    public void setGood_detail(String good_detail) {
        this.good_detail = good_detail;
    }
}
