package ds.mappie.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ds.mappie.R;
import ds.mappie.tasks.WaitResults;

public class ResultsActivity extends AppCompatActivity {

    public static TextView res, ip, port;
    public static Button reduce;
    public static Object results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        res = (TextView) findViewById(R.id.textView15);
        port = (TextView) findViewById(R.id.textView10);
        ip = (TextView) findViewById(R.id.textView12);
        reduce = (Button) findViewById(R.id.button5);

        new Thread(new WaitResults()).start();

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaitResults.wait.notify();
                Toast.makeText(getApplicationContext(), "Please wait for the results.", Toast.LENGTH_LONG).show();
                try {
                    results.wait();
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
}
