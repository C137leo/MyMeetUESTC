package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AppointmentInfo {
    @Id(autoincrement = false)
    String appointmentUID;
    @Property
    String appointmentTitle;
    String publishTime;
    String whoPublish;
    String whoPublishStuID;
    int whoPublishStuGrade;
    String whoPublishStuMajor;
    String location;
    String appointmentTime;
    double appointmentLatitude;
    double appointmentLongtitude;
    int appointmentNum;
    @Generated(hash = 42264840)
    public AppointmentInfo(String appointmentUID, String appointmentTitle,
            String publishTime, String whoPublish, String whoPublishStuID,
            int whoPublishStuGrade, String whoPublishStuMajor, String location,
            String appointmentTime, double appointmentLatitude,
            double appointmentLongtitude, int appointmentNum) {
        this.appointmentUID = appointmentUID;
        this.appointmentTitle = appointmentTitle;
        this.publishTime = publishTime;
        this.whoPublish = whoPublish;
        this.whoPublishStuID = whoPublishStuID;
        this.whoPublishStuGrade = whoPublishStuGrade;
        this.whoPublishStuMajor = whoPublishStuMajor;
        this.location = location;
        this.appointmentTime = appointmentTime;
        this.appointmentLatitude = appointmentLatitude;
        this.appointmentLongtitude = appointmentLongtitude;
        this.appointmentNum = appointmentNum;
    }
    @Generated(hash = 1142461733)
    public AppointmentInfo() {
    }
    public String getAppointmentUID() {
        return this.appointmentUID;
    }
    public void setAppointmentUID(String appointmentUID) {
        this.appointmentUID = appointmentUID;
    }
    public String getPublishTime() {
        return this.publishTime;
    }
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
    public String getWhoPublish() {
        return this.whoPublish;
    }
    public void setWhoPublish(String whoPublish) {
        this.whoPublish = whoPublish;
    }
    public String getWhoPublishStuID() {
        return this.whoPublishStuID;
    }
    public void setWhoPublishStuID(String whoPublishStuID) {
        this.whoPublishStuID = whoPublishStuID;
    }
    public int getWhoPublishStuGrade() {
        return this.whoPublishStuGrade;
    }
    public void setWhoPublishStuGrade(int whoPublishStuGrade) {
        this.whoPublishStuGrade = whoPublishStuGrade;
    }
    public String getWhoPublishStuMajor() {
        return this.whoPublishStuMajor;
    }
    public void setWhoPublishStuMajor(String whoPublishStuMajor) {
        this.whoPublishStuMajor = whoPublishStuMajor;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAppointmentTime() {
        return this.appointmentTime;
    }
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    public int getAppointmentNum() {
        return this.appointmentNum;
    }
    public void setAppointmentNum(int appointmentNum) {
        this.appointmentNum = appointmentNum;
    }
    public double getAppointmentLatitude() {
        return this.appointmentLatitude;
    }
    public void setAppointmentLatitude(double appointmentLatitude) {
        this.appointmentLatitude = appointmentLatitude;
    }
    public double getAppointmentLongtitude() {
        return this.appointmentLongtitude;
    }
    public void setAppointmentLongtitude(double appointmentLongtitude) {
        this.appointmentLongtitude = appointmentLongtitude;
    }
    public String getAppointmentTitle() {
        return this.appointmentTitle;
    }
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }


}
