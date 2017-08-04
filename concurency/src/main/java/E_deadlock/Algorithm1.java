package E_deadlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Algorithm1 implements Runnable {

    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Algorithm1(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm1 is waiting");
            startSignal.await();

            synchronized (App.mutexA) {
                System.out.println(threadId + " Algorithm1 aquired mutexA");
                synchronized (App.mutexB) {
                    System.out.println(threadId + " Algorithm1 aquired mutexB");
                    TimeUnit.SECONDS.sleep(2); //some operation
                }
            }

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm1 finished ");
        doneSignal.countDown();
    }
}
