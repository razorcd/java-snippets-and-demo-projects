package F_notify;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Algorithm3 implements Runnable {

    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Algorithm3(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm3 is waiting");
            startSignal.await();

            synchronized (App.mutex) {
                TimeUnit.SECONDS.sleep(3);

                System.out.println(threadId + " Algorithm3 notifies mutex notification.");
//                App.mutex.notify(); // will notify only one
                App.mutex.add("3"); App.mutex.add("3");
                App.mutex.notifyAll();

                System.out.println(threadId + " Algorithm3 mutex was notified but thread still uses current mutex.");
                TimeUnit.SECONDS.sleep(5);
            }

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm3 finished - ");
        doneSignal.countDown();
    }
}
