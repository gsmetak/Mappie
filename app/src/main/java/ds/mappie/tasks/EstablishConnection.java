package ds.mappie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ds.mappie.dialogs.YesNoDialog;
import ds.mappie.services.MRHandler;

/**
 * Created by George-PC on 10-Jun-16.
 */
public class EstablishConnection extends AsyncTask<String, Void, Boolean> {
    public Context c;

    public EstablishConnection(Context c){
        this.c = c;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            Socket requestSocket = new Socket(InetAddress.getByName(params[0]), Integer.parseInt(params[1]));
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    protected void onPostExecute(Boolean result) {
        Toast.makeText(c, result.toString(), Toast.LENGTH_LONG).show();

    }
}
