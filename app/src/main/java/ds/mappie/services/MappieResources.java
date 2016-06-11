package ds.mappie.services;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by George-PC on 11-Jun-16.
 */
public class MappieResources extends Application {
    LatLng topLeft;
    LatLng botRight;
    boolean hasGeo = false;
    double minLat = 0.0, minLong = 0.0, maxLat = 0.0, maxLong = 0.0;

    public LatLng getTopLeft() {
        return topLeft;
    }

    public void setGeo(LatLng topLeft, LatLng botRight) {
        maxLat = topLeft.latitude;
        minLong = topLeft.longitude;
        minLat = botRight.latitude;
        maxLong = botRight.longitude;
        hasGeo = true;
    }

    public LatLng getBotRight() {
        return botRight;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMinLong() {
        return minLong;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMaxLong() {
        return maxLong;
    }

    public boolean hasGeo() {
        return hasGeo;
    }
}
