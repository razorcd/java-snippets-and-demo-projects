package com.demo;

import com.demo.oldIO.MyEntity;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NioObjectBynaryStream {

    public static void run() {
        List<MyEntity> entities = Arrays.asList(
                new MyEntity("Name1", new Date(), new BigDecimal(555), true),
                new MyEntity("Name2", new Date(), new BigDecimal(777), false)
        );

        writeFile("nioObjectFileFile.tmp", entities);
        List<MyEntity> newEntities = readFile("nioObjectFileFile.tmp");
        System.out.println(newEntities);
    }

    private static void writeFile(String fileName, List<MyEntity> entities) {
        Path filePath = FileSystems.getDefault().getPath(fileName);

        try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)))) {
            for (MyEntity myEntity : entities) {
                objectOutputStream.writeObject(myEntity);
            }
//            objectOutputStream.close();  // handled by try-with-resources
        } catch (IOException e) {
            System.out.println("Can't write file. " + e.getMessage() );
            e.printStackTrace();
        }
    }



    private static List<MyEntity> readFile(String fileName) {
        Path filePath = FileSystems.getDefault().getPath(fileName);
        List<MyEntity> myEntities = new ArrayList<>();
        boolean eof = false;

        try (ObjectInputStream objectInputStream= new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))) {
            while (!eof) {
                try {
                    MyEntity myEntity = (MyEntity) objectInputStream.readObject();
                    myEntities.add(myEntity);
                } catch (EOFException e) {
                    eof = true;
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found. " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Can't read file. " + e.getMessage() );
            e.printStackTrace();
        }

        return myEntities;
    }
}
