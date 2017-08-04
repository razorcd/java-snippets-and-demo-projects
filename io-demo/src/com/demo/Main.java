package com.demo;

import java.io.IOException;
import java.nio.file.*;

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

        TransferFiles.run();
        handleFiles();
    }

    private static void handleFiles() {
        // Paths:
        FileSystems.getDefault(); // -> current user folder (project folder)
        FileSystems.getDefault().getPath("folderName", "fileName.zx");
        Path path = Paths.get("/home/razor/app/file.data");
        System.out.println("Absolute path: " + Paths.get("").toAbsolutePath());


        Path aFolder = FileSystems.getDefault().getPath("TEMP");
        Path aFile = FileSystems.getDefault().getPath("TEMP","temp1.tmp");
        Path aFile2 = FileSystems.getDefault().getPath("TEMP","temp2.tmp");
        Path aFile3 = FileSystems.getDefault().getPath("TEMP","temp3.tmp");

        try {
            Files.createDirectories(aFolder); // create folder
            if (!Files.exists(aFile)) {Files.createFile(aFile);} // create folder. Fails if file already exits.
            Files.copy(aFile, aFile2, StandardCopyOption.REPLACE_EXISTING); // copy file.
            Files.move(aFile2, aFile3, StandardCopyOption.REPLACE_EXISTING); // move file

            System.out.println("File temp2.tmp exists: " + Files.exists(aFile3));

            Files.delete(aFile3); //delete file
//            Files.deleteIfExists(aFile3);
            // Deleting Folders requires manually deleting all files inside.

        } catch (IOException e) {
            System.out.println("Error. "+e.getMessage());
        }
        System.out.println("File temp2.tmp exists: " + Files.exists(aFile3));

        //NIO: better exceptions and supports symbolic links
    }


}
