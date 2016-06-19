package ds.mappie.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by George-PC on 18-Jun-16.
 */
public class POI implements Serializable {

    private String POI, POI_name;
    private double latitude, longitude;
    private ArrayList<String> photos = new ArrayList<>();
    private int noOfCheckIns = 0;
    private static final long serialVersionUID = -2424363051271966964L;

    public POI(String POI, String POI_name, double lat, double longitude, ArrayList<String> photos, int no) {
        this.POI = POI;
        this.latitude = lat;
        this.longitude = longitude;
        this.POI_name = POI_name;
        this.photos = photos;
        this.noOfCheckIns = no;
    }

    public String getPOI() {
        return POI;
    }

    public void setPOI(String POI) {
        this.POI = POI;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public int getNoOfCheckins() {
        return noOfCheckIns;
    }

    public void setNoOfCheckins(int noOfCheckIns) {
        this.noOfCheckIns = noOfCheckIns;
    }

    public void addCheckIn() {
        noOfCheckIns++;
    }

    public void addPhoto(String url) {
        photos.add(url);
    }

    public void addCheckIns(int no) {
        noOfCheckIns += no;
    }

    public void addPhotos(ArrayList<String> photos) {
        this.photos.addAll(photos);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPOI_name() {
        return POI_name;
    }

    public void setPOI_name(String POI_name) {
        this.POI_name = POI_name;
    }
}
