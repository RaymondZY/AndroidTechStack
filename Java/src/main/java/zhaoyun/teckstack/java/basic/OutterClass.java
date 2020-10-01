package zhaoyun.teckstack.java.basic;

public class OutterClass {

    private static String staticString;
    private String string;

    private static void staticMethod() {
    }

    private void method() {
    }

    private static class StaticInnerClass {

        private StaticInnerClass() {
            // 无法获取Outter对象
            // OutterClass outerClass = OutterClass.this;

            // 无法调用非静态方法
            // method();

            // 可以调用静态方法
            staticMethod();

            // 无法获取非静态属性
            // System.out.println(string);

            // 可以获取静态属性
            System.out.println(staticString);
        }
    }

    private class InnerClass {

        private InnerClass() {
            // 可以获取Outter对象
            OutterClass outerClass = OutterClass.this;

            // 可以调用非静态方法
            method();

            // 可以调用静态方法
            staticMethod();

            // 可以获取非静态属性
            System.out.println(string);

            // 可以获取静态属性
            System.out.println(staticString);
        }
    }

    private void testAnonymous() {
        String string = "a";
        new Object() {
            @Override
            public String toString() {
                return string;
            }
        };
    }

    private void testLocalInner() {
        class LocalInner {
            void method() {
                // 可以访问非静态变量
                System.out.println(string);

                // 可以访问静态变量
                System.out.println(staticString);

                // 可以调用非静态方法
                OutterClass.this.method();

                // 可以调用静态方法
                OutterClass.staticMethod();
            }
        }
    }

    private static void testStaticLocalInner() {
        class LocalInner {
            void method() {
                // 无法访问非静态变量
                // System.out.println(string);

                // 可以访问静态变量
                System.out.println(staticString);

                // 无法调用非静态方法
                // OutterClass.this.method();

                // 可以调用静态方法
                OutterClass.staticMethod();
            }
        }
    }

    public static void main(String[] args) {
        OutterClass outterClass = new OutterClass();

        StaticInnerClass staticInnerClass = new StaticInnerClass();

        InnerClass innerClass = outterClass.new InnerClass();
    }
}
