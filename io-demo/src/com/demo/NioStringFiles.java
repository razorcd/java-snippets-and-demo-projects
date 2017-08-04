package com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class NioStringFiles {

    public static void run() {

        writeFile("nioStringFile.tmp", "Line 1\nLine 2\nLine 3");
        System.out.println(readFile("nioStringFile.tmp"));
//        System.out.println(readFileWithScanner("nioStringFile.tmp"));

    }

    private static void writeFile(String fileName, String data) {
        Path filePath = FileSystems.getDefault().getPath(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {  // with NIO we create the buffer and file writer in one call
            bufferedWriter.write(data);
        } catch (IOException e) {
            System.out.println("Can't write file. " + e.getMessage() );
            e.printStackTrace();
        }
    }



    // reading file directly through the BufferedReader
    private static String readFile(String fileName) {
        StringBuilder result = new StringBuilder();
        Path filePath = FileSystems.getDefault().getPath(fileName);

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                result.append(input + "\n");
            }
        } catch (IOException e) {
            System.out.println("Can't write file. " + e.getMessage() );
            e.printStackTrace();
        }
        return result.toString().trim();
    }



    // reading file through the BufferedReader using a Scanner
    private static String readFileWithScanner(String fileName) {
        StringBuilder result = new StringBuilder();
        Path filePath = FileSystems.getDefault().getPath(fileName);

        try (Scanner scanner = new Scanner(Files.newBufferedReader(filePath))) {
            while (scanner.hasNext()) {
                result.append(scanner.nextLine() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Can't write file. " + e.getMessage() );
            e.printStackTrace();
        }
        return result.toString().trim();
    }

}
