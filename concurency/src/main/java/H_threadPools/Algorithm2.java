package H_threadPools;

import java.util.concurrent.TimeUnit;

public class Algorithm2 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm2 is started");

//            System.out.println(threadId + " Algorithm2 waits for semaphore.");
//            App.semaphore.acquire();
//            System.out.println(threadId + " Algorithm2 acquired from semaphore.");

            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm2 finished - ");
    }
}
