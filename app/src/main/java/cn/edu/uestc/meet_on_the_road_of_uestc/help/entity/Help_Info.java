package cn.edu.uestc.meet_on_the_road_of_uestc.help.entity;

public class Help_Info {
    private String distance;
    private String owner_name;
    private String good_title;
    private String publish_time;

    public Help_Info(String owner_name,String good_title,String publish_time,String distance){
        this.distance=distance;
        this.owner_name=owner_name;
        this.good_title=good_title;
        this.publish_time=publish_time;
    }
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getGood_title() {
        return good_title;
    }

    public void setGood_title(String good_title) {
        this.good_title = good_title;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
}
