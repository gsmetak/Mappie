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
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import ds.mappie.R;
import ds.mappie.activities.MainActivity;
import ds.mappie.dialogs.YesNoDialog;
import ds.mappie.models.MapReduce;
import ds.mappie.models.MapRef;
import ds.mappie.models.ReduceRef;
import ds.mappie.tasks.EstablishConnection;


public class MRHandler extends AppCompatActivity implements YesNoDialog.DialogListener {
    public static List<MapReduce> addresses = new ArrayList<>();
    public static List<MapReduce> mapRed = new ArrayList<>();
    public String selected = "";
    public static int countR = 0, countM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_address);

        ((TextView) findViewById(R.id.textView7)).setText(String.valueOf(countM));
        ((TextView) findViewById(R.id.textView9)).setText(String.valueOf(countR));

        Button ok = (Button) findViewById(R.id.button3);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAd();
            }
        });

        Button reset = (Button) findViewById(R.id.button4);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
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
                MapReduce m = new MapRef(address, Integer.parseInt(port));
                addresses.add(m);
                new EstablishConnection(getApplicationContext()).execute(m);
                break;
            case "Reducer":
                MapReduce r = new ReduceRef(address, Integer.parseInt(port));

                if (countR == 0) {
                    addresses.add(r);
                    new EstablishConnection(getApplicationContext()).execute(r);
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

        if (countR != 1 || countM < 3) {
            Toast.makeText(getApplicationContext(), "Add only 1 Reducer and >=3 Mappers.", Toast.LENGTH_LONG).show();
            init();
        } else {
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }
    }

    private void clear(){
        addresses.clear();
        mapRed.clear();
        countM = 0;
        countR = 0;
        init();
    }



    public Context getC() {
        return getApplicationContext();
    }
}
