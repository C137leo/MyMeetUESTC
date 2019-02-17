package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import dev.utils.common.DateUtils;

@Entity
public class HelpInfo implements Comparable<HelpInfo>{
    @Id(autoincrement = false)
    private String UID; //唯一标识符
    @Property
    private String StuID; //学号
    private String location; //地址
    private String owner_name; //姓名
    private String good_title; //帮帮标题
    private String publish_time; //发布时间
    private int isPay;  //是否有偿
    private String good_detail; //帮帮详情
    private int isFinish; //是否完成
    private String whoFinishIt; //谁完成
    private String acceptTime;  //接受时间
    private String whoFinishItStuID; //完成人的学号
    private String whoFinishItStuMajor; //完成人的专业
    private int whoFinishItStuGrade; //完成人的年级
    private double latitude; //地点的经度
    private double longitude; //地点的纬度
    @Generated(hash = 1493464364)
    public HelpInfo(String UID, String StuID, String location, String owner_name,
            String good_title, String publish_time, int isPay, String good_detail,
            int isFinish, String whoFinishIt, String acceptTime,
            String whoFinishItStuID, String whoFinishItStuMajor,
            int whoFinishItStuGrade, double latitude, double longitude) {
        this.UID = UID;
        this.StuID = StuID;
        this.location = location;
        this.owner_name = owner_name;
        this.good_title = good_title;
        this.publish_time = publish_time;
        this.isPay = isPay;
        this.good_detail = good_detail;
        this.isFinish = isFinish;
        this.whoFinishIt = whoFinishIt;
        this.acceptTime = acceptTime;
        this.whoFinishItStuID = whoFinishItStuID;
        this.whoFinishItStuMajor = whoFinishItStuMajor;
        this.whoFinishItStuGrade = whoFinishItStuGrade;
        this.latitude = latitude;
        this.longitude = longitude;
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
    public int getIsFinish() {
        return this.isFinish;
    }
    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
    public String getWhoFinishIt() {
        return this.whoFinishIt;
    }
    public void setWhoFinishIt(String whoFinishIt) {
        this.whoFinishIt = whoFinishIt;
    }
    public String getAcceptTime() {
        return this.acceptTime;
    }
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
    public String getWhoFinishItStuID() {
        return this.whoFinishItStuID;
    }
    public void setWhoFinishItStuID(String whoFinishItStuID) {
        this.whoFinishItStuID = whoFinishItStuID;
    }
    public String getWhoFinishItStuMajor() {
        return this.whoFinishItStuMajor;
    }
    public void setWhoFinishItStuMajor(String whoFinishItStuMajor) {
        this.whoFinishItStuMajor = whoFinishItStuMajor;
    }
    public int getWhoFinishItStuGrade() {
        return this.whoFinishItStuGrade;
    }
    public void setWhoFinishItStuGrade(int whoFinishItStuGrade) {
        this.whoFinishItStuGrade = whoFinishItStuGrade;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int compareTo(@NonNull HelpInfo helpInfo) {
        if(DateUtils.parseLong(helpInfo.getPublish_time())>DateUtils.parseLong(getPublish_time())){
            Log.d("adjust","adjust");
            return 1;
        }else{
            Log.d("adjust","adjust");
            return -1;
        }
    }
}
