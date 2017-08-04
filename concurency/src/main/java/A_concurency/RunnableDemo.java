package A_concurency;

import java.util.concurrent.TimeUnit;

public class RunnableDemo implements Runnable {

    Integer number;

    public RunnableDemo(Integer number) {
        this.number = number;
    }

    public void run() {
        long threadId = Thread.currentThread().getId();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.printf("RunnableDemo " + threadId + ": " + this.number + "\n");



        //modifies global variable (bad practice)
        System.out.printf("RunnableDemo " + threadId + " accesses Globals.globalVar: " + App.globalVar.get() + "\n");

        App.globalVar.set("THREAD RunnableDemo "+threadId+" CHANGED GLOBAL");
        try {
            TimeUnit.MICROSECONDS.sleep(100L);
        } catch (InterruptedException ex) {}

        System.out.printf("RunnableDemo " + threadId + " accesses after set Globals.globalVar: " + App.globalVar.get() + "\n");

        //to avoid memory leaks call remove on any ThreadLocal instance !!!
        App.globalVar.remove();
        System.out.printf("RunnableDemo " + threadId + " accesses after remove Globals.globalVar: " + App.globalVar.get() + "\n");
    }
}
