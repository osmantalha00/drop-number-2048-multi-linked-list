package io.github.uzmeztalha.game2048;

public class Main {

    public static void main(String[] args) {
        new Thread(new Game2048()).start();
    }

}
