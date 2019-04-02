package cn.edu.uestc.meet_on_the_road_of_uestc.choosepath;

public class Path {
    private String pathName;
    private int pathId;
//    boolean active;
    private String lastRunDate;
    private double pathLatitude;
    private double pathLongtitude;

    public double getPathLatitude() {
        return pathLatitude;
    }

    public void setPathLatitude(double pathLatitude) {
        this.pathLatitude = pathLatitude;
    }

    public double getPathLongtitude() {
        return pathLongtitude;
    }

    public void setPathLongtitude(double pathLongtitude) {
        this.pathLongtitude = pathLongtitude;
    }

    public Path(String pathName, int pathId, double pathLatitude, double pathLongtitude) {
        this.pathName = pathName;
        this.pathId = pathId;
        this.pathLatitude = pathLatitude;
        this.pathLongtitude = pathLongtitude;
    }

    public Path(String pathName, int pathId, String lastRunDate){
        this.pathName=pathName;
        this.pathId=pathId;
//        this.lastRunDate=LastRunDate;
//      this.active=active;
    }
    public Path(String pathName,int pathId){
        this.pathName=pathName;
        this.pathId=pathId;
    }
    public String getPathName() {
        return pathName;
    }

    public int getPathId() {
        return pathId;
    }

    public String getLastRunDate() {
        return lastRunDate;
    }
//    为后面复杂例子准备的。
//    public boolean isActive() {
//        return active;
//    }
}
