package com.demo;

import com.demo.nIO.NioBinaryFiles;
import com.demo.nIO.NioStringFiles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) {

        // OLD IO
//        StringFiles.run();
//        DataBinaryStreams.run();
//        ObjectBinaryStreams.run();

        //  OLD and NEW IO
//        MixStringFiles.run();
//        MixObjectBinaryStream.run();


        // NEW IO
//        NioStringFiles.run();
//        NioBinaryFiles.run();

        CopyFiles.run();
    }


}
