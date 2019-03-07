package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.converter.StuInfoListConverter;

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
    String appointmentDate;
    String appointmentTime;
    double appointmentLatitude;
    double appointmentLongtitude;
    int appointmentNum;
    int appointmentType; // 1:约图书馆,2:约跑,3:约饭,0:自定义
    String appointmentTypeText;
    int appointmentStatus; //0:正在进行 1:已完成 3:删除
    @Convert(converter = StuInfoListConverter.class,columnType = String.class)
    List<StuInfo> appointmentStuInfoList; //参与学生的数组
    @Generated(hash = 1710594240)
    public AppointmentInfo(String appointmentUID, String appointmentTitle,
            String publishTime, String whoPublish, String whoPublishStuID,
            int whoPublishStuGrade, String whoPublishStuMajor, String location,
            String appointmentDate, String appointmentTime,
            double appointmentLatitude, double appointmentLongtitude,
            int appointmentNum, int appointmentType, String appointmentTypeText,
            int appointmentStatus, List<StuInfo> appointmentStuInfoList) {
        this.appointmentUID = appointmentUID;
        this.appointmentTitle = appointmentTitle;
        this.publishTime = publishTime;
        this.whoPublish = whoPublish;
        this.whoPublishStuID = whoPublishStuID;
        this.whoPublishStuGrade = whoPublishStuGrade;
        this.whoPublishStuMajor = whoPublishStuMajor;
        this.location = location;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentLatitude = appointmentLatitude;
        this.appointmentLongtitude = appointmentLongtitude;
        this.appointmentNum = appointmentNum;
        this.appointmentType = appointmentType;
        this.appointmentTypeText = appointmentTypeText;
        this.appointmentStatus = appointmentStatus;
        this.appointmentStuInfoList = appointmentStuInfoList;
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
    public int getAppointmentType() {
        return this.appointmentType;
    }
    public void setAppointmentType(int appointmentType) {
        this.appointmentType = appointmentType;
    }
    public String getAppointmentTypeText() {
        return this.appointmentTypeText;
    }
    public void setAppointmentTypeText(String appointmentTypeText) {
        this.appointmentTypeText = appointmentTypeText;
    }
    public int getAppointmentStatus() {
        return this.appointmentStatus;
    }
    public void setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
    public List<StuInfo> getAppointmentStuInfoList() {
        return this.appointmentStuInfoList;
    }
    public void setAppointmentStuInfoList(List<StuInfo> appointmentStuInfoList) {
        this.appointmentStuInfoList = appointmentStuInfoList;
    }
    public String getAppointmentDate() {
        return this.appointmentDate;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


}
