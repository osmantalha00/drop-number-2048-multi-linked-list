package io.github.uzmeztalha.game2048.DataStructure.MultiLinkedList;

public class Node <Data> {
    public int id;
    public Data data;
    public Node<Data> child;
    public Node<Data> next;

    public Node(Data data) {
        this.id = lastID++;
        this.data = data;
        this.child = null;
        this.next = null;
    }
    
    
    //Static field
    public static int lastID = 0;
}