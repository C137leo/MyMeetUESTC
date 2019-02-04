package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.DaoSession;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.traceInfoDao;
import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.StuInfoDao;


@Entity
public class StuInfo {


    @Id(autoincrement = false)
    private String StuID;
    @Property
    private String StuName;
    private String StuPassWord;
    private String StuSignature;
    private int StuGrade;
    private double mLatitude;
    private double mLontitude;
    @Generated(hash = 104385949)
    public StuInfo(String StuID, String StuName, String StuPassWord,
            String StuSignature, int StuGrade, double mLatitude,
            double mLontitude) {
        this.StuID = StuID;
        this.StuName = StuName;
        this.StuPassWord = StuPassWord;
        this.StuSignature = StuSignature;
        this.StuGrade = StuGrade;
        this.mLatitude = mLatitude;
        this.mLontitude = mLontitude;
    }
    @Generated(hash = 724382685)
    public StuInfo() {
    }
    public String getStuID() {
        return this.StuID;
    }
    public void setStuID(String StuID) {
        this.StuID = StuID;
    }
    public String getStuName() {
        return this.StuName;
    }
    public void setStuName(String StuName) {
        this.StuName = StuName;
    }
    public String getStuPassWord() {
        return this.StuPassWord;
    }
    public void setStuPassWord(String StuPassWord) {
        this.StuPassWord = StuPassWord;
    }
    public String getStuSignature() {
        return this.StuSignature;
    }
    public void setStuSignature(String StuSignature) {
        this.StuSignature = StuSignature;
    }
    public int getStuGrade() {
        return this.StuGrade;
    }
    public void setStuGrade(int StuGrade) {
        this.StuGrade = StuGrade;
    }
    public double getMLatitude() {
        return this.mLatitude;
    }
    public void setMLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }
    public double getMLontitude() {
        return this.mLontitude;
    }
    public void setMLontitude(double mLontitude) {
        this.mLontitude = mLontitude;
    }
}
