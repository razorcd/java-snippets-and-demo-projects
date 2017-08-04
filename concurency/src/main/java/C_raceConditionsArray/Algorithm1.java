package C_raceConditionsArray;

import java.util.Arrays;

public class Algorithm1 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();


        for (int i = 0; i < App.sharedArray.length; i++) {
            App.sharedArray[i] = App.sharedValueObject.getValue();
        }

        System.out.println(threadId + " Algorithm1 - App.sharedArray: " + Arrays.toString(App.sharedArray));
    }
}
