package H_threadPools;

import java.util.concurrent.TimeUnit;

public class Algorithm3 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm3 is started");

//            System.out.println(threadId + " Algorithm3 waits for semaphore.");
//            App.semaphore.acquire();
//            System.out.println(threadId + " Algorithm3 acquired from semaphore.");

            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm3 finished - ");
    }
}
