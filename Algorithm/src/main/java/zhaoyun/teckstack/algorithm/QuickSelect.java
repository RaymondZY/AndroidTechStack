package zhaoyun.teckstack.algorithm;

import java.util.Arrays;
import java.util.Random;

public class QuickSelect {

    private Random random = new Random();

    private int select(int[] numbers, int left, int right, int index) {
        if (left < right) {
            swap(numbers, right, random.nextInt(right - left) + left);
        }
        int pivot = numbers[right];
        int pos = left;
        for (int i = left; i < right; i++) {
            if (numbers[i] <= pivot) {
                swap(numbers, i, pos);
                pos++;
            }
        }
        int pivotIndex = pos;
        swap(numbers, pivotIndex, right);
        if (index == pivotIndex) {
            return numbers[pivotIndex];
        } else if (index < pivotIndex) {
            return select(numbers, left, pivotIndex - 1, index);
        } else {
            return select(numbers, pivotIndex + 1, right, index);
        }
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }
        int result = new QuickSelect().select(numbers, 0, numbers.length - 1, 4);
        System.out.println(result);
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
