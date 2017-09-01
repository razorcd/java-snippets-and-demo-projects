package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPIPSockets {

    public static void run() {

        // create socket
        try (Socket sock = new Socket("whois.internic.net", 43)) {

            // get streams
            InputStream inputStream = sock.getInputStream();
            OutputStream outputStream = sock.getOutputStream();

            // create message
            String string = "infiniteskils.com\n";
            byte buffer[] = string.getBytes();   // message is transmitted as bytes


            // send message
            outputStream.write(buffer);  // sends the command throw the socket


            // read response message
            int ch;
            while ((ch = inputStream.read()) != -1) {
                System.out.print((char) ch);
            }


            //close socket
            sock.close();



        } catch (IOException e) {

        }

    }
}
