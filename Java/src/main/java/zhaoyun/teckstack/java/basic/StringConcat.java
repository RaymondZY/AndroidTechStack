package zhaoyun.teckstack.java.basic;

public class StringConcat {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "c";

        String string1 = "a" + "b" + "c";
        String string2 = a + b + c; // 等价于 String string2 = new StringBuilder().append(a).append(b).append(c).toString();

        System.out.println(string1 == string2); // false

        String string3 = "abc";
        String string4 = string3.concat("def");

        System.out.println(string3 == string4); // false
    }
}
