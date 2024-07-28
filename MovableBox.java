package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovableBox extends PaintableBox implements Movable {
    static Logger log = LogManager.getLogger(MovableBox.class);
    Vector motion = new PositionalVector(0, 0);
    private boolean running = true;
    private World world;

    public MovableBox(int x, int y, int width, int height, Color color, World world) {
        super(x, y, width, height, color);
        this.world = world;
    }

    public MovableBox(int x, int y, int width, int height, World world) {
        super(x, y, width, height);
        this.world = world;
    }

    public int getDX() {
        return motion.getDX();
    }

    public int getDY() {
        return motion.getDY();
    }

    public void setDX(int dx) {
        motion = new PositionalVector(dx, motion.getDY());
    }

    public void setDY(int dy) {
        motion = new PositionalVector(motion.getDX(), dy);
    }

    public Vector getMotion() {
        return motion;
    }

    public void setMotion(Vector motion) {
        this.motion = motion;
    }

    public void move() {
        region.translate(motion.getDX(), motion.getDY());
        handleCollision();
    }

    public void handleCollision() {
        if (world instanceof BoundedWorld) {
            BoundedWorld boundedWorld = (BoundedWorld) world;
            if (isOutOfBounds(boundedWorld.getBounds())) {
                if ((getMinX() < boundedWorld.getBounds().getMinX()) || (getMaxX() > boundedWorld.getBounds().getMaxX())) {
                    setDX(-getDX());
                }

                if ((getMinY() < boundedWorld.getBounds().getMinY()) || (getMaxY() > boundedWorld.getBounds().getMaxY())) {
                    setDY(-getDY());
                }
            }

            for (int i = 0; i < boundedWorld.getCount(); i++) {
                Regionable other = boundedWorld.get(i);
                if (this != other && this.intersects(other)) {
                    if (other instanceof Brick) {
                        boundedWorld.remove(i);
                        log.debug("Brick destroyed: {}", other);
                    }
                    Vector motion = getMotion();
                    setDX(-motion.getDX());
                    setDY(-motion.getDY());
                }
            }
        }
    }

    public boolean isOutOfBounds(Rectangle bounds) {
        return (getMinX() < bounds.getMinX()) || (getMaxX() > bounds.getMaxX()) || (getMinY() < bounds.getMinY()) || (getMaxY() > bounds.getMaxY());
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100);
                move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        running = false;
    }
}
