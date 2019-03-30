import java.util.Random;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Random random = new Random(System.nanoTime());

        /* Pushing time */
        long begin = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            list.pushAt(random.nextInt(i + 1), random.nextInt(1_000_000));
        }
        long end = System.nanoTime();
        System.out.println("Pushing time: " + (end - begin) / 1_000_000_000.0);

        /* Replacing time */
        begin = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.replaceAt(random.nextInt(10_000), random.nextInt(1_000_000));
        }
        end = System.nanoTime();
        System.out.println("Replacing time: " + (end - begin) / 1_000_000_000.0);

        /* Sum time */
        begin = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            long result = list.countSum();
        }
        end = System.nanoTime();
        System.out.println("Sum time: " + (end - begin) / 1_000_000_000.0);

        /* Finding index of a known value time*/
        begin = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.findIndexOf(random.nextInt(1_000_000));
        }
        end = System.nanoTime();
        System.out.println("Finding index time: " + (end - begin) / 1_000_000_000.0);

        /* Deleting time */
        begin = System.nanoTime();
        for (int i = 10_000; i > 0; i--) {
            list.popAt(random.nextInt(i));
        }
        end = System.nanoTime();
        System.out.println("Deleting time: " + (end - begin) / 1_000_000_000.0);
    }
}

/** Struct with data field 'data' and link field 'next' */
class Node {
    public int data;    // number
    public Node next;   // link to next node
}

class LinkedList {
    private Node head;
    private int size = 0;

    /* Add 'value' to the beginning of list */
    void pushFront(int value) {
        Node newNode = new Node();
        newNode.data = value;
        newNode.next = head;
        head = newNode;
        size++;
    }

    /* Add 'value' to the ending of the list */
    void pushBack(int value) {
        pushAt(size, value);
    }

    /* Add 'value' on the 'index' place
    (indexation starts with '0') */
    void pushAt(int index, int value) {
        if (index < 0 || index > size) {        // Checking if the 'index' is correct
            System.out.println("Cannot push the "+ value +" to the list. Incorrect index");
            return;
        }
        else if (index == 0) {                  // An occasion, when 'value' inserts before 'head'
            pushFront(value);
            return;
        }

        Node temp = head;
        while (index > 1) {                     // Moving to the index we are looking for
            index--;
            temp = temp.next;
        }
        Node newNode = new Node();
        newNode.data = value;
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    /* Deleting the first element in the list */
    void popFront() {
        if(size == 0) {         // Checking if the list is empty
            System.out.println("Cannot delete the element. There is no elements");
            return;
        }

        head = head.next;
        size--;
    }

    /* Deleting the last element in the list */
    void popBack(){
        popAt(size - 1);
    }

    /* Deleting the 'index' element in the list
    (indexation starts with '0') */
    void popAt(int index){
        if(size == 0) {                             // Checking if the list is empty
            System.out.println("Cannot delete the element. There is no elements");
            return;
        }
        else if (index < 0 || index > size - 1) {   // Checking if the 'index' is correct
            System.out.println("Cannot delete the element from the list. Incorrect index");
            return;
        }
        else if (index == 0){                       // An occasion, when we delete the first element in the list
            popFront();
            return;
        }

        Node temp = head;
        while(index > 1) {                          // Moving to the index that is previous to that we are looking for
            index--;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    /* Replacing the 'index' element with the 'value'
    (indexation starts with '0') */
    void replaceAt(int index, int value){
        if (index < 0 || index > size - 1){         // Checking if the 'index' is correct
            System.out.println("Cannot change the value of "+ value +" in the list. Incorrect index");
            return;
        }

        Node temp = head;
        while (index > 0) {                         // Moving to the index we are looking for
            index--;
            temp = temp.next;
        }
        temp.data = value;
    }

    /* Counts sum of the list */
    int countSum() {
        int sum = 0;
        Node temp = head;
        while (temp != null) {
            sum += temp.data;
            temp = temp.next;
        }
        return sum;
    }

    /* Finds index of the 'value' element (indexation starts with '0') */
    int findIndexOf(int value){
        int index = 0;
        Node temp = head;
        while (temp != null) {
            if(temp.data == value){
                return index;
            }

            index++;
            temp = temp.next;
        }
        return -1;
    }

    /* Prints the list on the screen */
    void printList() {
        System.out.print("List: ");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
/*
        list.pushAt(0, 0);
        list.printList();

        list.pushBack(-1);
        list.printList();

        list.pushFront(1);
        list.printList();

        list.pushAt(1,4);
        list.printList();

        list.popAt(2);
        list.printList();

        list.pushFront(4);
        list.printList();

        list.pushAt(4, 5);
        list.printList();

        list.popFront();
        list.printList();

        list.popBack();
        list.printList();

        list.pushBack(6);
        list.printList();

        list.pushBack(7);
        list.printList();

        list.replaceAt(1,10);
        list.printList();

        list.replaceAt(2,11);
        list.printList();

        list.replaceAt(0,12);
        list.printList();

        System.out.println("Sum: " + list.countSum());
        System.out.println("Index 11: " + list.findIndexOf(11));

        list.popFront();
        list.printList();

        list.popBack();
        list.printList();
        */