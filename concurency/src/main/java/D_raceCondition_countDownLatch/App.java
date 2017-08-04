package D_raceCondition_countDownLatch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static final Object mutex = new Object();

    public static volatile Integer globalInt = 0;   // volatile - will not help because that behaves at a lower level (L1, L2 or code reordering). Solution: use mutex or AtomicInteger.
    public static volatile AtomicInteger globalAtomicInt = new AtomicInteger(0);  // increment/decrement is thread safe even if threads run do it at same time.
    public static volatile Integer globalIntForMutex = 0;

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
        System.out.println(" --- MAIN: after all threads finished. App.globalInt: " + App.globalInt);  // expected result is same as numberOfThreads otherwise RACE CONDITIONS occurred
        System.out.println(" --- MAIN: after all threads finished. App.globalAtomicInt: " + App.globalAtomicInt); // Atomic objects are thread safe. It will always be same as numberOfThreads
        System.out.println(" --- MAIN: after all threads finished. App.globalIntForMutex: " + App.globalIntForMutex); // using MUTEX to prevent race condition
        System.out.println(" --- END MAIN ---");

        // !!! RUN IT A FEW TIMES, RACE CONDITIONS WILL OCCUR on globalInt
    }
}
