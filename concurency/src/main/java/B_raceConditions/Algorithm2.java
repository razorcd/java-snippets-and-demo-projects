package B_raceConditions;

public class Algorithm2 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();

        App.sharedBool = true;
        System.out.println(threadId + " Algorithm2 - App.shared: " + App.sharedString);
    }
}
