package main.java;

public class Flow {

    public static void run() {

        // for i
        for (int i = 0; i < 6; i++) {
            if (i==2) {continue;}     // jumps to next round
            if (i==4) {break;}     // exits loop
            System.out.println("For i=" + i);
        }

        //for(init; while; execute) {...}
        for(String a="."; a.length()<10; a+=".") {
            if (a.equals("..")) {continue;}     // jumps to next round
            if (a.equals(".....")) {break;}     // exits loop
            System.out.println(a);
        }

        // for ceach in array
        for (int i : new int[]{1,2,3,4,5,6} ) {
            if (i==2) {continue;}     // jumps to next round
            if (i==5) {break;}     // exits loop
            System.out.println("for each loop i="+i);
        }


        // switch
        int foo = 2;
        switch (foo) {
            case 1:
                System.out.println("case 1");
                break;   // without this break; it will evaluate next case
            case 2: case 3:
                System.out.println("case 2 and 3");
                break;
            default:
                System.out.println("default case");
        }



        //while (evaluates and the executes)
        int w=0;

        while (w<3) {
            System.out.println("While loop w="+w);
            w++;
        }

        int j=0;
        while(true) {
            System.out.println("While loop j="+j);
            if (j==3) { break; }   // exits loop
            j++;
        }



        //do while (executes the evaluates)
        int k=0;

        do {
            System.out.println("Do while loop k="+k);
            k++;
        } while (k<3);

        int m=0;
        do {
            System.out.println("Do while loop m="+m);
            if (m==3) { break; }   // exits loop
            m++;
        } while(true);
    }
}