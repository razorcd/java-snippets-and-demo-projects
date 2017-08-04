package F_notify;

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

//                while(App.mutex.isEmpty()) {  // use a while-loop for cases where `thread wake ups` are caused by hardware issues
                    App.mutex.wait(); // puts wait in WAITING state and releases the monitor on App.mutex
//                }

                System.out.println(threadId + " Algorithm1 received notification");
                System.out.println(threadId + " Algorithm1 pulled: " + App.mutex.poll());

                TimeUnit.SECONDS.sleep(5);
            }

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm1 finished - ");
        doneSignal.countDown();
    }
}
