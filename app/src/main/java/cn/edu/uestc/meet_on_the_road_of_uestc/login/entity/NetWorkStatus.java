package cn.edu.uestc.meet_on_the_road_of_uestc.login.entity;


public class NetWorkStatus {

    /**
     * code : {"errcode":100,"errmsg":"登陆成功"}
     * info : {"StuID":"","StuName":"电子科技大学","StuGrade":"0","StuPassWord":"123456","StuSignature":"","NickName":""}
     */

    private CodeBean code;
    private InfoBean info;

    public CodeBean getCode() {
        return code;
    }

    public void setCode(CodeBean code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class CodeBean {
        /**
         * errcode : 100
         * errmsg : 登陆成功
         */

        private int errcode;
        private String errmsg;

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

    public static class InfoBean {
        /**
         * StuID :
         * StuName : 电子科技大学
         * StuGrade : 0
         * StuPassWord : 123456
         * StuSignature :
         * NickName :
         */

        private String StuID;
        private String StuName;
        private int StuGrade;
        private String StuPassWord;
        private String StuSignature;
        private String NickName;

        public String getStuID() {
            return StuID;
        }

        public void setStuID(String StuID) {
            this.StuID = StuID;
        }

        public String getStuName() {
            return StuName;
        }

        public void setStuName(String StuName) {
            this.StuName = StuName;
        }

        public int getStuGrade() {
            return StuGrade;
        }

        public void setStuGrade(int StuGrade) {
            this.StuGrade = StuGrade;
        }

        public String getStuPassWord() {
            return StuPassWord;
        }

        public void setStuPassWord(String StuPassWord) {
            this.StuPassWord = StuPassWord;
        }

        public String getStuSignature() {
            return StuSignature;
        }

        public void setStuSignature(String StuSignature) {
            this.StuSignature = StuSignature;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }
    }
}
