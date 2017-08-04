package D_raceCondition_countDownLatch;

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

            App.globalInt++; // read, increment, write. This will cause race condition when executed at same time by multiple threads.
            App.globalAtomicInt.incrementAndGet(); // Atomic objects are thread safe

            synchronized (App.mutex) {
                App.globalIntForMutex++;
            }

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm2 finished - App.globalInt: " + App.globalInt);
        System.out.println(threadId + " Algorithm2 finished - App.globalAtomicInt: " + App.globalAtomicInt);
        System.out.println(threadId + " Algorithm2 finished - App.globalIntForMutex: " + App.globalIntForMutex);
        doneSignal.countDown();
    }
}
