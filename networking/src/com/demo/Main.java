package com.demo;

import com.demo.socket_connection.ClientDemo;
import com.demo.socket_connection.ServerSocketDemo;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        HttpConn.run();   // get HTTP connection

        TCPIPSockets.run();


        // for Server/Client Socket please build and run both  ServerSocketDemo  and   ClientDemo   at the same time. Then send message form one to another.

    }
}
