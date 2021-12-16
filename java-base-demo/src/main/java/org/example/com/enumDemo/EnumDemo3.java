package org.example.com.enumDemo;

enum Signal {
    GREEN, YELLOW, RED
}

public class EnumDemo3 {
    Signal color = Signal.GREEN;

    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }
}
