package C_raceConditionsArray;

public class Algorithm2 implements Runnable {

    public void run() {
        Long threadId = Thread.currentThread().getId();


//        for (int i = 1; i <= App.sharedArray.length; i++) {
//            App.sharedValueObject.setValue(i);
//        }

        App.sharedValueObject.setValue(1);
        App.sharedValueObject.setValue(2);
        App.sharedValueObject.setValue(3);
        App.sharedValueObject.setValue(4);
        App.sharedValueObject.setValue(5);
        App.sharedValueObject.setValue(6);
        App.sharedValueObject.setValue(7);
        App.sharedValueObject.setValue(8);
        App.sharedValueObject.setValue(9);
        App.sharedValueObject.setValue(10);
        App.sharedValueObject.setValue(11);
        App.sharedValueObject.setValue(12);
        App.sharedValueObject.setValue(13);
        App.sharedValueObject.setValue(14);
        App.sharedValueObject.setValue(15);
        App.sharedValueObject.setValue(16);
        App.sharedValueObject.setValue(17);
        App.sharedValueObject.setValue(18);
        App.sharedValueObject.setValue(19);
        App.sharedValueObject.setValue(20);

    }
}
