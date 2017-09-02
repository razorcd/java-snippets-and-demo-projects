package com.demo;

import com.demo.socket_connection.ClientDemo;
import com.demo.socket_connection.ServerSocketDemo;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

//        HttpConn.run();   // get HTTP connection

//        TCPIPSockets.run();


        // for Server/Client Socket please build and run both  ServerSocketDemo  and   ClientDemo   at the same time. Then send message form one to another.

        HttpClient httpClient = HttpClient.New(new URL("http://www.google.com"));
        httpClient.
        InputStream inputStream = httpClient.getInputStream();
        System.out.println(inputStream.read());

    }
}
