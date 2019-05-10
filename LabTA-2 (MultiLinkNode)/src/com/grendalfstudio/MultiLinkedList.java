package com.grendalfstudio;

public class MultiLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int length;

    //special local variables
    private Node<T> current;
    private Node nullNode = null;
    private int count;

    public MultiLinkedList (){
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public int getLength() {
        return length;
    }

    //print list to console
    public void show(){
        StringBuilder out = new StringBuilder();
        if (this.first != null){                                         //if list not empty
            current = this.first;                                        //set the first element of list as current
            out.append(String.valueOf(current.value).concat(", "));      //add value of current element to result
            while (current.next != null){                                //while current isn't last
                current = current.next;                                  //go to next element
                out.append(String.valueOf(current.value).concat(", "));  //and add it's value to result
            }
             System.out.println("MultiLinkedList [ " + out + "]");
        } else  {                                                   //if list empty
            System.out.println("MultiLinkedList []");               //just print empty list
        }
    }

    //clear list
    public void clear(){
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public void addToEnd(T value){
        this.length++;
        if (this.first == null){                                                //here and thereafter this is checking for empty list
           this.first = this.last = new Node<>(value, nullNode, nullNode);        //create new node and set it as first and last
        } else {
            this.last.next = this.last = new Node<>(value, nullNode, this.last);  //create new node and set it as last
        }
    }

    public void addToBegin(T value){
        this.length++;
        if (this.first == null){
            this.last = this.first = new Node<>(value, nullNode, nullNode);
        } else {
            this.first = new Node<>(value, this.first, nullNode);
        }
    }

    public void insert(T value, int index ){
        if (this.first == null){
            this.last = this.first = new Node<>(value, this.first, nullNode);
        }
        if (index == 0){
            this.first = new Node<>(value, this.first, nullNode);
        }
        current = this.first;
        count = 0;
        while (current != null){                                                     //while current isn't last
            count++;                                                                 //increment current index
            if (count == index){                                                     //if isFound
                current.next = new Node<>(value, current.next, current.next.previous); //create new node and set it as next
                if (current.next.next == null){                                      //and if it's last
                    this.last = current.next;                                        //set it as last
                }
                break;
            }
            current = current.next;                 //go to next element
        }
    }

    public void pop(){
        if (this.first == null){
            return;
        }
        if (this.first.next == null){
            this.clear();
        } else {
            this.first.next.previous = null;                //set the prevPointer of second node to null
            this.first = this.first.next;                   //set the second element as first
            this.length--;
        }
    }

    public void delLast(){
        if (this.first == null){
            return;
        }
        if (this.first.next == null){
            this.clear();
        } else {
            this.last.previous.next = null;         //set nextPointer of second last element to null
            this.last = this.last.previous;         //set second last node as last
            length--;
        }
    }

    public void delByIndex(int index){
        if (this.first == null){
            return;
        }
        current = this.first;
        count = 0;
        if (index == 0){
            this.first = this.first.next;
            return;
        }
        Node<T> old = null;
        while (current != null){
            if (count == index){
                if (current.next == null){
                    this.last = current;
                }
                old.next = current.next;
                current.previous = current.next;
                break;
            }
            old = current;
            current = current.next;
            count++;
        }
    }

    public void changeFirst(T value){
        if (this.first == null) {
           return;
        }
        this.first.value = value;
    }

    public void changeLast(T value){
        if (this.first == null){
            return;
        }
        this.last.value = value;
    }

    public void changeByIndex(T value, int index){
        if (this.first == null){
            return;
        }
        if (index == 0){
            this.first.value = value;
        }
        current = this.first;
        count = 0;
        while (current != null){
            if (count == index){
                current.value = value;
                break;
            }
            current = current.next;
            count++;
        }
    }

    public long getSumLong(){
        long sum = 0;
        if (this.first == null){
            return sum;
        }
        current = this.first;
        while (current != null){
            sum += Long.valueOf(String.valueOf(current.value));
            current = current.next;
        }
        return sum;
    }

    public float getSumFloat(){
        float sum = 0;
        if (this.first == null){
            return sum;
        }
        current = this.first;
        while (current != null){
            sum += Float.valueOf(String.valueOf(current.value));

            current = current.next;
        }
        return sum;
    }

    public double getSumDouble(){
        double sum = 0;
        if (this.first == null){
            return sum;
        }
        current = this.first;
        while (current != null){
            sum += Double.valueOf(String.valueOf(current.value));
            current = current.next;
        }
        return sum;
    }

    public int getIndex(T value){
        if (this.first == null){
            return -1;
        }
        int index = 0;
        current = this.first;
        while (current != null){
            if (current.value == value){
                break;
            }
            if (current.next == null && current.value != value){
                return -1;
            }
            current = current.next;
            index++;
        }
        return index;
    }
}

