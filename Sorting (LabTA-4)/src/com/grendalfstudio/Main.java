package com.grendalfstudio;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        int size = 100000;
        int arr[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(1000);
        }

        int arrBubble[] = arr.clone();
        int arrQuick[] = arr.clone();
        int arrCounting[] = arr.clone();

        System.out.println("Bubble sort:");
        executeBubble(arrBubble);
        arrBubble = arr.clone();
        executeBubble(arrBubble);

        System.out.println("Quick sort:");
        executeQuick(arrQuick);
        arrQuick = arr.clone();
        executeQuick(arrQuick);

        System.out.println("Counting sort:");
        executeCounting(arr);
        arrCounting = arr.clone();
        executeCounting(arr);
    }

    public static void executeBubble(int arr[]){
        long tStart = System.nanoTime();
        Sorter.bubbleSort(arr);
        long tEnd = System.nanoTime();
        long time = tEnd - tStart;
        System.out.println(arr.length + ": " + time + " ns");
    }

    public static void executeQuick(int arr[]){
        long tStart = System.nanoTime();
        Sorter.quickSort(arr, 0, arr.length - 1);
        long tEnd = System.nanoTime();
        long time = tEnd - tStart;
        System.out.println(arr.length + ": " + time + " ns");
    }

    public static void executeCounting(int arr[]){
        long tStart = System.nanoTime();
        Sorter.countingSort(arr);
        long tEnd = System.nanoTime();
        long time = tEnd - tStart;
        System.out.println(arr.length + ": " + time + " ns");
    }
}
