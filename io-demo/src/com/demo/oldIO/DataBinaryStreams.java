package com.demo.oldIO;

import java.io.*;

public class DataBinaryStreams {
    public static void run() {
        String data = "";
        for (int i = 0; i < 100; i++) {
            data+= "Line " + i + " ||||||||||||||||\n";
        }
        writeByteStream("tempByteFile.tmp", "Line 1\n\u001B[32mLi----\u001B[34m------\u001B[m--------ne\u001B[0m 2\nLine 3"+data);
        System.out.println(readByteStream("tempByteFile.tmp"));
    }

    // WRITE ByteStream file
    private static void writeByteStream(String fileName, String data) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            dataOutputStream.writeUTF(data);
//            dataInputStream.close(); // handled by try with resource
        } catch (IOException e) {
            System.out.println("Error writing file.");
            e.printStackTrace();
        }
    }


    // READ ByteStream file
    private static String readByteStream(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            stringBuffer.append(dataInputStream.readUTF());
//            dataInputStream.close(); // handled by try with resource
        } catch (IOException e)  {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

        return stringBuffer.toString().trim();
    }
}
