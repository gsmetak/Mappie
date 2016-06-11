package ds.mappie.tasks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by George-PC on 11-Jun-16.
 */
public class WaitResults implements Runnable {
    @Override
    public void run() {
        ServerSocket appSocket = null;
        Socket connection;

        try {
            appSocket = new ServerSocket(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                connection = appSocket.accept();

                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

                String ready = in.readUTF();

                //TODO Enable button, check string, read results, print results


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
