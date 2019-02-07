package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;

import java.lang.ref.PhantomReference;

public class RegisterStatus {

    private int errcode;
    private String errmsg;

    public RegisterStatus(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
