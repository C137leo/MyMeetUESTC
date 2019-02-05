package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HelpInfo {
    @Id(autoincrement = false)
    String UID;
    @Property
    String StuID;
    String location;
    String owner_name;
    String good_title;
    String publish_time;
    int isPay;
    String good_detail;
    @Generated(hash = 254620700)
    public HelpInfo(String UID, String StuID, String location, String owner_name,
            String good_title, String publish_time, int isPay, String good_detail) {
        this.UID = UID;
        this.StuID = StuID;
        this.location = location;
        this.owner_name = owner_name;
        this.good_title = good_title;
        this.publish_time = publish_time;
        this.isPay = isPay;
        this.good_detail = good_detail;
    }
    @Generated(hash = 498075579)
    public HelpInfo() {
    }
    public String getUID() {
        return this.UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public String getStuID() {
        return this.StuID;
    }
    public void setStuID(String StuID) {
        this.StuID = StuID;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getOwner_name() {
        return this.owner_name;
    }
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    public String getGood_title() {
        return this.good_title;
    }
    public void setGood_title(String good_title) {
        this.good_title = good_title;
    }
    public String getPublish_time() {
        return this.publish_time;
    }
    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
    public int getIsPay() {
        return this.isPay;
    }
    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }
    public String getGood_detail() {
        return this.good_detail;
    }
    public void setGood_detail(String good_detail) {
        this.good_detail = good_detail;
    }

}
