package com.orugasumu.android.sproject;

public class Node {                                                     //This Node class was created to be used with Dijkstra's Algorithm
    public int source;                                                  //and the Linked List. Now contains WEIGHT!
    public int weight;
    public Node prev;
    public Node next;

    public Node(int source, int weight, Node next) {                    //parameterized constructor
        this.source = source;
        this.weight = weight;
        this.next = next;
    }

    public Node(int source, int weight, Node _head, Node _tail) {       //parameterized constructor SOURCE = vertex insert, WEIGHT of vertex
        this.source = source;
        this.weight = weight;
        prev = _head;
        next = _tail;
    }
}
