package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath;

public class Path {
    private String name;
    private int imageId;
    public Path(String name,int imageId){

        this.name=name;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
