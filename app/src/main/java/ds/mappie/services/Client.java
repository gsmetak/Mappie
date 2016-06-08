package ds.mappie.services;

import java.io.*;
import java.net.*;


public class Client {

    public static void main(String[] args) {

        new Client().startClient();

    }

    public void startClient() {
        Socket requestSocket = null;
        ObjectOutputStream out = null, out2 = null, out3 = null;
        int topK;
        double minLat, maxLat, minLong, maxLong, longLength, newLength;
        String minTime, maxTime;
        BufferedReader br;

        try {


            //Assign values for Request.
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter minimun latitude: ");
            minLat = Double.parseDouble(br.readLine());
            System.out.println("Enter maximum latitude: ");
            maxLat = Double.parseDouble(br.readLine());
            System.out.println("Enter minimun longtitude: ");
            minLong = Double.parseDouble(br.readLine());
            System.out.println("Enter maximum longtitude: ");
            maxLong = Double.parseDouble(br.readLine());

            System.out.println("Enter date to search from: ");
            minTime = br.readLine();
            System.out.println("Enter date to search to: ");
            maxTime = br.readLine();

            System.out.println("Enter how many popular locations to see: ");
            topK = Integer.parseInt(br.readLine());

            longLength = maxLong - minLong;
            newLength = longLength / 3;

            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65008);

            out = new ObjectOutputStream(requestSocket.getOutputStream());

            out.writeObject(new Request(minLat, maxLat, minLong, minLong + newLength, minTime, maxTime, topK));
            out.flush();

            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65034);

            out2 = new ObjectOutputStream(requestSocket.getOutputStream());

            out2.writeObject(new Request(minLat, maxLat, minLong + newLength, minLong + 2*newLength, minTime, maxTime, topK));
            out2.flush();

            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), 65063);
            out3 = new ObjectOutputStream(requestSocket.getOutputStream());

            out3.writeObject(new Request(minLat, maxLat, minLong + 2*newLength, maxLong, minTime, maxTime, topK));
            out3.flush();


        } catch (UnknownHostException e) {
            System.err.println("Error");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                requestSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
