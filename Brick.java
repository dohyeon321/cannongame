package com.nhnacademy;

import java.awt.Color;

public class Brick extends PaintableBox {
    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height, Color.RED);
    }
}
