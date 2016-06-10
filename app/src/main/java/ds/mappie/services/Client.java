//package ds.mappie.services;
//
//import java.io.*;
//import java.net.*;
//
//
//public class Client {
//
//    public static void main(String[] args) {
//
//        new Client().startClient();
//
//    }
//
//    public void startClient() {
//        Socket requestSocket = null;
//        ObjectOutputStream out = null, out2 = null, out3 = null;
//        int topK;
//        double minLat, maxLat, minLong, maxLong, longLength, newLength;
//        String minTime, maxTime;
//        BufferedReader br;
//
//        try {
//
//
//
//            longLength = maxLong - minLong;
//            newLength = longLength / 3;
//
//            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65008);
//
//            out = new ObjectOutputStream(requestSocket.getOutputStream());
//
//            out.writeObject(new Request(minLat, maxLat, minLong, minLong + newLength, minTime, maxTime, topK));
//            out.flush();
//
//            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65034);
//
//            out2 = new ObjectOutputStream(requestSocket.getOutputStream());
//
//            out2.writeObject(new Request(minLat, maxLat, minLong + newLength, minLong + 2*newLength, minTime, maxTime, topK));
//            out2.flush();
//
//            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65063);
//            out3 = new ObjectOutputStream(requestSocket.getOutputStream());
//
//            out3.writeObject(new Request(minLat, maxLat, minLong + 2*newLength, maxLong, minTime, maxTime, topK));
//            out3.flush();
//
//
//        } catch (UnknownHostException e) {
//            System.err.println("Error");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.close();
//                requestSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
