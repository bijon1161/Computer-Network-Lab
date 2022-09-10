package com.mycompany.one_bit_sliding_window_protocol;

/**
 *
 * @author bijon
 */
//SENDER PROGRAM
import java.net.*;
import java.io.*;
import java.rmi.*;
import java.util.Scanner;

public class sender2 {

    public void run1() throws Exception {
        Socket s = new Socket("localhost", 2880);

        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(s.getInputStream());
        String sbuff[] = new String[8];
        PrintStream p;
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;
        do {
            p = new PrintStream(s.getOutputStream());
            System.out.print("Enter the no. of frames : ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);
            if (nf <= sws - 1) {

                System.out.println("Enter " + nf + " Messages to be send\n");
                for (i = 1; i <= nf; i++) {
                    sbuff[sptr] = in.readLine();
                    p.println(sbuff[sptr]);
                    sptr = ++sptr % 8;
                }
                sws -= nf;
                System.out.print("Acknowledgment received");
                ano = Integer.parseInt(in1.readLine());
                System.out.println(" for " + ano + " frames");
                sws += nf;
            } else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            System.out.print("\nDo you wants to send some more frames : ");
            ch = in.readLine();
            p.println(ch);
        } while (ch.equals("yes"));
        // s.close();
    }

    public void run2() throws Exception {
        Socket s = new Socket("localhost", 2881);

        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(s.getInputStream());
        String sbuff[] = new String[8];
        PrintStream p;
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;
        do {
            p = new PrintStream(s.getOutputStream());
            System.out.print("Enter the no. of frames : ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);
            if (nf <= sws - 1) {

                System.out.println("Enter " + nf + " Messages to be send\n");
                for (i = 1; i <= nf; i++) {
                    sbuff[sptr] = in.readLine();
                    p.println(sbuff[sptr]);
                    sptr = ++sptr % 8;
                }
                sws -= nf;
                System.out.print("Acknowledgment received");
                ano = Integer.parseInt(in1.readLine());
                System.out.println(" for " + ano + " frames");
                sws += nf;
            } else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            System.out.print("\nDo you wants to send some more frames : ");
            ch = in.readLine();
            p.println(ch);
        } while (ch.equals("yes"));
        // s.close();
    }

    public static void main(String a[]) throws Exception {
        Scanner input = new Scanner(System.in);
        sender2 sender = new sender2();
        System.out.println("Whom do you want to send msg? receiver 1 0r receiver2 2: ");
        int rs = input.nextInt();
        if (rs == 1) {
            sender.run1();
        } else if (rs == 2) {
            sender.run2();
        } else {
            System.out.println("Invalid receiver : this receiver doesn't exists.");
        }

    }
}
