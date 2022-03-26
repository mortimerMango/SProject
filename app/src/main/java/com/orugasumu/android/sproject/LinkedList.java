package com.orugasumu.android.sproject;
public class LinkedList {                                                           //Our Linked List Class, now contains getWeight()!
                                                                                    //This linked list was created to use be used with Dijkstra's Algorithm
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {//constructor

        head = tail;
    }

    public LinkedList(int source, int weight) {//parameterized constructor

        pushFront(source, weight);
    }


    public void pushFront(int source, int weight) {                             //Push node to the front of Linked List
        if(this.head == null) {                                                 //IF there was nothing in there then make that the head - tail
            this.head = new Node(source, weight, null, null);
            this.tail = this.head;
            size++;
        }
        else {
            Node temp = new Node(source, weight, null, this.head);       //else link the new node as the new head and link the previous node to it
            this.head.prev = temp;
            this.head = temp;
            size++;
        }
    }//pushFront()

    public void pushBack(int source, int weight) {                              //Push the new node to the tail end of the linked list
        if(this.tail == null) {                                                 //If there aren't any node then use push Front b/c it would do the same thing
            pushFront(source, weight);//same thing as pushFront for first Node
        }
        else {
            Node temp = new Node(source, weight, this.tail, null);
            this.tail.next = temp;
            this.tail = temp;
            size++;
        }
    }//pushBack()

    public int getSize() {                                                      //Function to ge the current size of the Linked List

        return size;
    }

    public Node peek() {                                                        //Check which node is the head
        if(head == null)
            return null;
        else {
            Node temp = head;
            return temp;
        }
    }

    public boolean contains(int target) {                                       //find specific node with given parameter
        Node temp = head;
        while(temp != null) {
            if(temp.source == target)
                return true;
            else
                temp = temp.next;
        }

        return false;
    }//END contains()

    public int getWeight(int target) {                                          //get the weight of the given parameter
        Node temp = head;
        while(temp != null) {
            if(temp.source == target)
                return temp.weight;
            else
                temp = temp.next;
        }
        return Integer.MAX_VALUE;
    }//END getWeight()
}
