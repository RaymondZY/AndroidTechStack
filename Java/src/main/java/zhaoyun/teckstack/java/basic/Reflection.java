package zhaoyun.teckstack.java.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {

    public class SuperClass {

        public String superPublicString;
        protected String superProtectedString;
        private String superPrivateString;
        String superPackageString;

        public SuperClass() {
        }

        protected SuperClass(String a) {
        }

        private SuperClass(String a, String b) {
        }

        SuperClass(String a, String b, String c) {
        }

        public void superPublicMethod() {
        }

        protected void superProtectedMethod() {
        }

        private void superPrivateMethod() {
        }

        void superPackageMethod() {
        }
    }

    public class SubClass extends SuperClass {

        public String subPublicString;
        protected String subProtectedString;
        private String subPrivateString;
        String subPackageString;

        public SubClass() {
        }

        protected SubClass(String a) {
        }

        private SubClass(String a, String b) {
        }

        SubClass(String a, String b, String c) {
        }

        public void subPublicMethod() {
        }

        protected void subProtectedMethod() {
        }

        private void subPrivateMethod() {
        }

        void subPackageMethod() {
        }
    }

    public static void main(String[] args) {
        Class<?> clazz = SubClass.class;

        System.out.println("class.getFields() :");
        for (Field field : clazz.getFields()) {
            System.out.println(field.getName());
        }
        System.out.println();

        System.out.println("class.getDeclaredFields() :");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName());
        }
        System.out.println();

        System.out.println("class.getMethods() :");
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println();

        System.out.println("class.getDeclaredMethods() :");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
        System.out.println();

        System.out.println("class.getConstructors() :");
        for (Constructor<?> constructor : clazz.getConstructors()) {
            System.out.println(constructor.getName() + " " + Modifier.toString(constructor.getModifiers()));
        }
        System.out.println();

        System.out.println("class.getDeclaredConstructors() :");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println(constructor.getName() + " " + Modifier.toString(constructor.getModifiers()));
        }
        System.out.println();
    }
}
