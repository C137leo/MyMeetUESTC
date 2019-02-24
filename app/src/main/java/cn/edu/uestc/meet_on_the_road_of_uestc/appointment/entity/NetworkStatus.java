package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.entity;

public class NetworkStatus {
    String errCode;
    String errMsg;

    public NetworkStatus(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
