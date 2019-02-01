package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;


public class Stu {
    private String StuID;
    private String StuName;
    private String StuPassWord;
    private String StuSignature;
    private int StuGrade;
    private boolean isRemember;
    private double mLatitude;
    private double mLontitude;

    public Stu(String StuID,String StuName,String StuPassWord,String StuSignature,int StuGrade,boolean isRemember,double mLatitude,double mLontitude){
        this.StuID=StuID;
        this.StuName=StuName;
        this.StuPassWord=StuPassWord;
        this.StuSignature=StuSignature;
        this.StuGrade=StuGrade;
        this.isRemember=isRemember;
        this.mLatitude=mLatitude;
        this.mLontitude=mLontitude;
    }
    public String getStuID() {
        return StuID;
    }

    public void setStuID(String stuID) {
        StuID = stuID;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public String getStuPassWord() {
        return StuPassWord;
    }

    public void setStuPassWord(String stuPassWord) {
        StuPassWord = stuPassWord;
    }

    public String getStuSignature() {
        return StuSignature;
    }

    public void setStuSignature(String stuSignature) {
        StuSignature = stuSignature;
    }

    public int getStuGrade() {
        return StuGrade;
    }

    public void setStuGrade(int stuGrade) {
        StuGrade = stuGrade;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLontitude() {
        return mLontitude;
    }

    public void setmLontitude(double mLontitude) {
        this.mLontitude = mLontitude;
    }
}
