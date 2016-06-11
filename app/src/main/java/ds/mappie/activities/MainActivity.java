package ds.mappie.activities;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;
import java.util.ArrayList;

import ds.mappie.R;
import ds.mappie.models.MapReduce;
import ds.mappie.services.MappieResources;
import ds.mappie.services.Request;
import ds.mappie.tasks.SendTask;

public class MainActivity extends AppCompatActivity {
    double minLat, minLong, maxLat, maxLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        MappieResources app = (MappieResources) getApplication();

        if (app.hasGeo()) {
            minLat = app.getMinLat();
            maxLat = app.getMaxLat();
            minLong = app.getMinLong();
            maxLong = app.getMaxLong();
        }
    }

    public void showMaps() {
        Intent k = new Intent(this, MapsActivity.class);
        startActivity(k);
    }

    public void sendData() {

        Request r = new Request(minLat, maxLat, minLong, maxLong, ((EditText)findViewById(R.id.textView10)).getText().toString(), ((EditText)findViewById(R.id.textView13)).getText().toString(), Integer.parseInt(((EditText)findViewById(R.id.editText6)).getText().toString()));
        new SendTask().execute(r);

    }

}
