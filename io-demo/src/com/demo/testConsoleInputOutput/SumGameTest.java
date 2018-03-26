package com.demo.testConsoleInputOutput;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SumGameTest {
    final static PrintStream stdout = System.out;
    final static InputStream stdin = System.in;

    ExecutorService es;
    ByteArrayOutputStream consoleOutputStream;
    PipedOutputStream testDataStream;

    PipedInputStream newStdIn;
    PrintStream newStdOut;

    //outputStream is READ THE CONSOLE
    @Before
    public void grabSystemOut() throws UnsupportedEncodingException {
        consoleOutputStream = new ByteArrayOutputStream();
        newStdOut = new PrintStream(consoleOutputStream, true, "UTF-8");
        System.setOut(newStdOut);
    }

    // WRITE TO CONSOLE
    @Before
    public void grabSystemIn() throws IOException {
        testDataStream = new PipedOutputStream();   //we need an consoleOutputStream stream so we can send data from this thread
        newStdIn = new PipedInputStream();   //we send this data to our new input stream (WRITE TO CONSOLE)
        newStdIn.connect(testDataStream);
        System.setIn(newStdIn);
    }

    @Before
    public void startThreadExecutor() {
        es = Executors.newCachedThreadPool();
    }

    @After
    public void shutdownThreadExecutor() throws InterruptedException {
        es.shutdown();
        boolean finshed = es.awaitTermination(1, TimeUnit.MINUTES);  // wait for all threads to finish before exiting main thread
    }

    @After
    public void closeStreams() throws IOException {
        testDataStream.close();
        newStdIn.close();
        newStdOut.close();
    }

    @AfterClass
    public static void setStdInOutBack() {
        System.setIn(stdin);
        System.setOut(stdout);
    }


    @Test
    public void testMain() throws InterruptedException, IOException {
        startApplicationThread();


        // read what game application wrote to console
        assertThat("Started Sum Calculator.\n", is(consoleOutputStream.toString()));


        // print something to game application waiting console
        consoleOutputStream.reset(); // clear console input
        testDataStream.write("5\r\n".getBytes());  // write new data
        testDataStream.flush();  // send it trough
        Thread.sleep(100L); // wait for the Application thread to process it, result will be in the `consoleOutputStream` console. //TODO: implement a scanner with a pipestream.


        // read what game application wrote to console
        assertThat("plus\n", is(consoleOutputStream.toString()));

        // print something to game application waiting console
        consoleOutputStream.reset(); // clear console input
        testDataStream.write("12\r\n".getBytes());  // write new data
        testDataStream.flush();  // send it trough
        Thread.sleep(100L); // wait for the Application thread to process it, result will be in the `consoleOutputStream` console.


        // read what game application wrote to console
        assertThat("Equals\n17\n", is(consoleOutputStream.toString()));

    }


    private void startApplicationThread() throws InterruptedException {
        es.execute(() ->  SumGame.main(null)); //Runnable
        Thread.sleep(100L); // ensure thread is initialised
    }
}