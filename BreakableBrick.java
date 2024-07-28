package com.nhnacademy;

import java.awt.Color;

public class BreakableBrick extends PaintableBox {
    public BreakableBrick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public BreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height, Color.PINK);
    }
}
