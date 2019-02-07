package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;


public class NetWorkStatus {
    private int scode;
    private String smsg;
    private String StuID; //学号(主键)
    private String StuName; //姓名
    private String StuPassWord; //密码
    private String StuSignature; //个性签名
    private int StuGrade; //年级
    private String NickName; //昵称

    public NetWorkStatus(int scode, String smsg, String stuID, String stuName, String stuPassWord, String stuSignature, int stuGrade, String nickName) {
        this.scode = scode;
        this.smsg = smsg;
        StuID = stuID;
        StuName = stuName;
        StuPassWord = stuPassWord;
        StuSignature = stuSignature;
        StuGrade = stuGrade;
        NickName = nickName;
    }

    public int getScode() {
        return scode;
    }

    public void setScode(int scode) {
        this.scode = scode;
    }

    public String getSmsg() {
        return smsg;
    }

    public void setSmsg(String smsg) {
        this.smsg = smsg;
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
}
