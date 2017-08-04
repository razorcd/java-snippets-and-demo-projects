package com.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class CopyFiles {
    public static void run() {

        try (RandomAccessFile fromRa = new RandomAccessFile("someBinaryFile.dat", "r");
             FileChannel fromChannel = fromRa.getChannel();
             RandomAccessFile toRa = new RandomAccessFile("someBinaryFileCopy.tmp", "rwd");
             FileChannel toChannel = toRa.getChannel()) {

            fromChannel.position(0);
            toChannel.position(0);
//            long numTransferred = toChannel.transferFrom(fromChannel, 0,fromChannel.size());     // copy file
            long numTransferred = fromChannel.transferTo( 0, toChannel.size(), toChannel);     // copy file

            System.out.println("Num transferred: " + numTransferred);
        } catch (IOException e) {
            System.out.println("Error transferring file."+e.getMessage());
        }

    }
}
