package cn.edu.uestc.meet_on_the_road_of_uestc.help.entities;

import android.support.annotation.NonNull;

public class HelpInfoWithDistance implements Comparable<HelpInfoWithDistance>{
        private String UID; //唯一标识符
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
        private float distance; //距离

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
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

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public String getWhoFinishIt() {
        return whoFinishIt;
    }

    public void setWhoFinishIt(String whoFinishIt) {
        this.whoFinishIt = whoFinishIt;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getWhoFinishItStuID() {
        return whoFinishItStuID;
    }

    public void setWhoFinishItStuID(String whoFinishItStuID) {
        this.whoFinishItStuID = whoFinishItStuID;
    }

    public String getWhoFinishItStuMajor() {
        return whoFinishItStuMajor;
    }

    public void setWhoFinishItStuMajor(String whoFinishItStuMajor) {
        this.whoFinishItStuMajor = whoFinishItStuMajor;
    }

    public int getWhoFinishItStuGrade() {
        return whoFinishItStuGrade;
    }

    public void setWhoFinishItStuGrade(int whoFinishItStuGrade) {
        this.whoFinishItStuGrade = whoFinishItStuGrade;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public HelpInfoWithDistance(String UID, String stuID, String location, String owner_name, String good_title, String publish_time, int isPay, String good_detail, int isFinish, String whoFinishIt, String acceptTime, String whoFinishItStuID, String whoFinishItStuMajor, int whoFinishItStuGrade, float distance) {
        this.UID = UID;
        StuID = stuID;
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
        this.distance = distance;
    }

    @Override
    public int compareTo(@NonNull HelpInfoWithDistance o) {
        if(this.distance<o.distance){
            return 1;
        }else {
            return -1;
        }
    }
}
