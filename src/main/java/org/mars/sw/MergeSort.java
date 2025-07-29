package org.mars.sw;

import java.util.Arrays;

public class MergeSort {

    // Main method to initiate the merge sort process
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return; // Already sorted or empty
        }

        int n = array.length;
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < n ; i++) {
            right[i] = array[i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(array,left,right);

    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            }else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Method to merge two sorted arrays into a single sorted array
    // Example usage
    public static void main(String[] args) {
        int[] data = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println();

        mergeSort(data);

        System.out.println("Sorted array:");
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
