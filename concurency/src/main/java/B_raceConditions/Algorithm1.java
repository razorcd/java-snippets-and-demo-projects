package B_raceConditions;

public class Algorithm1 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();

        while (!App.sharedBool) {} // after 1ms, the thread will put this variable in L1, L2 cache even if it's global.
        System.out.println(threadId + " Algorithm1 - App.shared: " + App.sharedString);
    }
}
