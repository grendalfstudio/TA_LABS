package com.grendalfstudio;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        MultiLinkedList<Integer> listInteger = new MultiLinkedList<>();
        Random random = new Random(System.nanoTime());

        System.out.println("Filling listInteger...");
        for (int i = 0; i<100000; i++){
            listInteger.addToEnd(random.nextInt());
        }

        System.out.println("Replacements in listInteger...");
        long tStartReplace = System.nanoTime();
        for (int i = 0; i<10000; i++){
            listInteger.changeByIndex(random.nextInt(), random.nextInt(500000));
        }
        long tEndReplace = System.nanoTime();

        System.out.println("Getting indexes in listInteger...");
        long tStartSearch = System.nanoTime();
        for (int i = 0; i<10000; i++){
            listInteger.getIndex(random.nextInt());
        }
        long tEndSearch = System.nanoTime();

        double millisecondsReplace;
        double millisecondsSearch;
        millisecondsSearch = (double)(tEndSearch - tStartSearch) / 1_000_000.0;
        millisecondsReplace = (double)(tEndReplace - tStartReplace) / 1_000_000.0;

        System.out.println("Time spent for replacements in milliseconds: " + millisecondsReplace);
        System.out.println("Time spent for searching in milliseconds: " + millisecondsSearch);
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();

        System.out.println("Filling circularLinkedList...");
        for (int i = 0; i<100000; i++){
            circularLinkedList.addToEnd(random.nextInt());
        }

        System.out.println("Replacements in circularLinkedList...");
        tStartReplace = System.nanoTime();
        for (int i = 0; i<10000; i++){
            circularLinkedList.changeByIndex(random.nextInt(), random.nextInt(200000));
        }
        tEndReplace = System.nanoTime();

        System.out.println("Getting indexes in circularLinkedList...");
        tStartSearch = System.nanoTime();
        for (int i = 0; i<10000; i++){
            circularLinkedList.getIndex(random.nextInt());
        }
        tEndSearch = System.nanoTime();

        millisecondsSearch = (double)(tEndSearch - tStartSearch) / 1_000_000.0;
        millisecondsReplace = (double)(tEndReplace - tStartReplace) / 1_000_000.0;

        System.out.println("Time spent for replacements in milliseconds: " + millisecondsReplace);
        System.out.println("Time spent for searching in milliseconds: " + millisecondsSearch);
    }
}
