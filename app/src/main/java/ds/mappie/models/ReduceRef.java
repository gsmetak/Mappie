package ds.mappie.models;

public class ReduceRef extends MapReduce {
    public ReduceRef(String ip, int port) {
        super(ip, port);
    }

    @Override
    public String toString(){
        return ip + ":" + String.valueOf(port);
    }
}
