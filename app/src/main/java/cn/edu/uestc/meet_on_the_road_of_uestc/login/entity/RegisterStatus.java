package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;

import java.lang.ref.PhantomReference;

public class RegisterStatus {

    private int scode;
    private String smsg;

    public RegisterStatus(int scode, String smsg) {
        this.scode = scode;
        this.smsg = smsg;
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
}
