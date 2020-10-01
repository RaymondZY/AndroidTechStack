package zhaoyun.teckstack.java.basic;

public class StringPool {

    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        String c = "a" + "b" + "c"; // 在编译期就被处理成"abc"
        System.out.println(a == b); // true
        System.out.println(a == c); // true

        String d = new String("abc");
        String e = new String("abc");
        System.out.println(d == e); // false

        d = d.intern();
        e = e.intern();
        System.out.println(d == e); // true
    }
}
