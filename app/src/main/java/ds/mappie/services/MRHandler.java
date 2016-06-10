package ds.mappie.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ds.mappie.R;
import ds.mappie.activities.MainActivity;
import ds.mappie.dialogs.YesNoDialog;
import ds.mappie.tasks.EstablishConnection;


public class MRHandler extends AppCompatActivity implements YesNoDialog.DialogListener {
    public static List<MapReduce> addresses = new ArrayList<>();
    public String selected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_address);

        Button ok = (Button) findViewById(R.id.button3);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAd();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                selected = item;
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        List<String> mr = new ArrayList<>();
        mr.add("Mapper");
        mr.add("Reducer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mr);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    private void addAd() {
        String address = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String port = ((EditText) findViewById(R.id.editText5)).getText().toString();

        switch (selected) {
            case "Mapper":
                addresses.add(new MapRef(address, Integer.parseInt(port)));
                new EstablishConnection(getApplicationContext()).execute(address, port);
                break;
            case "Reducer":
                boolean exists = false;
                for (MapReduce mr : addresses) {
                    if (mr instanceof ReduceRef) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    addresses.add(new ReduceRef(address, Integer.parseInt(port)));
                } else {
                    Toast.makeText(getApplicationContext(), "There is a reducer already.", Toast.LENGTH_LONG).show();
                    init();
                }
                break;
        }
        showDialog();

    }

    public void showDialog() {
        YesNoDialog more = new YesNoDialog();
        Bundle tmp = new Bundle();
        tmp.putString("title", "Do you want to add another one?");
        tmp.putString("yes", "Yes");
        tmp.putString("no", "No");
        more.setArguments(tmp);
        more.show(getSupportFragmentManager(), "more");
    }

    @Override
    public void onDialogPositiveClick(android.support.v4.app.DialogFragment dialog) {
        init();
    }

    @Override
    public void onDialogNegativeClick(android.support.v4.app.DialogFragment dialog) {
        boolean exists = false;
        for (MapReduce mr : addresses) {
            if (mr instanceof ReduceRef) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            Toast.makeText(getApplicationContext(), "You must add 1 Reducer.", Toast.LENGTH_LONG).show();
            init();
        } else {
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }
    }


    private class MapReduce {
        public String ip;
        public int port;

        MapReduce(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }
    }

    private class MapRef extends MapReduce {
        MapRef(String ip, int port) {
            super(ip, port);
        }

    }

    private class ReduceRef extends MapReduce {
        ReduceRef(String ip, int port) {
            super(ip, port);
        }
    }

    public Context getC() {
        return getApplicationContext();
    }
}
