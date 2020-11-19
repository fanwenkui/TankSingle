package com.fwk.game.entity;

import com.fwk.game.ui.GameModel;

import java.awt.*;
import java.io.Serializable;

import static com.fwk.game.manager.ResourceManager.explodes;

public class Explode extends GameObject implements Serializable {
    private GameModel gameModel = GameModel.getInstance();
    private int step = 0;

    public static int WIDTH = explodes[0].getWidth();
    public static int HEIGHT = explodes[0].getHeight();


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.gameModel.gameObjects.add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(explodes[step++], x, y, WIDTH, HEIGHT, null);

        if (step == explodes.length) {
            gameModel.gameObjects.remove(this);
        }
    }
}
