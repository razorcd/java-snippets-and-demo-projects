package A_concurency;


//ThreadLocal makes each thread have it's own copy of global variable App.globalVar
public class Globals extends ThreadLocal<String> {

    private String v = "GLOBAL";

    protected String initialValue() {
        return this.v;
    }


    //overrides ThreadLocal methods resulting in the global variable (Globals instance) to not be Thread independent anymore:
//    public String get() {
//        return v;
//    }
//    public void set(String v) {
//        this.v = v;
//    }
}
