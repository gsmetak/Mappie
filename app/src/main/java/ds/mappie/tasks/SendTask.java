package ds.mappie.tasks;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import ds.mappie.activities.MainActivity;
import ds.mappie.models.ReduceRef;
import ds.mappie.services.MRHandler;
import ds.mappie.services.MappieResources;
import ds.mappie.services.Request;


public class SendTask extends AsyncTask<Request[], Void, Boolean> {

    @Override
    protected Boolean doInBackground(Request[]... requests) {

        for (int i = 0; i < MappieResources.outs.size(); i++)
            try {
                MappieResources.outs.get(i).writeUnshared( (requests[0])[i]);
                MappieResources.outs.get(i).flush();
            } catch (IOException e) {
                return false;
            }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {

    }

}


