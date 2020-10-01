package zhaoyun.teckstack.java.concurrent;

import java.util.ArrayList;
import java.util.List;

public class ObservableObserver {

    private interface Observer {

        void onContentChanged(String newContent);
    }

    private interface Observable {

        void notifyChange(String newContent);

        void registerObserver(Observer observer);

        void unregisterObserver(Observer observer);
    }

    private static class ObservableImpl implements Observable {

        private final List<Observer> observerList;

        private ObservableImpl() {
            observerList = new ArrayList<>();
        }

        @Override
        public void notifyChange(String newContent) {
            synchronized (observerList) {
                for (Observer observer : observerList) {
                    observer.onContentChanged(newContent);
                }
            }
        }

        @Override
        public void registerObserver(Observer observer) {
            synchronized (observerList) {
                if (!observerList.contains(observer)) {
                    observerList.add(observer);
                }
            }
        }

        @Override
        public void unregisterObserver(Observer observer) {
            synchronized (observerList) {
                observerList.remove(observer);
            }
        }
    }

    private static class ObserverImpl implements Observer {

        private String name;

        private ObserverImpl(String name) {
            this.name = name;
        }

        @Override
        public void onContentChanged(String newContent) {
            System.out.println(name + " : onContentChanged() " + newContent);
        }
    }

    public static void main(String[] args) {
        Observable observable = new ObservableImpl();
        for (int i = 0; i < 3; i++) {
            Observer observer = new ObserverImpl("observer " + i);
            observable.registerObserver(observer);
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    observable.notifyChange("new content from " + Thread.currentThread().getName());
                }
            }).start();
        }
    }
}
