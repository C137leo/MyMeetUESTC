package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class traceInfo {

    @Id(autoincrement = true)
    private Long time;
    @Property
    private Long stuID;
    private Long latitude;
    private Long lontitude;
    private Long speed;
    private Long avgSpeed;
    @Generated(hash = 1417715568)
    public traceInfo(Long time, Long stuID, Long latitude, Long lontitude,
            Long speed, Long avgSpeed) {
        this.time = time;
        this.stuID = stuID;
        this.latitude = latitude;
        this.lontitude = lontitude;
        this.speed = speed;
        this.avgSpeed = avgSpeed;
    }
    @Generated(hash = 594904309)
    public traceInfo() {
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    public Long getStuID() {
        return this.stuID;
    }
    public void setStuID(Long stuID) {
        this.stuID = stuID;
    }
    public Long getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
    public Long getLontitude() {
        return this.lontitude;
    }
    public void setLontitude(Long lontitude) {
        this.lontitude = lontitude;
    }
    public Long getSpeed() {
        return this.speed;
    }
    public void setSpeed(Long speed) {
        this.speed = speed;
    }
    public Long getAvgSpeed() {
        return this.avgSpeed;
    }
    public void setAvgSpeed(Long avgSpeed) {
        this.avgSpeed = avgSpeed;
    }
}
