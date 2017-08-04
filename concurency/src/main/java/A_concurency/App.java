package A_concurency;


import java.util.concurrent.TimeUnit;

public class App {

    public static Globals globalVar = new Globals();

    public static void main( String[] args ) throws Exception
    {
        Integer numbers[] = {0,1,2,3,4,5,7,8,9,10,11};

        // run threads using Thread
        for (Integer number : numbers) {
            new ThreadDemo(number).start();
        }

        // run threads using Runnable
        for (Integer number : numbers) {
            RunnableDemo runnableDemo = new RunnableDemo(number);
            new Thread(runnableDemo, number+ " thread").start();
        }




        //otherThreadDemo111
        ThreadDemo otherThreadDemo111 = new ThreadDemo(111);
        otherThreadDemo111.start();
        Thread.sleep(1000L);
        otherThreadDemo111.interrupt();  // interups the sleep state of the thread. Moves to RUNNABLE state again.
        System.out.println("otherThreadDemo111 state: " + otherThreadDemo111.getState());
//        otherThreadDemo.start(); //can not be started again
        System.out.println("otherThreadDemo111.isAlive(): " + otherThreadDemo111.isAlive());




        //otherRunnableThread222
        RunnableDemo runnableDemo222 = new RunnableDemo(222);
        Thread otherRunnableThread222 = new Thread(runnableDemo222, 222+ " thread");

        System.out.println("otherRunnableThread222 state: " + otherRunnableThread222.getState());
        otherRunnableThread222.start();
        System.out.println("otherRunnableThread222 state: " + otherRunnableThread222.getState());
        Thread.sleep(1000L);
        System.out.println("otherRunnableThread222 state: " + otherRunnableThread222.getState());
//        otherRunnableThread.start(); //can not be started again
        System.out.println("otherRunnableThread222.isAlive(): " + otherRunnableThread222.isAlive());





        //otherRunnableThread333
        RunnableDemo runnableDemo333 = new RunnableDemo(333);
        Thread otherRunnableThread333 = new Thread(runnableDemo333, 333+ " thread");
        otherRunnableThread333.start();
        TimeUnit.MICROSECONDS.sleep(100L);
        otherRunnableThread333.join(500L);  // waits for other thread to TERMINATE for max 500ms
//        TimeUnit.MICROSECONDS.sleep(300L);
//        otherRunnableThread333.wait(500L);
        System.out.println("otherRunnableThread333 state: " + otherRunnableThread333.getState());

    }
}







