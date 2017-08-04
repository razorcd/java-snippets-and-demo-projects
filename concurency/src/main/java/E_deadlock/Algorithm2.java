package E_deadlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Algorithm2 implements Runnable {

    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Algorithm2(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm2 is waiting");
            startSignal.await();

            synchronized (App.mutexB) {
                System.out.println(threadId + " Algorithm2 aquired mutexB");
                synchronized (App.mutexA) {
                    System.out.println(threadId + " Algorithm2 aquired mutexA");
                    TimeUnit.SECONDS.sleep(2); //some operation
                }
            }

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm2 finished ");
        doneSignal.countDown();
    }
}
