package ds.mappie.activities;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import ds.mappie.R;
import ds.mappie.models.POI;
import ds.mappie.services.MappieResources;
import ds.mappie.tasks.WaitResults;

public class ResultsActivity extends AppCompatActivity {

    public TextView res;
    public Button reduce;
    public static Object results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        res = (TextView) findViewById(R.id.textView15);
        reduce = (Button) findViewById(R.id.button5);

        Thread t = new Thread(new WaitResults());
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        changeText();

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Please wait for the results.", Toast.LENGTH_LONG).show();

                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try {
                            MappieResources.outs.get(MappieResources.outs.size()-1).writeInt(1);
                            MappieResources.outs.get(MappieResources.outs.size()-1).flush();

                            MappieResources.checkIns = ((HashMap<String, POI>) MappieResources.serIn.readObject());

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                showMapWithResults();
            }
        });


    }

    private void showMapWithResults() {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("results", true);
        startActivity(i);
    }

    public void changeText(){
        ((TextView) findViewById(R.id.textView15)).setText("Reducer ready!");
        ((Button) findViewById(R.id.button5)).setEnabled(true);
    }

}
