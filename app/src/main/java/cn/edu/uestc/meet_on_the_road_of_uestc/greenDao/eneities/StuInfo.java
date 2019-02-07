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
    private String StuID; //学号(主键)
    @Property
    private String StuName; //姓名
    private String StuPassWord; //密码
    private String StuSignature; //个性签名
    private int StuGrade; //年级
    private String NickName; //昵称
    @Generated(hash = 684544900)
    public StuInfo(String StuID, String StuName, String StuPassWord,
            String StuSignature, int StuGrade, String NickName) {
        this.StuID = StuID;
        this.StuName = StuName;
        this.StuPassWord = StuPassWord;
        this.StuSignature = StuSignature;
        this.StuGrade = StuGrade;
        this.NickName = NickName;
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
    public String getNickName() {
        return this.NickName;
    }
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
}
