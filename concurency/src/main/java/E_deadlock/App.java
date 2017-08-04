package E_deadlock;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App {

    public static final Object mutexA = new Object();
    public static final Object mutexB = new Object();

    private static Integer numberOfThreads = 3;   //must equal exact number of threads that trigger 'i am finished'

    private static CountDownLatch startSignal = new CountDownLatch(1); // when reaches 0 it triggers all awakes
    private static CountDownLatch doneSignal = new CountDownLatch(numberOfThreads); // when reaches 0 it triggers all awakes


    public static void main(String[] args) throws InterruptedException {
        Runnable alg1 = new Algorithm1(startSignal, doneSignal);
        Thread alg1Thread = new Thread(alg1, " Algorithm1");

        Runnable alg2 = new Algorithm2(startSignal, doneSignal);
        Thread alg2Thread = new Thread(alg2, " Algorithm2");

        alg1Thread.start();
        alg2Thread.start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(" --- START MAIN ---");
        startSignal.countDown(); // triggers all threads to start because CountDownLatch started with 1;


        System.out.println(" --- MAIN waiting for all threads to finish ---");
        doneSignal.await();  // is triggered when doneSignal.countDown() is called 3 times. (from threads)

        System.out.println(" --- END MAIN ---");

        // !!! When Algorithm1 acquires mutexA and Algorithm2 acquires mutexB they are both stuck => DEADLOCK
    }
}
