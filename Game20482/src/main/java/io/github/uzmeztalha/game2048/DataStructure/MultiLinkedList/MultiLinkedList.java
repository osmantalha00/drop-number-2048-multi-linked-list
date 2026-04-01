package io.github.uzmeztalha.game2048.DataStructure.MultiLinkedList;

public class MultiLinkedList <Data> {
    private Node<Data> head;
    private int rows;
    private int cols;

    public MultiLinkedList(int rows, int cols) {
        this.head = null;
        this.rows = rows;
        this.cols = cols;
    }
    
    public void fill(Data fillValue) {
        head = new Node(fillValue);
        Node<Data> firstRow = head;
        for (int i = 1; i < cols; i++) {
            firstRow.next = new Node(fillValue);
            firstRow = firstRow.next;
        }
        
        Node<Data> previousRow = head;
        Node<Data> currentRowHead = new Node(fillValue);
        for (int i = 1; i < rows; i++) {
            Node<Data> currentRow = currentRowHead;
            previousRow.child = currentRow;
            for (int j = 1; j < cols; j++) {
                currentRow.next = new Node(fillValue);
                previousRow.next.child = currentRow.next;
                currentRow = currentRow.next;
                previousRow = previousRow.next;
            }
            previousRow = currentRowHead;
            currentRowHead = new Node(fillValue);
        }
    }

    public void printGrid() {
        Node<Data> currentCol = head;
        while (currentCol != null) {
            Node<Data> currentRow = currentCol;
            while (currentRow != null) {
                System.out.print(currentRow.data + " ");
                currentRow = currentRow.next;
            }
            currentCol = currentCol.child;
            System.out.println();
        }
    }
    public void printGridWithID() {
        Node<Data> currentCol = head;
        while (currentCol != null) {
            Node<Data> currentRow = currentCol;
            while (currentRow != null) {
                System.out.print(currentRow.id + " ");
                currentRow = currentRow.next;
            }
            currentCol = currentCol.child;
            System.out.println();
        }
    }

    public void set(Data data, int row, int col) {
        Node current = getHead();
        for (int i = 0; i < row; i++) {
            current = current.child;
        }
        for (int j = 0; j < col; j++) {
            current = current.next;
        }
        current.data = data;
    }
    
    public Node<Data> getHead() {
        return head;
    }
    
    
    public int getRows() {
        return rows;
    }
    
    
    public int getCols() {
        return cols;
    }
}