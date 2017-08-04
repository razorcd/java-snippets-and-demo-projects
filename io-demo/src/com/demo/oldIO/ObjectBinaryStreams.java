package com.demo.oldIO;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ObjectBinaryStreams {

    public static void run() {
        List<MyEntity> entities = Arrays.asList(
                new MyEntity("Name1", new Date(), new BigDecimal(555), true),
                new MyEntity("Name2", new Date(), new BigDecimal(777), false)
        );

        writeObjectsToFile("tempObjectsFile.tmp", entities);  // entities must implement the Serializable interface
        List<MyEntity> entitiesFromFile = readObjectsFromFile("tempObjectsFile.tmp");
        System.out.println(entitiesFromFile);
    }

    // write serializable objects
    private static void writeObjectsToFile(String fileName, List<MyEntity> entities) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (MyEntity myEntity : entities) {
                objectOutputStream.writeObject(myEntity);
            }
        } catch(IOException e) {
            System.out.println("Error writing file.");
            e.printStackTrace();
        }
    }


    // read serializable objects
    private static List<MyEntity> readObjectsFromFile(String fileName) {
        List<MyEntity> entities = new ArrayList<>();
        boolean eof;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))){
                eof = false;
            while(!eof) {
                try {
                    MyEntity myEntity = (MyEntity) objectInputStream.readObject();
                    entities.add(myEntity);
                } catch (ClassNotFoundException e) {
                    System.out.println("Object dod not mach the class. " + e.getMessage());
                    e.printStackTrace();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file. "+e.getMessage());
            e.printStackTrace();
        }
        return entities;
    }
}
