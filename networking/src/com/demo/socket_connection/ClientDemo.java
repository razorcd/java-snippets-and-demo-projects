package com.demo.socket_connection;

import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

    private static String HOST = "127.0.0.1";
    private static int PORT = 8888;

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter output = null;
        BufferedReader input = null;

        // create Socket
        try {
            socket = new Socket(HOST, PORT);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // stdin reader
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


        String userinput;
        System.out.println("Type your message ...");
        while ((userinput = stdIn.readLine().toString()) != null) {
            // read std input and send through socket
            output.println(userinput);
            // log the response
            System.out.println("client: " + input.readLine());
        }


        output.close();
        input.close();
        stdIn.close();
        socket.close();

    }

}
