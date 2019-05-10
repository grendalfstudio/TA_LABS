package com.grendalfstudio;



public class CircularLinkedList<T>  {

    private Node<T> head;
    private Node<T> last;
    private int length;

    //special local variables
    private Node<T> current;
    private int count;

    public int getLength(){
        return length;
    }

    public CircularLinkedList() {
        this.length = 0;
        this.head = null;
        this.last = null;
    }

    public void show() {

        StringBuilder out = new StringBuilder();

        if (head != null) {
            current = head;
            out.append(current.value + " ");
            while (current.next != head) {
                current = current.next;
                out.append(current.value + " ");
            }
            System.out.println(out);
        }
        else {
            System.out.println("Empty");
        }
    }

    public void clear() {
        length = 0;
        head = null;
        last = null;
    }

    public void addToBegin(T value) {
        if (head == null) {
            head = last = new Node<T>(value, null, null);
        }
        else {
            head = new Node<>(value, head, last);
            head.next.previous = head;
            last.next = head;
        }
    }

    public void addToEnd(T value) {
        if (head == null) {
            head = last = new Node<T>(value, null, null);
        }
        else {
            last = new Node(value, head, last);
            last.previous.next = last;
            head.previous = last;
        }
    }

    public void insert(T value, int i) {
        if (head == null) {
            head = last = new Node<>(value, null, null);
        }
        if (i == 0) {
            head = new Node<>(value, head, last);
            head.next.previous = head;
            last.next = head;
            return;
        }
        else {
            count = 0;
            current = head;
            do {
                count ++;
                if(count == i) {
                    current.next = new Node<>(value, current.next, current);
                    if (current.next.next == head) {
                        last = current.next;
                        head.previous = last;
                        last.next = head;
                    }
                    else {
                        current.next.next.previous = current.next;
                    }
                    break;
                }
                current = current.next;
            }while (current != head);
        }
    }

    public void pop() {
        if (head == null) {
            return;
        }
        head = head.next;
        head.previous = last;
        last.next = head;
    }

    public void delLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        current = last.previous;
        current.next = head;
        last = current;
        head.previous = last;
    }

    public void delByIndex(int i) {
        if (head == null) {
            return;
        }
        count = 0;
        current = head;
        if (i == 0) {
            head = head.next;
            head.previous = last;
            last.next = head;
            return;
        }
        do {
            if (count == i) {
                if(current.next == head) {
                    last = current.previous;
                    current.previous.next = head;
                    head.previous = last;
                    return;
                }
                current.previous.next = current.next;
                current.next.previous = current.previous;
                break;
            }
            current = current.next;
            count ++;
        }while (current != null);
    }

    public void changeFirst(T value) {
        if (head == null) {
            return;
        }
        head.value = value;
    }

    public void changeLast(T value) {
        if (head == null) {
            return;
        }

        last.value = value;
    }

    public void changeByIndex(int i, T value) {
        if (head == null) {
            return;
        }
        current = head;
        count = 0;
        do {
            if (count == i) {
                current.value = value;
            }
            current = current.next;
            count ++;
        }while(current != head);
    }

    public void getSum() {
        int sum = 0;
        if (head == null) {
            return;
        }
        current = head;
        do {
            sum += Integer.parseInt(String.valueOf(current.value));
            current = current.next;
        }while (current != null);
        System.out.println(sum);
    }

    public int getIndex(T value){
        if (this.head == null){
            return -1;
        }
        int index = 0;
        current = head;
        do {
            if (current.value == value){
                break;
            }
            if (current == last && current.value != value){
                return -1;
            }
            current = current.next;
            index++;
        } while (current != head);
        return index;
    }
}


