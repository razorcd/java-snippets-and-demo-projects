package A_concurency;

import java.util.concurrent.TimeUnit;

public class ThreadDemo extends Thread {

    Integer number;

    public ThreadDemo(Integer number) {
        super(number + " thread"); //call the thread constructor to name the thread
        this.number = number;
    }

    // this method will be executed in the new thread
    public void run() {
        long threadId = Thread.currentThread().getId();

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException ex ) {
            Thread.currentThread().interrupt();
        }
        System.out.printf("ThreadDemo " + threadId + ": " + this.number + "\n");
    }
}
