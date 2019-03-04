package cn.edu.uestc.meet_on_the_road_of_uestc.appointment.appointmentAdd.entity;

public class TimeMessage {

    String selectTime;
    String date;

    public TimeMessage(String selectTime, String date) {
        this.selectTime = selectTime;
        this.date = date;
    }

    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
