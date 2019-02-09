package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class traceInfo {

    @Id(autoincrement = false)
    private String date;
    @Property
    private String stuID;
    private String latitude;
    private String lontitude;
    private double speed;
    private double avgSpeed;
    private Long startTime;
    private Long stopTime;
    private Long time;
    private double distance;
    @Generated(hash = 1729753035)
    public traceInfo(String date, String stuID, String latitude, String lontitude,
            double speed, double avgSpeed, Long startTime, Long stopTime, Long time,
            double distance) {
        this.date = date;
        this.stuID = stuID;
        this.latitude = latitude;
        this.lontitude = lontitude;
        this.speed = speed;
        this.avgSpeed = avgSpeed;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.time = time;
        this.distance = distance;
    }
    @Generated(hash = 594904309)
    public traceInfo() {
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStuID() {
        return this.stuID;
    }
    public void setStuID(String stuID) {
        this.stuID = stuID;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLontitude() {
        return this.lontitude;
    }
    public void setLontitude(String lontitude) {
        this.lontitude = lontitude;
    }
    public double getSpeed() {
        return this.speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getAvgSpeed() {
        return this.avgSpeed;
    }
    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }
    public Long getStartTime() {
        return this.startTime;
    }
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    public Long getStopTime() {
        return this.stopTime;
    }
    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    public double getDistance() {
        return this.distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

}
