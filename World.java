package com.nhnacademy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    List<Regionable> regionableList;
    List<Thread> threads = new ArrayList<>();
    static Logger log = LogManager.getLogger(World.class);

    public World() {
        super();
        regionableList = new ArrayList<>();
        log.debug("World가 생성되었습니다!");
    }

    public void add(Regionable regionable) {
        if (regionable == null) {
            throw new NullPointerException();
        }

        regionableList.add(regionable);
        if (regionable instanceof Movable) {
            Thread thread = new Thread((Movable) regionable);
            threads.add(thread);
            thread.start();
        }
    }

    public void remove(Regionable regionable) {
        if (regionable == null) {
            throw new NullPointerException();
        }

        if (regionable instanceof Movable) {
            ((Movable) regionable).stop();
        }

        regionableList.remove(regionable);
    }

    public int getCount() {
        return regionableList.size();
    }

    public Regionable get(int index) {
        return regionableList.get(index);
    }

    @Override
    public void remove(int index) {
        Regionable regionable = regionableList.get(index);
        if (regionable instanceof Movable) {
            ((Movable) regionable).stop();
        }
        regionableList.remove(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Regionable regionable : regionableList) {
            if (regionable instanceof Paintable) {
                ((Paintable) regionable).paint(g);
            } else {
                log.debug("{}은 그릴 수 없습니다.", regionable);
            }
        }
    }
}
