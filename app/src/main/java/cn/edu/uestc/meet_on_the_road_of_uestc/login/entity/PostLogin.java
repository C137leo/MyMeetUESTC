package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;

public class PostLogin {

    private String name;
    private String mpassword;

    public PostLogin(String name, String mpassword) {
        this.name = name;
        this.mpassword = mpassword;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }
}
