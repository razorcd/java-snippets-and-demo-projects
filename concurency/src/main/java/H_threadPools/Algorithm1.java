package H_threadPools;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Algorithm1 implements Callable<String> {

    public Algorithm1() {
    }

    public String call() {
        Long threadId = Thread.currentThread().getId();

        try {
            System.out.println(threadId + " Algorithm1 is started");

//            System.out.println(threadId + " Algorithm1 waits for semaphore.");
//            App.semaphore.acquire();
//            System.out.println(threadId + " Algorithm1 acquired from semaphore.");

            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException ex) {}

        System.out.println(threadId + " Algorithm1 finished - ");
        return "Algorithm1 finished";
    }
}
