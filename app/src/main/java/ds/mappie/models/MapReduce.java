package ds.mappie.models;

import java.net.Socket;

public class MapReduce {
    public String ip;
    public int port;
    public Socket requestSocket;

    MapReduce(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}

