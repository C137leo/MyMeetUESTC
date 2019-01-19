package cn.edu.uestc.meet_on_the_road_of_uestc.Login.bean;

public class registerInfo {
    private String registerAccount;
    private String registerName;
    private String registerStuID;
    private String registerPassword;
    public registerInfo(String registerAccount,String registerName,String registerStuID,String registerPassword){
        this.registerAccount=registerAccount;
        this.registerName=registerName;
        this.registerPassword=registerPassword;
        this.registerStuID=registerStuID;
    }

    public String getRegisterAccount() {
        return registerAccount;
    }

    public void setRegisterAccount(String registerAccount) {
        this.registerAccount = registerAccount;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterStuID() {
        return registerStuID;
    }

    public void setRegisterStuID(String registerStuID) {
        this.registerStuID = registerStuID;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }
}
