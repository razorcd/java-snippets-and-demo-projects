package B_raceConditions;

public class App {

    public static Boolean sharedBool = false;
//    public static volatile Boolean sharedBool = false;  // using 'volatile' will not cache in L1, L2 and ensures synchronisation of global variable across threads
    public static String sharedString = "SHARED 0";

    public static void main( String[] args ) throws Exception {
        Runnable alg1 = new Algorithm1();
        Thread alg1Thread = new Thread(alg1, " Algorithm1");

        Runnable alg2 = new Algorithm2();
        Thread alg2Thread = new Thread(alg2, " Algorithm2");

        System.out.println("--- start main ---");
        alg1Thread.start();
        Thread.sleep(1000L);  //if sleeping for more then Xms, alg2Thread will store App.sharedBool in L1, L2 cache. So it will never exit the loop even if the main App.sharedBool is changed.
        alg2Thread.start();
        System.out.println("--- end main ---");

        // !!! RUN A FEW TIMES. App will stuck because Algorithm1 will cache App.sharedBool in it's non shareable L1/L2 CPU cache. Algorithm2 changes App.sharedBool but does not affect Algorithm1's cached value.
    }
}
