package ds.mappie.tasks;

import android.content.Intent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ds.mappie.activities.ResultsActivity;
import ds.mappie.models.CheckIn;
import ds.mappie.services.MappieResources;

/**
 * Created by George-PC on 11-Jun-16.
 */
public class WaitResults implements Runnable {
    public static Object wait;

    @Override
    public void run() {
        ServerSocket appSocket = null;
        Socket connection;

        try {
            appSocket = new ServerSocket(0);
            ResultsActivity.port.setText(String.valueOf(appSocket.getLocalPort()));
            ResultsActivity.ip.setText(appSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                connection = appSocket.accept();

                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

                in.readObject(); //Reducer is ready.

                ResultsActivity.res.setText("Reducer ready!");
                ResultsActivity.reduce.setEnabled(true);

                wait.wait();

                MappieResources.checkIns = ((HashMap<CheckIn, Integer>)in.readObject());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
