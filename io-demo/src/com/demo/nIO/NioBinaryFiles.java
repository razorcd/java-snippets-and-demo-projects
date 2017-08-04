package com.demo.nIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class NioBinaryFiles {
    public static void run() {

        writeFile("newIOBinaryFile.tmp", "Line 1\nLine 2\nLine 3");
//        System.out.println(readFileWithRandomAccessOnly("newIOBinaryFile.tmp"));
        System.out.println(readFileWithRandomAccessAndChannel("newIOBinaryFile.tmp"));
    }


    // WRITE
    private static void writeFile(String fileName, String data) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileChannel fileChannel = fileOutputStream.getChannel()) {

            byte[] bytesData = data.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(bytesData);

            int bytesWritten = fileChannel.write(buffer);
//            buffer.flip();  // ! to Reset buffer if you want to Read/Write again using this buffer
            System.out.println("bytes written to file: " + bytesWritten);
        } catch (IOException e) {
            System.out.println("can't write file. " + e.getMessage());
        }
    }


    //READ
    private static List<String> readFileWithRandomAccessOnly(String fileName) {
        List<String> lines = new ArrayList<>();

        try(RandomAccessFile ra = new RandomAccessFile(fileName, "rwd")) {
            byte[] b = new byte[99];
            ra.read(b);
            lines.add(new String(b));
        } catch (IOException e) {
            System.out.println("can't read file. " + e.getMessage());
        }
        return lines;
    }

    //READ
    private static List<String> readFileWithRandomAccessAndChannel(String fileName) {
        List<String> lines = new ArrayList<>();

        try(RandomAccessFile ra = new RandomAccessFile(fileName, "rwd");   // try-with-resources will close both  RandomAccessFile and FileChannel
            FileChannel fileChannel = ra.getChannel()) {
            System.out.println("Read file size(bytes): " + fileChannel.size());

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            if (byteBuffer.hasArray()) {
                lines.add(new String(byteBuffer.array()));
            }
//            buffer.flip();  // ! to Reset buffer if you want to Read/Write again using this buffer
        } catch (IOException e) {
            System.out.println("can't read file. " + e.getMessage());
        }
        return lines;
    }
}
