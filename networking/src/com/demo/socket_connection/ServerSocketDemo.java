package com.demo.socket_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {

    private static int LISTENING_PORT = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        // create SERVER SOCKET
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            System.exit(1);
        }

        // create CLIENT SOCKET
        Socket clientSocket = null;
        System.out.println("Listening to a connection on port " + LISTENING_PORT);
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Connection successful.");
        System.out.println("Listening for input....");

        // Writer and Reader
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // read and log
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            System.out.println("Server got message: " + inputLine);
            output.println("Replying " + inputLine);
            if (inputLine.equals("exit")) {
                System.out.println("Exiting.");
                break;
            }
        }

        // close
        output.close();
        input.close();
        clientSocket.close();
        serverSocket.close();

    }
}
