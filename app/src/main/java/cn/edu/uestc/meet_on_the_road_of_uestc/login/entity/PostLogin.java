package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;

public class PostLogin {

    private String username;
    private String mpassword;

    public PostLogin(String username, String mpassword) {
        this.username = username;
        this.mpassword = mpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }
}
