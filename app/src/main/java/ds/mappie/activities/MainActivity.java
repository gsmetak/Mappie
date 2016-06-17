package ds.mappie.activities;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ds.mappie.R;
import ds.mappie.models.MapReduce;
import ds.mappie.models.ReduceRef;
import ds.mappie.services.MRHandler;
import ds.mappie.services.MappieResources;
import ds.mappie.services.Request;
import ds.mappie.tasks.SendTask;

public class MainActivity extends AppCompatActivity {
    MappieResources app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MappieResources) getApplication();
        addOuts();

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

        if (app.hasGeo()) {
            send.setEnabled(true);
        }
    }

    public void showMaps() {
        Intent k = new Intent(this, MapsActivity.class);
        startActivity(k);
    }

    public void sendData() {
        String midDate = ((EditText) findViewById(R.id.textView10)).getText().toString();
        String maxDate = ((EditText) findViewById(R.id.textView13)).getText().toString();
        int topK = Integer.parseInt(((EditText) findViewById(R.id.editText6)).getText().toString());
        int size = MRHandler.mappers.size();
        double longLength = app.getMaxLong() - app.getMinLong();
        double newLength = longLength / size;
        Request[] requests = new Request[size];

        for (int i = 0; i < size; i++) {
            requests[i] = new Request(app.getMinLat(), app.getMaxLat(), app.getMinLong() + i * newLength, app.getMinLong() + (i + 1) * newLength, midDate, maxDate, topK);
        }

        new SendTask().execute(requests);

    }

    public void addOuts() {
        ArrayList<ObjectOutputStream> outs = new ArrayList<ObjectOutputStream>();

        for (int i = 0; i < MRHandler.mappers.size(); i++) {
            try {
                outs.add(new ObjectOutputStream(MRHandler.mappers.get(i).requestSocket.getOutputStream()));
            } catch (IOException e) {
            }
        }

        app.setOuts(outs);

    }
}
