package ds.mappie.tasks;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ds.mappie.activities.ResultsActivity;
import ds.mappie.models.POI;
import ds.mappie.services.MappieResources;

/**
 * Created by George-PC on 11-Jun-16.
 */
public class WaitResults implements Runnable {
    public static Object wait = new Object();

    @Override
    public void run() {
        ServerSocket appSocket = null;
        Socket connection;


        try {


            Socket con = MappieResources.serverSocket.accept();

            ObjectInputStream in = new ObjectInputStream(con.getInputStream());
            MappieResources.serIn = in;

            in.readInt(); //Reducer is ready.

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
