package com.mycompany.one_bit_sliding_window_protocol;

/**
 *
 * @author bijon
 */
//RECEIVER PROGRAM
import java.net.*;
import java.io.*;
import java.rmi.*;

public class receiver2 {

    public static void main(String a[]) throws Exception {
        System.out.println("Waiting for sender");
        ServerSocket ser = new ServerSocket(2881);
        Socket s = ser.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintStream p = new PrintStream(s.getOutputStream());
        int i = 0, rptr = -1, nf, rws = 8;
        String rbuf[] = new String[8];
        String ch;
        System.out.println();

        do {
            nf = Integer.parseInt(in.readLine());
            if (nf <= rws - 1) {
                for (i = 1; i <= nf; i++) {
                    rptr = ++rptr % 8;
                    rbuf[rptr] = in.readLine();
                    System.out.println("The received Frame " + rptr + " is : " + rbuf[rptr]);
                }
                rws -= nf;
                System.out.println("\nAcknowledgment sent\n");
                p.println(rptr + 1);
                rws += nf;
            } else {
                break;
            }
            ch = in.readLine();
        } while (ch.equals("yes"));
        s.close();
    }
}
