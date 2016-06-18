package ds.mappie.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ds.mappie.dialogs.DatePicker;
import ds.mappie.R;
import ds.mappie.services.MRHandler;
import ds.mappie.services.MappieResources;
import ds.mappie.models.Request;
import ds.mappie.tasks.SendTask;

public class MainActivity extends AppCompatActivity {
    MappieResources app;
    DatePicker dTo, dFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MappieResources) getApplication();

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

        Button from = (Button) findViewById(R.id.button6);

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dFrom = new DatePicker();
                dFrom.show(getFragmentManager(), "from");
            }
        });

        Button to = (Button) findViewById(R.id.button8);

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dTo = new DatePicker();
                dTo.show(getFragmentManager(), "to");
            }
        });
    }

    public void showMaps() {
        Intent k = new Intent(this, MapsActivity.class);
        startActivity(k);
    }

    public void sendData() {
        int topK = Integer.parseInt(((EditText) findViewById(R.id.editText6)).getText().toString());
        int size = MRHandler.mappers.size();
        double longLength = app.getMaxLong() - app.getMinLong();
        double newLength = longLength / size;
        Request[] requests = new Request[size];

        for (int i = 0; i < size; i++) {
            requests[i] = new Request(app.getMinLat(), app.getMaxLat(), app.getMinLong() + i * newLength, app.getMinLong() + (i + 1) * newLength, dFrom.toString(), dTo.toString(), topK);
        }

        new SendTask().execute(requests);

    }


}
