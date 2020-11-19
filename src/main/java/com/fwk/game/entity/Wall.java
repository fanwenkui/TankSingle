package com.fwk.game.entity;

import com.fwk.game.ui.GameModel;

import java.awt.*;
import java.io.Serializable;

public class Wall extends GameObject implements Serializable {
    private int width;
    private int height;
    private GameModel gameModel=GameModel.getInstance();

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x, y, width, height);
        this.gameModel.gameObjects.add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
