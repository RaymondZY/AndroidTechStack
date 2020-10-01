package zhaoyun.teckstack.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LRUCache<K, V> {

    private Map<K, Node<K, V>> map;
    private int size;
    private int capacity;
    private Node<K, V> head, tail;

    private static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized V put(K key, V value) {
        V oldValue = null;
        if (map.containsKey(key)) {
            Node<K, V> oldNode = map.get(key);
            oldValue = oldNode.value;
            oldNode.value = value;
            moveToHead(oldNode);
        } else {
            Node<K, V> newNode = new Node<>(key, value);
            addToHead(newNode);
            map.put(key, newNode);
            size++;
            if (size > capacity) {
                Node<K, V> removedNode = trimTail();
                map.remove(removedNode.key);
            }
        }
        return oldValue;
    }

    public synchronized V get(K key) {
        if (map.containsKey(key)) {
            Node<K, V> oldNode = map.get(key);
            moveToHead(oldNode);
            return oldNode.value;
        }
        return null;
    }

    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(Node<K, V> node) {
        remove(node);
        addToHead(node);
    }

    private Node<K, V> trimTail() {
        Node<K, V> node = tail.prev;
        remove(tail.prev);
        return node;
    }

    private void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(4);
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            new Thread(
                    () -> {
                        while (true) {
                            Integer key = random.nextInt(10);
                            Integer value = cache.get(key);
                            System.out.println(Thread.currentThread().getName() + " get(" + key + ") = " + value);
                            key = random.nextInt(10);
                            System.out.println(Thread.currentThread().getName() + " put(" + key + ", " + key + ")");
                            cache.put(key, key);
                        }
                    },
                    "Thread " + i
            ).start();
        }
    }
}
