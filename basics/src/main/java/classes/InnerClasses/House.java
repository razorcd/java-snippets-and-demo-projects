package main.java.classes.InnerClasses;

import main.java.classes.valueObject.IValueObject;

import java.util.ArrayList;
import java.util.List;

// INNER CLASSES EXAMPLE
/** - private and public Instance classes
 *  - private and public Static classes
 *  - inside Method classes (defined inside methods)
 *  - Anonymous classes (defined as argument at method call)
 */
public class House {

    private List<RoomPriv> roomPrivs = new ArrayList<>();
    private int area;

    public House(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void addRoom(int roomSize) {
        this.roomPrivs.add(new RoomPriv(roomSize));  // access private inner class
    }

    public List<RoomPriv> getRoomPrivs() {     // access private inner class
        return roomPrivs;
    }




    // 1. Public Inner CLASS.   use:   House.RoomPub room = h.new RoomPub(3);
    public class RoomPub {
        int area;

        public RoomPub(int area) {
            this.area = area;
        }

    }

    // 2. Private Inner CLASS.   use only inside parent class
    private class RoomPriv {
        int area;

        public RoomPriv(int area) {
            this.area = area;
        }

        @Override
        public String toString() {
            return "RoomPub{" +
                    "area=" + area +
                    '}';
        }
    }



    // 3. public Static Inner Class
    public static class StaticInnerPubClass {
        public void staticSayPub() {
            System.out.println("Message from public StaticInnerPubClass");
        }
    }



    // 4. private Static Inner Class
    private static class StaticInnerPrivClass {
        public void staticSayPriv() {
            System.out.println("Message from private StaticInnerPrivClass");
        }
    }



    // 5. Inner Method Class
    public void methodWithClass() {

        class MethodClass {
            String message;

            public MethodClass(String message) {
                this.message = message;
            }

            public void say() {
                System.out.println(this.message);
            }
        }

        MethodClass mc = new MethodClass("Message from inner method class (methodWithClass)");
        mc.say();
    }


    // 6. Anonymous classes   (send Instance of a Class Defined at method Invocation).
    //    (good when needing to define a class instance only once, or we have to many implementations of an Interface that we use only once)
    /**  Use as:
         h.sayMethodWithAnonymousClass(new IValueObject<String>() {
             String value;

             @Override
             public String getValue() {
                return value;
             }

             @Override
             public void setValue(String value) {
                this.value = value;
             }
         });
     */
    public void sayMethodWithAnonymousClass(IValueObject v) {
        v.setValue("AnonymousClassValue");
        System.out.println("Anonymous class value: " + v.getValue());
    }

}
