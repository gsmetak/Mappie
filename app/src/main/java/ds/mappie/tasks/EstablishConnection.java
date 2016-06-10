package ds.mappie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ds.mappie.activities.MainActivity;
import ds.mappie.dialogs.YesNoDialog;
import ds.mappie.models.MapReduce;
import ds.mappie.models.ReduceRef;
import ds.mappie.services.MRHandler;

/**
 * Created by George-PC on 10-Jun-16.
 */
public class EstablishConnection extends AsyncTask<MapReduce, Void, Boolean> {
    public Context c;

    public EstablishConnection(Context c) {
        this.c = c;
    }

    @Override
    protected Boolean doInBackground(MapReduce... params) {
        Socket requestSocket;
        try {
            requestSocket = new Socket(InetAddress.getByName(params[0].ip), params[0].port);

        } catch (IOException e) {
            return false;
        }

        params[0].requestSocket = requestSocket;
        MRHandler.mapRed.add(params[0]);

        if (params[0] instanceof ReduceRef)
            MRHandler.countR++;
        else
            MRHandler.countM++;

        return true;
    }

    protected void onPostExecute(Boolean result) {
        if (result)
            Toast.makeText(c, "Connection was successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(c, "Connection was unsuccessful, try again.", Toast.LENGTH_LONG).show();
    }
}
