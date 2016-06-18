package ds.mappie.tasks;

import android.os.AsyncTask;

import java.io.IOException;

import ds.mappie.services.MappieResources;
import ds.mappie.models.Request;


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


