package zhaoyun.teckstack.java.basic;

public class Primitives {

    public static void main(String[] args) {
        Integer integer1 = 1;                     // Integer.valueOf(1) -> Integer.IntegerCache.cache[]
        Integer integer2 = 1;                     // Integer.valueOf(1) -> Integer.IntegerCache.cache[]
        System.out.println(integer1 == integer2); // true

        Integer integer3 = 128;                   // Integer.valueOf(128) -> new Integer(128)
        Integer integer4 = 128;                   // Integer.valueOf(128) -> new Integer(128)
        System.out.println(integer3 == integer4); // false

        Integer integer5 = new Integer(1);
        Integer integer6 = new Integer(1);
        System.out.println(integer5 == integer6); // false

        Integer integer7 = new Integer(1);
        int integer8 = 1;
        System.out.println(integer7 == integer8); // true unboxing
    }
}
