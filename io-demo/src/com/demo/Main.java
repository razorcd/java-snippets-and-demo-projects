package com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // OLD IO
//        StringFiles.run();
//        DataBinaryStreams.run();
//        ObjectBinaryStreams.run();

        // NEW IO
//        NioStringFiles.run();
        NioObjectBynaryStream.run();
    }
}
