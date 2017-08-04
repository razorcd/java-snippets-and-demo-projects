package F_notify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App {

    public static final Queue<String> mutex = new LinkedList();

    private static Integer numberOfThreads = 3;   //must equal exact number of threads that trigger 'i am finished'

    private static CountDownLatch startSignal = new CountDownLatch(1); // when reaches 0 it triggers all awakes
    private static CountDownLatch doneSignal = new CountDownLatch(numberOfThreads); // when reaches 0 it triggers all awakes


    public static void main(String[] args) throws InterruptedException {
        Runnable alg1 = new Algorithm1(startSignal, doneSignal);
        Thread alg1Thread = new Thread(alg1, " Algorithm1");

        Runnable alg2 = new Algorithm2(startSignal, doneSignal);
        Thread alg2Thread = new Thread(alg2, " Algorithm2");

        Runnable alg3 = new Algorithm3(startSignal, doneSignal);
        Thread alg3Thread = new Thread(alg3, " Algorithm3");

        alg1Thread.start();
        alg2Thread.start();
        alg3Thread.start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(" --- START MAIN ---");
        startSignal.countDown(); // triggers all threads to start because CountDownLatch started with 1;


        System.out.println(" --- MAIN waiting for all threads to finish ---");

        doneSignal.await();  // is triggered when doneSignal.countDown() is called 3 times. (from threads)
        System.out.println(" --- END MAIN ---");

//      !!! RUN IT:
//        - Main thread will start
//        - Algorithm1 and Algorithm2 will enter `waiting` state end release the `mutex`
//        - Algorithm3 will add data to `mutex` queue and then notify all other threads. Monitor still not released at this point.
//        - Algorithm3 will process a few seconds an then exit  and release the `mutex`
//        - Algorithm1 will take the `mutex` again, process and exit by releasing the `mutex`
//        - Algorithm2 will take the `mutex` again, process and exit by releasing the `mutex`
//        - Main thread will close
    }
}
