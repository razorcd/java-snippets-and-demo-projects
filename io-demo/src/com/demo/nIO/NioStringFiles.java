package com.demo.nIO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioStringFiles {
    public static void run() {

        writeFile("newIOStringFile.tmp", "Line 1\nLine 2\nLine 3");
        System.out.println(readFile("newIOStringFile.tmp"));
    }


    private static void writeFile(String fileName, String data) {
        try {
            Path filePath = FileSystems.getDefault().getPath(fileName);
            Files.write(filePath, data.getBytes("UTF-8"), StandardOpenOption.CREATE); // create or create new or append ...
        } catch (IOException e) {
            System.out.println("can't write file. " + e.getMessage());
        }
    }


    private static List<String> readFile(String fileName) {
        List<String> lines = null;
        try {
            Path filePath = FileSystems.getDefault().getPath(fileName);

             lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("can't read file. " + e.getMessage());
        }
        return lines;
    }


}
