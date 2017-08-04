package H_threadPools;

import java.util.concurrent.*;

public class App {

    public static final BlockingQueue<String> mutex = new LinkedBlockingQueue<String>();

    private static Integer numberOfThreads = 2;   //must equal exact number of threads that trigger 'i am finished'

//    public static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        ((ThreadPoolExecutor) executor).prestartAllCoreThreads();

        Callable alg1 = new Algorithm1();
        Runnable alg2 = new Algorithm2();
        Runnable alg3 = new Algorithm3();
        Future alg1future =  executor.submit(alg1);
        Future alg2future =  executor.submit(alg2);
        Future alg3future =  executor.submit(alg3);


//        TimeUnit.SECONDS.sleep(3);

        System.out.println(" --- START MAIN ---");
//        App.semaphore.release();


        try {
            System.out.println("Return from Callable: " + alg1future.get()); // will block execution of current thread until result is received form Algorithm1 thread
        } catch (ExecutionException e) {}


        executor.shutdown();

        System.out.println(" --- MAIN waiting for all threads to finish ---");
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // will wait for executor to have completed all threads

        System.out.println(" --- END MAIN ---");

//        !!! RUN IT
//        We have a thread pool of 2 and 3 threads executing. Last thread will wait for one of the first 2 to finish.
    }
}
