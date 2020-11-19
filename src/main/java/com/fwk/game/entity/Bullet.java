package com.fwk.game.entity;

import com.fwk.game.enums.Direct;
import com.fwk.game.enums.Group;
import com.fwk.game.manager.ResourceManager;
import com.fwk.game.ui.GameModel;

import java.awt.*;
import java.io.Serializable;

public class Bullet extends GameObject implements Serializable {
    public static int WIDTH = ResourceManager.bulletUp.getWidth(), HEIGHT = ResourceManager.bulletUp.getHeight();

    private Direct direct;
    private int speed = 10;
    private GameModel gameModel= GameModel.getInstance();
    public Group group;

    public Bullet(int x, int y, Direct direct, Group group) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.group = group;
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        this.gameModel.gameObjects.add(this);
    }

    @Override
    public void paint(Graphics g) {
        switch (direct) {
            case UP:
                g.drawImage(ResourceManager.bulletUp, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletDown, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.bulletLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletRight, x, y, null);
                break;
        }
        move();
    }

    public void die() {
        gameModel.gameObjects.remove(this);
    }

    private void move() {
        switch (direct) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
        rectangle.y = y;
        rectangle.x = x;

        boundsCheck();
    }

    private void boundsCheck() {
        if(!rectangle.intersects(gameModel.rectangle)){
            gameModel.gameObjects.remove(this);
        }
    }
}
