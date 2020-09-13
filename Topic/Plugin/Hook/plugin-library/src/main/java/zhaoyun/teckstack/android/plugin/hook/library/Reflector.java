package zhaoyun.teckstack.android.plugin.hook.library;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhaoyun
 * @version 2020/7/1
 */
public class Reflector {

    public static ClassAction on(String className) throws Exception {
        return new ClassAction(className);
    }

    public static ClassAction on(Class<?> clazz) throws Exception {
        return new ClassAction(clazz);
    }

    public static class ClassAction {

        private Class<?> mClazz;

        private ClassAction(Class<?> clazz) throws Exception {
            mClazz = clazz;
        }

        private ClassAction(String className) throws Exception {
            mClazz = Class.forName(className);
        }

        public FieldAction field(String fieldName) throws Exception {
            return new FieldAction(mClazz, fieldName);
        }

        public MethodAction method(String methodName, Class<?>... parametersClazz) throws Exception {
            return new MethodAction(mClazz, methodName, parametersClazz);
        }
    }

    public static class MethodAction {

        private Method mMethod;
        private Object mTarget;

        private MethodAction(Class<?> clazz, String methodName, Class<?>... parametersClazz) throws Exception {
            try {
                mMethod = clazz.getMethod(methodName, parametersClazz);
            } catch (NoSuchMethodException e) {
                mMethod = clazz.getDeclaredMethod(methodName, parametersClazz);
                mMethod.setAccessible(true);
            }
        }

        public MethodAction noTarget() {
            mTarget = null;
            return this;
        }

        public MethodAction target(Object target) {
            mTarget = target;
            return this;
        }

        public Object invoke(Object... args) throws Exception {
            return mMethod.invoke(mTarget, args);
        }
    }

    public static class FieldAction {

        private Field mField;
        private Object mTarget;

        private FieldAction(Class<?> clazz, String fieldName) throws Exception {
            try {
                mField = clazz.getField(fieldName);
            } catch (NoSuchFieldException e) {
                mField = clazz.getDeclaredField(fieldName);
                mField.setAccessible(true);
            }
        }

        public FieldAction target(Object target) {
            mTarget = target;
            return this;
        }

        public FieldAction noTarget() {
            mTarget = null;
            return this;
        }

        public void set(Object value) throws Exception {
            mField.set(mTarget, value);
        }

        public Object get() throws Exception {
            return mField.get(mTarget);
        }
    }
}
