package com.nhnacademy;

import java.awt.Color;

public class UnbreakableBrick extends PaintableBox {
    public UnbreakableBrick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public UnbreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLUE);
    }
}
