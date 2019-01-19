package cn.edu.uestc.meet_on_the_road_of_uestc.navigation.adapter;

public class traceTime {
    private String IMEI;
    private long startTime;
    private long stopTime;
    public traceTime(String IMEI,long startTime,long stopTime){
        this.IMEI=IMEI;
        this.startTime=startTime;
        this.stopTime=stopTime;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
