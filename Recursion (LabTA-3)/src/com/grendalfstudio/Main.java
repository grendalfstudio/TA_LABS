package com.grendalfstudio;

public class Main {



    public static void main(String[] args) {
	    int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i*10;
        }
        System.out.println(Tasks.iterativeSum(array));
        System.out.println(Tasks.recursiveSum(array, 10));
        System.out.println(Tasks.binomial(2, 10));
        System.out.println(Tasks.recursiveRoot(3));
        Tasks.iterativeReverse();
        Tasks.recursiveReverse(5);
        System.out.println(Tasks.ackermann(1, 1));
        System.out.println(Tasks.ackermann(2, 2));
        System.out.println(Tasks.ackermann(3, 3));
        System.out.println(Tasks.ackermann(3, 12));
    }
}
