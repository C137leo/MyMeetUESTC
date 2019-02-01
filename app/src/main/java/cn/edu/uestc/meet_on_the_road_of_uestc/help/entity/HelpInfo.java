package cn.edu.uestc.meet_on_the_road_of_uestc.help.entity;

public class HelpInfo {
    private int isPay;  //0代表不需要，1代表需要
    private String stuID;
    private String owner_name;
    private String good_title;
    private String publish_time;
    private String location;
    private String good_detail;

    public HelpInfo() {
    }

    public HelpInfo(int isPay, String stuID, String owner_name, String good_title, String publish_time, String location, String good_detail) {
        this.isPay = isPay;
        this.stuID = stuID;
        this.owner_name = owner_name;
        this.good_title = good_title;
        this.publish_time = publish_time;
        this.location = location;
        this.good_detail = good_detail;
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

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }


    public String getGood_detail() {
        return good_detail;
    }

    public void setGood_detail(String good_detail) {
        this.good_detail = good_detail;
    }
}
