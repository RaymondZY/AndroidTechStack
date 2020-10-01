package zhaoyun.teckstack.algorithm;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {

    private int[] numArray;
    private int size;

    private HeapSort() {
        numArray = new int[64];
        size = 0;
    }

    private void sort(int[] nums, int left, int right) {
        for (int i = left; i <= right; i++) {
            push(nums[i]);
        }
        for (int i = left; i <= right; i++) {
            nums[i] = pop();
        }
    }

    private void push(int value) {
        ensureCapacity(size + 1);
        numArray[size++] = value;
        int pos = size - 1;
        while (pos != 0) {
            int father = (pos - 1) / 2;
            if (numArray[pos] < numArray[father]) {
                swap(numArray, pos, father);
                pos = father;
            } else {
                break;
            }
        }
    }

    private int pop() {
        int result = numArray[0];
        numArray[0] = numArray[--size];
        int pos = 0;
        while (pos * 2 + 1 < size) {
            int son = pos * 2 + 1;
            if (son + 1 < size && numArray[son + 1] < numArray[son]) {
                son++;
            }
            if (numArray[pos] > numArray[son]) {
                swap(numArray, pos, son);
                pos = son;
            } else {
                break;
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void ensureCapacity(int capacity) {
        int max = numArray.length;
        if (max < capacity) {
            max <<= 1;
            numArray = Arrays.copyOf(numArray, max);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] numArray = new int[10];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = random.nextInt(100);
        }
        int[] copy = Arrays.copyOf(numArray, numArray.length);
        new HeapSort().sort(numArray, 0, numArray.length - 1);
        Arrays.sort(copy);
        boolean right = true;
        for (int i = 0; i < numArray.length - 1; i++) {
            if (numArray[i] != copy[i]) {
                right = false;
                break;
            }
        }
        System.out.println(right);
    }
}
