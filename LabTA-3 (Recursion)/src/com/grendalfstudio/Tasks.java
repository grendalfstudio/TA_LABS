package com.grendalfstudio;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.lang.Math;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Tasks {

    @Contract(pure = true)
    public static int iterativeSum(@NotNull int[] array){
        int sum = 0;
        for (int i: array){
            sum += i;
        }
        return sum;
    }

    public static int recursiveSum(int[] array, int size){
        if (size > 0){
            return array[size - 1] + recursiveSum(array, size - 1);
        } else {
            return array[size];
        }
    }

    public static void iterativeReverse(){
        System.out.println("Enter the number of numbers: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("Enter the numbers: ");
        int[] numbers = IntStream.range(0, num).map(i -> scanner.nextInt()).toArray();
        IntStream.iterate(num - 1, i -> i >= 0, i -> i - 1).map(i -> numbers[i]).forEach(System.out::println);
    }

    public static void recursiveReverse(int num){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (num == 1){
            System.out.println(number);
        } else {
            recursiveReverse(num -1);
            System.out.println(number);
        }
    }

    public static int ackermann(int first, int second){
        if (first < 0 || second < 0){
            throw new IllegalArgumentException("Must be non-negative");
        }

        if (first == 0){
            return second+1;
        } else if (second  == 0){
            return ackermann(first - 1, 1);
        } else {
            return ackermann(first -1, ackermann(first, second-1));
        }
    }

    public static int binomial(int m, int n){
        if (m > n || m < 0 || n < 0){
            throw new IllegalArgumentException("Must be non-negative and m less than n");
        }
        if (m == 0 || m == n){
            return 1;
        } else {
            return binomial(m, n-1) + binomial(m - 1, n -1);
        }
    }

    public static double recursiveRoot(int depth){
        double n = 6;
        double m = 2;
        return recursiveRootHid(depth, n, m);
    }

    private static double recursiveRootHid(int depth, double n, double m){
        if (depth == 1){
            return Math.sqrt(n + m);
        } else {
            return Math.sqrt((n + m* recursiveRootHid(depth - 1, n+1, m + 1) ));
        }
    }
}
