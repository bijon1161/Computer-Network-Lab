/*

 */
package com.mycompany.go_back_n_sliding_window_protocol;

/**
 *
 * @author shakil
 */

/*Server Program*/
import java.net.*;
import java.io.*;
import java.util.*;

public class receiver2 {

    public static void main(String args[]) throws Exception {
        ServerSocket server = new ServerSocket(6266);
        System.out.println("Server established.");
        Socket client = server.accept();
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        System.out.println("Client is now connected.");
        int x = (Integer) ois.readObject();
        int k = (Integer) ois.readObject();
        int j = 0;
        int i = (Integer) ois.readObject();
        boolean flag = true;
        Random r = new Random(6);
        int mod = r.nextInt(6);
        while (mod == 1 || mod == 0) {
            mod = r.nextInt(6);
        }
        while (true) {
            int c = k;
            for (int h = 0; h <= x; h++) {
                System.out.print("|" + c + "|");
                c = (c + 1) % x;
            }
            System.out.println();
            System.out.println();
            if (k == j) {
                System.out.println("Frame " + k + " recieved" + "\n" + "Data:" + j);
                j++;
                System.out.println();
            } else {
                System.out.println("Frames recieved not in correct order" + "\n" + " Expected farme:" + j + "\n" + " Recieved frame no :" + k);
            }
            System.out.println();
            if (j % mod == 0 && flag) {
                System.out.println("Error found. Acknowledgement not sent. ");
                flag = !flag;
                j--;
            } else if (k == j - 1) {
                oos.writeObject(k);
                System.out.println("Acknowledgement sent");
            }
            System.out.println();
            if (j % mod == 0) {
                flag = !flag;
            }
            k = (Integer) ois.readObject();
            if (k == -1) {
                break;
            }
            i = (Integer) ois.readObject();
        }
        System.out.println("Receiver finished sending data. Exiting");
        oos.writeObject(-1);
    }
}
