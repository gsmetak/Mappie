package ds.mappie.models;

import java.io.Serializable;

/**
 * Created by George-PC on 30-Mar-16.
 */
public class CheckIn implements Serializable {

    private int user, POI_category_id;
    private double latitude, longitude;
    private String POI, POI_name, POI_category, dateTime, photo;
    private static final long serialVersionUID = -2426363051271966964L;

    public CheckIn(int user, int POI_category_id, double latitude, double longitude, String POI, String POI_category, String POI_name, String dateTime, String photo) {

        this.user = user;
        this.POI_category_id = POI_category_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.POI = POI;
        this.POI_name = POI_name;
        this.POI_category = POI_category;
        this.dateTime = dateTime;
        this.photo = photo;

    }

    @Override
    public String toString() {

        return (POI_name);

    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPOI_category_id() {
        return POI_category_id;
    }

    public void setPOI_category_id(int POI_category_id) {
        this.POI_category_id = POI_category_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPOI() {
        return POI;
    }

    public void setPOI(String POI) {
        this.POI = POI;
    }

    public String getPOI_name() {
        return POI_name;
    }

    public void setPOI_name(String POI_name) {
        this.POI_name = POI_name;
    }

    public String getPOI_category() {
        return POI_category;
    }

    public void setPOI_category(String POI_category) {
        this.POI_category = POI_category;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
