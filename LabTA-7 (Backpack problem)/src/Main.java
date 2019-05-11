import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Initializing

        /* Input data:
         * 5
         * 13
         * Pen 3 1
         * Pencilbox 4 6
         * Eraser 5 4
         * Cup 8 7
         * Phone 9 6
         */
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int amount = scan.nextInt();

        System.out.print("Enter a size of backpack: ");
        int maxWeight = scan.nextInt();

        Item[] items = new Item[amount];

        System.out.println("Enter name of item, its weight and price by space:");
        for (int i = 0; i < amount; i++) {            // Filling the arrays
            String name = scan.next();
            int weight = scan.nextInt();
            int price = scan.nextInt();
            items[i] = new Item(name, weight, price);
        }
        Backpack backpack = new Backpack(amount, items, maxWeight);
        backpack.findBestPackDynamic();
        backpack.findBestPackGreedy();
        backpack.findMaxPack();
        backpack.findIntersection();
    }
}

class Item {
    public String name;
    public int weight;
    public int price;
    Item(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }
}

class Backpack {
    private int amount;
    private Item[] items;
    private int maxWeight;
    private int[][] matrix;    // A main dynamic massive
    private ArrayList<Integer> answerDynamic;
    private ArrayList<Integer> answerGreedy;
    private ArrayList<Integer> answerMaxPack;
    private ArrayList<Integer> answerIntersection1;
    private ArrayList<Integer> answerIntersection2;

    public Backpack(int amount, Item[] items, int maxWeight) {
            this.amount = amount;
            this.items = items.clone();
            this.maxWeight = maxWeight;
            matrix = new int[this.amount + 1][this.maxWeight + 1];
            answerDynamic = new ArrayList<>();
            answerGreedy = new ArrayList<>();
            answerMaxPack = new ArrayList<>();
            answerIntersection1 = new ArrayList<>();
            answerIntersection2 = new ArrayList<>();
        }

    public void findBestPackDynamic() {
        for (int i = 0; i <= maxWeight; i++) {        // Fill first row by zeros
            matrix[0][i] = 0;
        }
        for (int i = 0; i <= amount; i++) {           // Fill first column by zeros
            matrix[i][0] = 0;
        }
        for (int k = 1; k <= amount; k++) {          // Main algorithm
            for (int weight = 1; weight <= maxWeight; weight++) {  // Checking every weight of a backpack
                if (weight >= items[k - 1].weight) {                   // If current item can be put into the backpack
                    matrix[k][weight] =                            // Choose it or not
                            Math.max(matrix[k - 1][weight],
                                    matrix[k - 1][weight - items[k - 1].weight] + items[k - 1].price);
                } else {                                           // Else don't put it
                    matrix[k][weight] = matrix[k - 1][weight];
                }
            }
        }
        findAnswerDynamic(amount, maxWeight);
        System.out.println("==========Dynamic programming results==========");
        printBestPack(answerDynamic);
    }
    private void findAnswerDynamic(int k, int size) {
        if (matrix[k][size] == 0)
            return;
        if (matrix[k - 1][size] == matrix[k][size])
            findAnswerDynamic(k - 1, size);
        else {
            findAnswerDynamic(k - 1, size - items[k - 1].weight);
            answerDynamic.add(k - 1);
        }
    }

    public void findBestPackGreedy() {
        List<Item> sortedItems = Arrays.asList(items.clone());
        Collections.sort(sortedItems, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Item first = (Item) o1;
                        Item second = (Item) o2;

                        Integer firstPrice = first.price;
                        Integer secondPrice = second.price;
                        if (secondPrice.compareTo(firstPrice) != 0) {
                            return secondPrice.compareTo(firstPrice);
                        } else {
                            Integer firstWeight = first.weight;
                            Integer secondWeight = second.weight;
                            return firstWeight.compareTo(secondWeight);
                        }
                    }
                } );
        int freeSpace = maxWeight;
        int sumPrice = 0;
        for(int i = 0; i < amount; i++) {
            Item current = sortedItems.get(i);
            if(freeSpace - current.weight < 0)
                continue;
            freeSpace -= current.weight;

            for(int j = 0; j < amount; j++) {
                if(items[j].equals(current)) {
                    answerGreedy.add(j);
                    break;
                }
            }
        }
        System.out.println("==========Greedy algorithm results==========");
        printBestPack(answerGreedy);
    }

    private void printBestPack(ArrayList<Integer> answer) {
        int sumWeight = 0;
        int sumPrice = 0;
        ArrayList<String> finalNames = new ArrayList<>();
        for (Integer iter : answer) {
            finalNames.add(items[iter].name);
            sumWeight += items[iter].weight;
            sumPrice += items[iter].price;
        }
        String output = "Size: " + answer.size() + "; Weight: " + sumWeight + "; Price: " + sumPrice + ";";
        System.out.println(output);
        System.out.println(finalNames);
    }

    public void findMaxPack() {
        List<Item> sortedItems = Arrays.asList(items.clone());
        Collections.sort(sortedItems, new Comparator() {
            public int compare(Object o1, Object o2) {
                Item first = (Item) o1;
                Item second = (Item) o2;
                Integer firstWeight = first.weight;
                Integer secondWeight = second.weight;
                return firstWeight.compareTo(secondWeight);
            }
        } );
        int freeSpace = maxWeight;
        int itemsNumber = 0;
        while(itemsNumber < amount && freeSpace - sortedItems.get(itemsNumber).weight >= 0) {
            freeSpace -= sortedItems.get(itemsNumber).weight;
            for(int j = 0; j < amount; j++) {
                if(items[j].equals(sortedItems.get(itemsNumber))) {
                    answerMaxPack.add(j);
                    break;
                }
            }
            itemsNumber++;
        }
        System.out.println("==========Max pack results==========");
        printBestPack(answerMaxPack);
    }

    public void findIntersection() {
        int[] intersection1 = new int[amount];
        int[] intersection2 = new int[amount];

        for (int i = 0; i < amount; i++) {
            intersection1[i] = 0;
            intersection2[i] = 0;

        }
        for (Integer iter : answerDynamic) {
            intersection1[iter]++;
        }
        for (Integer iter : answerGreedy) {
            intersection2[iter]++;
        }
        for (Integer iter : answerMaxPack) {
            intersection1[iter]++;
            intersection2[iter]++;
        }
        for (int i = 0; i < amount; i++) {
            if (intersection1[i] == 2) {
                answerIntersection1.add(i);
            }
            if (intersection2[i] == 2) {
                answerIntersection2.add(i);
            }
        }
        System.out.println("==========Intersection of dynamic and max pack results==========");
        if(answerIntersection1.isEmpty() == false) {
            printBestPack(answerIntersection1);
        } else {
            System.out.println("There is no intersection");
        }
        System.out.println("==========Intersection of greedy and max pack results==========");
        if(answerIntersection2.isEmpty() == false) {
            printBestPack(answerIntersection2);
        } else {
            System.out.println("There is no intersection");
        }
    }
}