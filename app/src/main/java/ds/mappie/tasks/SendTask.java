package ds.mappie.tasks;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ds.mappie.activities.MainActivity;
import ds.mappie.services.MRHandler;
import ds.mappie.services.Request;


public class SendTask extends AsyncTask<Request, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Request... arg0) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(MRHandler.mapRed.get(0).requestSocket.getOutputStream());
            out.writeUnshared(arg0[0]);
        } catch (IOException e) {
            return false;
        }

        return true;

    }

    @Override
    protected void onPostExecute(Boolean result) {

    }

}


