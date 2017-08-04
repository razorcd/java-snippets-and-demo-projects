package G_blockingQueue;

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

            synchronized (App.mutex) {
                System.out.println(threadId + " Algorithm1 waits for mutex notification.");

                String s = App.mutex.take();
                System.out.println(threadId + " Algorithm1 received notification and pulled: " + s);

                TimeUnit.SECONDS.sleep(5);
            }

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm1 finished - ");
        doneSignal.countDown();
    }
}
