package zhaoyun.teckstack.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumer {

    private static class Consumer {

        private final String name;
        private final List<Integer> containerList;

        private Consumer(String name, List<Integer> containerList) {
            this.name = name;
            this.containerList = containerList;
        }

        private void consume() {
            synchronized (containerList) {
                System.out.println(name + " : Monitor.enter()");
                while (containerList.size() == 0) {
                    try {
                        System.out.println(name + " : Wait");
                        containerList.wait();
                        System.out.println(name + " : Wait finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " : Consuming ..");
                int value = containerList.get(containerList.size() - 1);
                System.out.println(name + " : Consumed " + value);
                containerList.remove(containerList.size() - 1);
                containerList.notifyAll();
                System.out.println(name + " : Monitor.exit()");
            }
        }
    }

    private static class Producer {

        private final String name;
        private final List<Integer> containerList;
        private final int capacity;

        private Producer(String name, List<Integer> containerList, int capacity) {
            this.name = name;
            this.containerList = containerList;
            this.capacity = capacity;
        }

        private void produce() {
            synchronized (containerList) {
                System.out.println(name + " : Monitor.enter()");
                while (containerList.size() == capacity) {
                    try {
                        System.out.println(name + " : Wait");
                        containerList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " : Producing..");
                int value = containerList.size();
                System.out.println(name + " : Produced " + value);
                containerList.add(value);
                containerList.notifyAll();
                System.out.println(name + " : Monitor.exit()");
            }
        }
    }

    private static class ConsumerThread extends Thread {

        private final Consumer consumer;

        private ConsumerThread(Consumer consumer) {
            this.consumer = consumer;

        }

        @Override
        public void run() {
            Random random = new Random();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(random.nextInt(500) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                consumer.consume();
            }
        }
    }

    private static class ProducerThread extends Thread {

        private final Producer producer;

        private ProducerThread(Producer producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(random.nextInt(500) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                producer.produce();
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> containerList = new ArrayList<>();
        List<Thread> workThreadList = new ArrayList<>();

        ConsumerThread consumerThread1 = new ConsumerThread(new Consumer("Consumer 1", containerList));
        consumerThread1.start();
        workThreadList.add(consumerThread1);

        ConsumerThread consumerThread2 = new ConsumerThread(new Consumer("Consumer 2", containerList));
        consumerThread2.start();
        workThreadList.add(consumerThread2);

        ConsumerThread consumerThread3 = new ConsumerThread(new Consumer("Consumer 3", containerList));
        consumerThread3.start();
        workThreadList.add(consumerThread3);

        ProducerThread producerThread1 = new ProducerThread(new Producer("Producer 1", containerList, 5));
        producerThread1.start();
        workThreadList.add(producerThread1);

        ProducerThread producerThread2 = new ProducerThread(new Producer("Producer 2", containerList, 5));
        producerThread2.start();
        workThreadList.add(producerThread2);

        ProducerThread producerThread3 = new ProducerThread(new Producer("Producer 3", containerList, 5));
        producerThread3.start();
        workThreadList.add(producerThread3);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        for (Thread thread : workThreadList) {
//            thread.interrupt();
//        }
    }
}
