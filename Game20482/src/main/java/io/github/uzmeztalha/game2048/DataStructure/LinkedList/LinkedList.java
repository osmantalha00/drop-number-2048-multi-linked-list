package io.github.uzmeztalha.game2048.DataStructure.LinkedList;

public class LinkedList<Data> {

    public LinkedListNode head;

    private int size;

    public LinkedList() {
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addFirst(Data element) {
        addFirst(new LinkedListNode(element));
    }

    public void addFirst(LinkedListNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(Data element) {
        addLast(new LinkedListNode(element));
    }

    public void addLast(LinkedListNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            LinkedListNode<Data> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public void insertAfter(Data afterData, Data newData) {
        LinkedListNode temp = head;

        while (temp != null && (temp.data != afterData)) {
            temp = temp.next;
        }

        if (temp != null) {
            LinkedListNode<Data> newNode = new LinkedListNode(newData);
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        } else {
            addLast(newData);
        }
    }

    public void print() {
        LinkedListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " > ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public LinkedListNode findNode(Data element) {
        LinkedListNode current = head;

        while (current != null && current.data != element) {
            current = current.next;
        }
        return current;
    }
    
    public void delete(Data target){
        LinkedListNode current = head; 
        LinkedListNode previous = null;
        //search through the list
        while(current != null){
            //if the target has been found at the current position
            if(current.data == target){
                if(previous == null){ // start of the list
                    head = current.next;
                }
                else{
                    previous.next = current.next;
                }
            }
            previous = current;
            current = current.next;
        }
        
    
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public void clear() {
        size = 0;
        head = null;
    }
    
}