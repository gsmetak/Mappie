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

import java.io.IOException;
import java.io.ObjectOutputStream;
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
    public static List<MapRef> mappers = new ArrayList<>();
    public static ReduceRef reducer = null;
    public String selected = "";
    public MappieResources app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_address);

        ((TextView) findViewById(R.id.textView7)).setText(String.valueOf(mappers.size()));
        if (reducer == null)
            ((TextView) findViewById(R.id.textView9)).setText(String.valueOf(0));
        else
            ((TextView) findViewById(R.id.textView9)).setText(String.valueOf(1));

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
                new EstablishConnection(getApplicationContext()).execute(m);
                break;
            case "Reducer":
                MapReduce r = new ReduceRef(address, Integer.parseInt(port));
                if (reducer == null) {
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

        if (reducer == null ) {
            Toast.makeText(getApplicationContext(), "Add only 1 Reducer and >=3 Mappers.", Toast.LENGTH_LONG).show();
            init();
        } else {
            app = (MappieResources) getApplication();
            addOuts();

            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < app.getOuts().size(); i++)
                        try {
                            app.getOuts().get(i).writeUTF(reducer.toString());
                            new ObjectOutputStream(MRHandler.reducer.requestSocket.getOutputStream()).writeInt(MRHandler.mappers.size());
                        } catch (IOException e) {
                        }
                }
            }.start();

            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }
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

    private void clear() {
        mappers.clear();
        reducer = null;
        init();
    }


    public Context getC() {
        return getApplicationContext();
    }
}
