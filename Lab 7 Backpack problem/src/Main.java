import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);

        System.out.println("Enter number of items: ");
        int amount = sin.nextInt();

        System.out.println("\nEnter a size of backpack: ");
        int maxWeight = sin.nextInt();

        String[] names = new String[amount];
        int[] weights = new int[amount];
        int[] prices = new int[amount];

        System.out.println("\nEnter names of item, its weight and price by space:\n");
        for(int i = 0; i < amount; i++) {            // Filling the arrays
            String name = sin.next();
            if(name == "stop")
                break;

            int weight = sin.nextInt();
            int price = sin.nextInt();

            names[i] = name;
            weights[i] = weight;
            prices[i] = price;
        }

        Backpack backpack = new Backpack(amount, names, weights, prices, maxWeight);
    }
}

class Backpack {
    private int amount;
    private String[] names;
    private int[] weights;
    private int[] prices;
    private int maxWeight;
    private int[][] matrix;    // A main dynamic massive

    private void findAnswer(int k, int size) {
        if(matrix[k][size] == 0)
            return;
        if(matrix[k - 1][size] == matrix[k][size])
            findAnswer(k - 1, size);
        else
            findAnswer(k - 1, size - weight[k]);
        if A[k][s] == 0
        return
        if A[k - 1][s] == A[k][s]
        findAns(k - 1, s)
                else
        findAns(k - 1, s - w[k])
        ans.push(k)
    }
}

public Backpack(int amount, String[] names, int[] weights, int[] prices, int maxWeight) {
        this.amount = amount;
        this.names = names;
        this.weights = weights;
        this.prices = prices;
        this.maxWeight = maxWeight;
        matrix = new int[this.amount + 1][this.maxWeight + 1];
    }
public void findBestPack() {
        for(int i = 0; i < maxWeight; i++) {        // Fill first row by zeros
            matrix[0][i] = 0;
        }
        for(int i = 0; i < amount; i++) {           // Fill first column by zeros
            matrix[i][0] = 0;
        }
        for(int k = 1; k <= amount; k++) {          // Main algorithm
            for(int size = 1; size <= maxWeight; size++) {      // Checking every size of a backpack
                if(size >= weights[k]) {                         // If current item can be put into the backpack
                    matrix[k][size] =                           // Choose it or not
                    Math.max(matrix[k - 1][size], matrix[k - 1][size - weights[k]] + prices[k]);
                } else {                                        // Else don't put it
                    matrix[k][size] = matrix[k - 1][size];
                }
            }
        }

    public void findMaxPack()
    {

    }
    public void findIntersection()
    {

    }
}