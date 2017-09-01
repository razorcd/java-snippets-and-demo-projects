package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpConn {

    private static final String TARGET_URL = "http://www.google.com";

    public static void run() {

        try {
            //set URL
            URL theURL = new URL(TARGET_URL);

            System.out.println("Protocol: " + theURL.getProtocol());
            System.out.println("Port: " + theURL.getPort());
            System.out.println("Host: " + theURL.getHost());

            //get connection
            URLConnection connection = theURL.openConnection();
            connection.connect();

            // read ContentLength
            int contentLength = connection.getContentLength();
            System.out.println("Content Length: " + contentLength);
            if (contentLength == 0) {
                throw new RuntimeException("ContentLength is 0");
            }


            //get Input Stream
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
                int c;
                while ((c = inputStream.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();  // ensure stream is closed
                }
            }


        } catch (MalformedURLException e) {
            System.out.println("Maflormed URL : " + e.getMessage());
        } catch (IOException e) {
        }

    }
}
