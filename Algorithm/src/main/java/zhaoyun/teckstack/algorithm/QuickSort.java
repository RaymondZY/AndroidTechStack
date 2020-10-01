package zhaoyun.teckstack.algorithm;

import java.util.Random;

public class QuickSort {

    private void sort(int[] numArray, int left, int right) {
        int pivot = numArray[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (numArray[j] >= pivot && i < j) {
                j--;
            }
            while (numArray[i] <= pivot && i < j) {
                i++;
            }
            if (i < j) {
                swap(numArray, i, j);
            }
        }
        swap(numArray, left, i);

        if (left < i - 1) {
            sort(numArray, left, i - 1);
        }
        if (j + 1 < right) {
            sort(numArray, j + 1, right);
        }
    }

    private void swap(int[] numArray, int i, int j) {
        int temp = numArray[i];
        numArray[i] = numArray[j];
        numArray[j] = temp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] numArray = new int[100000];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = random.nextInt(100);
        }
        new QuickSort().sort(numArray, 0, numArray.length - 1);
        boolean right = true;
        for (int i = 0; i < numArray.length - 1; i++) {
            if (numArray[i] > numArray[i + 1]) {
                right = false;
                break;
            }
        }
        System.out.println(right);
    }
}
