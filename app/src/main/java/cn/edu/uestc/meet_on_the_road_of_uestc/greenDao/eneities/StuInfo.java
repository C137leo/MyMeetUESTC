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
    @ToMany(referencedJoinProperty =  "stuID")
    private List<traceInfo> traceInfoList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 348921390)
    private transient StuInfoDao myDao;
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
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 418788378)
    public List<traceInfo> getTraceInfoList() {
        if (traceInfoList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            traceInfoDao targetDao = daoSession.getTraceInfoDao();
            List<traceInfo> traceInfoListNew = targetDao
                    ._queryStuInfo_TraceInfoList(StuID);
            synchronized (this) {
                if (traceInfoList == null) {
                    traceInfoList = traceInfoListNew;
                }
            }
        }
        return traceInfoList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1966413244)
    public synchronized void resetTraceInfoList() {
        traceInfoList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1041004529)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStuInfoDao() : null;
    }
}
