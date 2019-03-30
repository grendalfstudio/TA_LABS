package com.grendalfstudio;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Sorter {
    public static void  bubbleSort(@NotNull int array[]){
        int n = array.length;
        for (int i = 0; i < array.length; i++){
            for (int j =1; j < array.length; j++){
                if (array[j-1] > array[j]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(@NotNull int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    public static void countingSort(int array[]){
        int max = getMax(array);
        int[] count = new int[max + 1];
        int[] output = new int[array.length];
        for (int i: array){
            i = 0;
        }

        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;
        }

        for (int i = 1; i < max + 1; i++){
            count[i] += count[i-1];
        }

        for (int i = array.length-1; i>=0; --i){
            output[count[array[i]]-1] = array[i];
            --count[array[i]];
        }

        for (int i = 0; i < array.length; i++){
            array[i] = output[i];
        }
    }

    @Contract(pure = true)
    private static int getMax(@NotNull int arr[]){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
}

