package com.demo.oldIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StringFiles {

    public static void run() {
        writeTextToFile("testFile.tmp", "One line, text\nLine 2, other text\nLine3");
//        String text = readWholeFileAtOnce("testFile.tmp");
//        String text = readWholeFileWithScanner("testFile.tmp");
        String text = readWholeFileWithScannerAndBufferReader("testFile.tmp");
        System.out.println(text);
    }


    // WRITE. With Try With Resources
    private static void writeTextToFile(String fileName, String text) {

        try(FileWriter fileWriter = new FileWriter(fileName)) {    // Try With Resources ensures the File is always Closed

            fileWriter.write(text);

        } catch (IOException e) {   // catch or specify that the method throws an exception
            System.out.println("Error writing to file: " + fileName);
            e.printStackTrace();   // LOG
        }

    }

      // WRITE. With manually handling the exceptions and closing file
//    private static void writeTextToFile(String fileName, String text) {
//        FileWriter fileWriter = null;
//        try {
//
//            fileWriter = new FileWriter(fileName);
//            fileWriter.write(text);
//
//        } catch (IOException e) {   // catch or specify that the method throws an exception
//            System.out.println("Error writing to file: " + fileName);
//            e.printStackTrace();   // LOG
//        } finally {
//            try {
//
//                fileWriter.close();
//
//            } catch (IOException | NullPointerException e) {
//                System.out.println("Error closing file: " + fileName);
//                e.printStackTrace();
//            }
//        }
//    }



    //READ. With Try with Resources and Scanner and BufferReader
    private static String readWholeFileWithScannerAndBufferReader(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = null;

        try(BufferedReader bf =(new BufferedReader(new FileReader(fileName)))) {  // BufferReader - reads files in chuck of bytes (it's faster)
            scanner = new Scanner(bf);
            while(scanner.hasNext()) {
                stringBuffer.append(scanner.nextLine());
                stringBuffer.append("\n");
            }
        } catch(IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        } finally {
            if (scanner != null) scanner.close();
        }

        return stringBuffer.toString().trim();
    }


    // READ. With Try with Resources and Scanner
    private static String readWholeFileWithScanner(String fileName) {
//        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = null;

        try(FileReader fileReader = new FileReader(fileName)) {
            scanner = new Scanner(fileReader);
//            scanner.useDelimiter("\n");    //to read line with .next()
            while(scanner.hasNext()) {
//                result += scanner.nextLine() + "\n";
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        } finally {
            if (scanner != null) scanner.close();   //scanner.close() ->  Closes any Stream(here FileReader) that it has been using if Stream implements Closeable interface.
         }
        return stringBuilder.toString().trim(); // trim to remove extra `/n`
//        return result.trim();   // trim to remove extra `/n`
    }



    // READ. With try catch and direct filereader
    private static String readWholeFileAtOnce(String fileName) {
        FileReader fileReader = null;
        char[] charBuffer = null;
        try {

            fileReader = new FileReader(fileName);

            // read whole file:
            charBuffer = new char[50];
            fileReader.read(charBuffer);

        } catch (IOException e) { // catch or specify that the method throws an exception
            e.printStackTrace();
        } finally {
            try {

                fileReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(charBuffer);    // converts char[] to String
    }

}
