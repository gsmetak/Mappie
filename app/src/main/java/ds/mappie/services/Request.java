package ds.mappie.services;

import java.io.Serializable;

/**
 * Created by Socrates on 31/3/2016.
 */
public class Request implements Serializable {


    private static final long serialVersionUID = -2723363051271966964L;
    private double minLat, maxLat, minLong, maxLong;
    private String minTime, maxTime;
    private int topK;




    public Request(double minLat, double maxLat, double minLong, double maxLong, String minTime, String maxTime, int topK) {

        this.maxLong = maxLong;
        this.minLong = minLong;
        this.maxLat = maxLat;
        this.minLat = minLat;
        this.maxTime = maxTime;
        this.minTime = minTime;
        this.topK = topK;

    }

    @Override
    public String toString() {

        return ("Min Latitude: " + minLat + " Max Latitude: " + maxLat
                + " Min Longtitude: " + minLong + " Max Longtitude: " + maxLong
                + " Min Time: " + minTime + " Max Time: " + maxTime);

    }

    public double getMinLong() {
        return minLong;
    }

    public int getTopK() {
        return topK;
    }

    public void setTopK(int topK) {
        this.topK = topK;
    }

    public void setMinLong(double minLong) {
        this.minLong = minLong;
    }

    public double getMaxLong() {
        return maxLong;
    }

    public void setMaxLong(double maxLong) {
        this.maxLong = maxLong;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }


}





