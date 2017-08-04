package C_raceConditionsArray;

public class App {

    public static ValueClass sharedValueObject = new ValueClass(0);
    public static Integer[] sharedArray = new Integer[20];

    public static void main( String[] args ) throws Exception {

        Runnable alg1 = new Algorithm1();
        Thread alg1Thread = new Thread(alg1, " Algorithm1");

        Runnable alg2 = new Algorithm2();
        Thread alg2Thread = new Thread(alg2, " Algorithm2");

        System.out.println("--- start main ---");
        alg2Thread.start();
        alg1Thread.start();
        System.out.println("--- end main ---");

        // !!! Array values might not be exactly in order because Compiler, JVM or CPU can do code reordering in Algorithm2 assignments (or for-loop), if output is the same for one thread.
    }
}
