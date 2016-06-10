package ds.mappie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.Socket;
import java.util.ArrayList;

import ds.mappie.R;
import ds.mappie.models.MapReduce;
import ds.mappie.services.Request;
import ds.mappie.tasks.SendTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMaps();
            }
        });

        Button send = (Button) findViewById(R.id.button2);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    public void showMaps(){
        Intent k = new Intent(this, MapsActivity.class);
        startActivity(k);
    }

    public void sendData(){
//        String min, max;
//        double minLat, minLong, maxLat, maxLong;
//        String[] mins, maxs;
//
//        min = ((EditText) findViewById(R.id.editText)).getText().toString();
//        max = ((EditText) findViewById(R.id.editText4)).getText().toString();
//
//        mins = min.trim().split(":");
//        maxs = max.trim().split(":");
//
//        minLat = Double.parseDouble(mins[0]);
//        minLong = Double.parseDouble(mins[1]);
//        maxLat = Double.parseDouble(maxs[0]);
//        maxLong = Double.parseDouble(maxs[1]);

        Request r = new Request(15.0, 15.0, 16.0, 16.0, "13:00", "15:00", 5);
        new SendTask().execute(r);

    }

}
