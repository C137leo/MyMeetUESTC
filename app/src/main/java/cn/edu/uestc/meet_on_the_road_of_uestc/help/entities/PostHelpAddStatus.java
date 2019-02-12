package cn.edu.uestc.meet_on_the_road_of_uestc.help.entities;

public class PostHelpAddStatus {

    private int errcode;
    private String errmsg;

    public PostHelpAddStatus(int errcode, String errmsg) {
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
