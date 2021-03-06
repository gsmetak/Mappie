package ds.mappie.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ds.mappie.R;
import ds.mappie.models.POI;
import ds.mappie.services.MappieResources;
import ds.mappie.tasks.GetPhoto;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private HashMap<Marker, ArrayList<String>> markers = new HashMap<>();
    private MarkerOptions options = new MarkerOptions();
    LatLng tmp = new LatLng(40.719810375488535, -74.00258103213994);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(tmp));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(9), 2000, null);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng topLeft = mMap.getProjection().getVisibleRegion().farLeft;
                LatLng botRight = mMap.getProjection().getVisibleRegion().nearRight;
                ((MappieResources) getApplication()).setGeo(topLeft, botRight);

                Intent k = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(k);
            }
        });

        Intent i = getIntent();
        if (i.hasExtra("results")) {
            button.setEnabled(false);


            for (POI p : ((MappieResources) getApplication()).checkIns.values()) {
                options.position(new LatLng(p.getLatitude(), p.getLongitude())).title(p.getPOI_name()).snippet(p.getNoOfCheckins() + " check ins have been made here.");
                markers.put(mMap.addMarker(options), p.getPhotos());
            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    Intent i = new Intent(getApplicationContext(), PhotoView.class);
                    i.putStringArrayListExtra("photos", markers.get(marker));
                    i.putExtra("name", marker.getTitle());
                    i.putExtra("check", marker.getSnippet());

                    startActivity(i);

                    return true;
                }
            });

        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


    }
}
