package com.nhnacademy;

public class BoundedWorld extends World implements Runnable {
    public boolean outOfBounds(Regionable object) {
        return (object.getMinX() < getBounds().getMinX())
                || object.getMaxX() > getBounds().getMaxX()
                || object.getMinY() < getBounds().getMinY()
                || object.getMaxY() > getBounds().getMaxY();
    }

    public void bounce(Movable object) {
        if (outOfBounds(object)) {
            if ((object.getMinX() < getBounds().getMinX()) || (object.getMaxX() > getBounds().getMaxX())) {
                object.getMotion().turnDX();
            }

            if ((object.getMinY() < getBounds().getMinY()) || (object.getMaxY() > getBounds().getMaxY())) {
                object.getMotion().turnDY();
            }
        }
    }

    
    public void move() {
        for (int i = 0; i < getCount(); i++) {
            Regionable object = get(i);

            if (object instanceof Movable) {
                ((Movable) object).move();
            }
        }

        repaint();
    }

    @Override
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
