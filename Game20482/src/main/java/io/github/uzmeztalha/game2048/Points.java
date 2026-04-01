package io.github.uzmeztalha.game2048;

import java.awt.Color;

public enum Points {
    P0("", 0, Color.WHITE),
    P2("2", 2, new Color(255, 255, 166)),
    P4("4", 4, new Color(255, 222, 89)),
    P8("8", 8, new Color(212, 234, 107)),
    P16("16", 16, new Color(224, 194, 205)),
    P32("32", 32, new Color(221, 232, 203)),
    P64("64", 64, new Color(222, 220, 230)),
    P128("128", 128, new Color(255, 123, 89)),
    P256("256", 256, new Color(255, 216, 206)),
    ;
    
    private final String text;
    private final int value;
    private final Color color;

    Points(String text, int value, Color color) {
        this.text = text;
        this.value = value;
        this.color = color;
    }

    public String getText() {
        return text;
    }
    public int getValue() {
        return value;
    }
    public Color getColor(){
        return color;
    }
    
    public static Points getNextPoint(Points point) {
        if (point.value == 256) return point;
        Points[] values = Points.values();
        for (int i = 1; i < values.length; i++) {
            if (values[i].value == point.value) {
                return values[i+1];
            }
        }
        return Points.P0;
    }
    
    public static Points getRandomPoint() {
        Points[] values = Points.values();
        int randomValue = 1 + (int)(Math.random() * (values.length - 1));
        return values[randomValue];
    }
    
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
