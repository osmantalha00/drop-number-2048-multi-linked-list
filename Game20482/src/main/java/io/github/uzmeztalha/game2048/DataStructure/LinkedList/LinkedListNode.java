package io.github.uzmeztalha.game2048.DataStructure.LinkedList;

public class LinkedListNode <Data> {
    public Data data;
    public LinkedListNode next;

    public LinkedListNode(Data data) {
        this.data = data;
        this.next = null;
    }
}
