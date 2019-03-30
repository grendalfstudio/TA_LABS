package com.grendalfstudio;

public class Node<T> {
    public Node<T> next;
    public Node<T> previous;
    public T value;

    public Node (T value,  Node next, Node previous){
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
