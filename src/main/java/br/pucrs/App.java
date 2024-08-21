package br.pucrs;

import java.util.Arrays;
import java.util.Random;
import static java.lang.Math.max;

public class App {
    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576};
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            iterations = 0;
            mergeSort(array);
            //maxValue1(array);
           // maxValue2(array,0,array.length-1);
            long endTime = System.currentTimeMillis();
            System.out.println("Size: " + size + ", Iterations mergeSort: " + iterations + ", Time: " + (endTime - startTime) + " ms");
        }
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            iterations = 0;
            //mergeSort(array);
            maxValue1(array);
            //maxValue2(array,0,array.length-1);
            long endTime = System.currentTimeMillis();
            System.out.println("Size: " + size + ", Iterations maxValue1: " + iterations + ", Time: " + (endTime - startTime) + " ms");
        }
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            iterations = 0;
            //mergeSort(array);
            //maxValue1(array);
            maxValue2(array,0,array.length-1);
            long endTime = System.currentTimeMillis();
            System.out.println("Size: " + size + ", Iterations maxValue2: " + iterations + ", Time: " + (endTime - startTime) + " ms");
        }
        int[] bitSizes = {4, 16, 64};
        Random random = new Random();
        for (int bits : bitSizes) {
            long x = random.nextLong() & ((1L << bits) - 1);
            long y = random.nextLong() & ((1L << bits) - 1);
            long startTime = System.currentTimeMillis();
            iterations = 0;
            long result = multiply(x, y, bits);
            long endTime = System.currentTimeMillis();
            System.out.println("Bits: " + bits + ", Iterations Multipy: " + iterations + ", Time: " + (endTime - startTime) + " ms, Result: " + result);
        }
    }
    public static long multiply(long x, long y, int n) {
        if (n == 1) {
            iterations++;
            return x * y;
        } else {
            int m = (n + 1) / 2;
            long a = x >> m;
            long b = x & ((1L << m) - 1);
            long c = y >> m;
            long d = y & ((1L << m) - 1);
            long e = multiply(a, c, m);
            long f = multiply(b, d, m);
            long g = multiply(b, c, m);
            long h = multiply(a, d, m);
            return (e << (2 * m)) + ((g + h) << m) + f;
        }
    }
    public static long maxValue1(int[] array){
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            iterations++;
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }
    public static long maxValue2(int[] A,int init, int end){
        if (end - init <= 1){
            iterations++;
            return max(A[init],A[end]);
        }
        else {
            int m = (init + end)/2;
            long v1 = maxValue2(A,init,m);
            long v2 = maxValue2(A,m+1,end);
            return max(v1,v2);
        }
    }

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
