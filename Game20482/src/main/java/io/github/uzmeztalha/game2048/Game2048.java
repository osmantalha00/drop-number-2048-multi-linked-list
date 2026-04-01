package io.github.uzmeztalha.game2048;

import io.github.uzmeztalha.game2048.DataStructure.LinkedList.LinkedList;
import io.github.uzmeztalha.game2048.DataStructure.LinkedList.LinkedListNode;
import io.github.uzmeztalha.game2048.DataStructure.MultiLinkedList.MultiLinkedList;
import io.github.uzmeztalha.game2048.DataStructure.MultiLinkedList.Node;
import io.github.uzmeztalha.game2048.Frame.GameScreen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game2048 implements Runnable, KeyListener {
    
    private JFrame gameFrame;
    private GameScreen gameScreen;
    private MultiLinkedList<Points> blocks;
    private LinkedList<Node<Points>> toMoveDown;
    private LinkedList<Node<Points>> toMerge;
    
    public Game2048() {
        gameFrame = new JFrame("Game2048 0.1");
        initalize();
        blocks = new MultiLinkedList(7, 5);
        blocks.fill(Points.P0);
        toMoveDown = new LinkedList();
        toMerge = new LinkedList();
        
    }
    
    
    private void initalize() {
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setFocusable(true);
        gameFrame.setResizable(false);
        gameScreen = new GameScreen();
        gameFrame.addKeyListener(this);
        gameFrame.setContentPane(gameScreen.getPanel());
        gameFrame.setSize(424, 624);
    }

    @Override
    public void run() {        
        while (true) {
            printBlocksToGameScreen();
            calculatePoints();
            doTick();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {}
        }
    }
    
    private boolean isFallingBlockExists() {
        return toMoveDown.getSize() == 0;
    }
    
    private void generateFallingBlock() {
        int randomCol = (int)(Math.random() * blocks.getCols());
        Points randomPoint = Points.getRandomPoint();
        blocks.set(randomPoint, 0, randomCol);
    }
    
    private void doTick() {
        Node<Points> rowHead = blocks.getHead();
        while (rowHead != null) {
            Node<Points> rowCurrent = rowHead;
            while (rowCurrent != null) {
                if(moveDataToChild(rowCurrent)) {
                    toMoveDown.addFirst(rowCurrent);
                }
                if (mergeDataWithChild(rowCurrent)) {
                    toMerge.addFirst(rowCurrent);
                }
                rowCurrent = rowCurrent.next;
            }
            rowHead = rowHead.child;
        }
        
        if (isFallingBlockExists()) generateFallingBlock();
        LinkedListNode<Node<Points>> current = toMoveDown.head;
        while (current != null) {
            performMoveDataToChild(current.data);
            current = current.next;
        }
        toMoveDown.clear();
        
        current = toMerge.head;
        while (current != null) {
            performMergeDataWithChild(current.data);
            current = current.next;
        }
        toMerge.clear();
        
    }
    private boolean moveDataToChild(Node<Points> node) {
        if (node.child == null) return false;
        if (node.data == Points.P0) return false;
        return node.child.data == Points.P0;
    }
    private void performMoveDataToChild(Node<Points> node) {
        node.child.data = node.data;
        node.data = Points.P0;
    }
        
    private boolean mergeDataWithChild(Node<Points> node) {
        if (node.child == null) return false;
        if (node.data == Points.P0 || node.data == Points.P256) return false;
        return node.child.data == node.data;
    }
    private void performMergeDataWithChild(Node<Points> node) {
        node.data = Points.P0;
        node.child.data = Points.getNextPoint(node.child.data);
    }
    
    private void printBlocksToGameScreen() {
        Node<Points> colCurrent = blocks.getHead();
        for (int i = 0; i < blocks.getRows(); i++){
            Node<Points> rowCurrent = colCurrent;
            for (int j = 0; j < blocks.getCols(); j++) {
                Points currentPoint = rowCurrent.data;
                gameScreen.blocks[i][j].setText(currentPoint.getText());
                gameScreen.blocks[i][j].setBackground(currentPoint.getColor());
                rowCurrent = rowCurrent.next;
            }
            colCurrent = colCurrent.child;
        }
    }
    
    private void calculatePoints() {
        int score = 0;
        Node<Points> rowHead = blocks.getHead();
        while (rowHead != null) {
            Node<Points> rowCurrent = rowHead;
            while (rowCurrent != null) {
                score += rowCurrent.data.getValue();
                rowCurrent = rowCurrent.next;
            }
            rowHead = rowHead.child;
        }
        gameScreen.getScoreLabel().setText("Score: " + score);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        System.out.println("Key pressed");
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}
