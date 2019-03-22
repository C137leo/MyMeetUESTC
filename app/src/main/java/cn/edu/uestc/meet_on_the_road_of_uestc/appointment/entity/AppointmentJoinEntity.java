package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity;

public class AppointmentJoinEntity {
    String appointmentUID;
    private String StuID; //学号
    private String StuName; //姓名
    private String StuPassWord; //密码
    private String StuSignature; //个性签名
    private int StuGrade; //年级
    private String NickName; //昵称
    private String major;

    public AppointmentJoinEntity(String appointmentUID, String stuID, String stuName, String stuPassWord, String stuSignature, int stuGrade, String nickName, String major) {
        this.appointmentUID = appointmentUID;
        StuID = stuID;
        StuName = stuName;
        StuPassWord = stuPassWord;
        StuSignature = stuSignature;
        StuGrade = stuGrade;
        NickName = nickName;
        this.major = major;
    }

    public String getAppointmentUID() {
        return appointmentUID;
    }

    public void setAppointmentUID(String appointmentUID) {
        this.appointmentUID = appointmentUID;
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

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
