package ds.mappie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import ds.mappie.models.MapReduce;
import ds.mappie.models.MapRef;
import ds.mappie.models.ReduceRef;
import ds.mappie.activities.MRHandler;

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
        Socket requestSocket = new Socket();
        InetSocketAddress iNetAddress = new InetSocketAddress(params[0].ip, params[0].port);
        try {
            requestSocket.connect(iNetAddress, 15000);

        } catch (IOException e) {
            return false;
        }

        params[0].requestSocket = requestSocket;


        if (params[0] instanceof ReduceRef)
            MRHandler.reducer = ((ReduceRef) params[0]);
        else
            MRHandler.mappers.add((MapRef) params[0]);

        return true;
    }

    protected void onPostExecute(Boolean result) {
        if (result)
            Toast.makeText(c, "Connection was successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(c, "Connection was unsuccessful, try again.", Toast.LENGTH_LONG).show();
    }

}
