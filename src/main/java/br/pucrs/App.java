package br.pucrs;

import java.util.Arrays;
import java.util.Random;

public class App {
    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576};
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            iterations = 0;
            //mergeSort(array);
            maxValue(array);
            long endTime = System.currentTimeMillis();
            System.out.println("Size: " + size + ", Iterations: " + iterations + ", Time: " + (endTime - startTime) + " ms");
        }
    }
    //exercício 2
    public static long maxValue(int[] array){
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            iterations++;
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }
    // exercício 1
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            iterations++;
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            iterations++;
            result[k++] = left[i++];
        }
        while (j < right.length) {
            iterations++;
            result[k++] = right[j++];
        }
        return result;
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

}
