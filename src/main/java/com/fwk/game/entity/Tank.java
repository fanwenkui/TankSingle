package com.fwk.game.entity;

import com.fwk.game.enums.Direct;
import com.fwk.game.enums.Group;
import com.fwk.game.manager.ResourceManager;
import com.fwk.game.strategy.DefaultShotStrategy;
import com.fwk.game.strategy.ShotStrategy;
import com.fwk.game.strategy.SuperShotStrategy;
import com.fwk.game.ui.GameModel;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

import static com.fwk.game.constant.TankConstant.GAME_HEIGHT;
import static com.fwk.game.constant.TankConstant.GAME_WIDTH;
import static com.fwk.game.enums.Group.ENEMY;
import static com.fwk.game.enums.Group.FRIEND;

public class Tank extends GameObject implements Serializable {
    public static int WIDTH = ResourceManager.tankUp.getWidth(), HEIGHT = ResourceManager.tankUp.getHeight();
    public GameModel gameModel = GameModel.getInstance();
    public Direct direct;
    public Group group;
    public ShotStrategy shotStrategy=new DefaultShotStrategy();
    public ShotStrategy superShotStrategy=new SuperShotStrategy();
    public boolean moving = true;

    private int preX;
    private int preY;
    private Random random = new Random();
    private int speed = 5;

    public Tank(int x, int y, Direct direct, Group group) {
        this.preX = this.x = x;
        this.preY = this.y = y;
        this.direct = direct;
        this.group = group;
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        this.gameModel.gameObjects.add(this);
    }

    public void paint(Graphics g) {
        switch (direct) {
            case UP:
                g.drawImage(ResourceManager.tankUp, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankDown, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.tankLeft, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankRight, x, y, null);
                break;
        }
        move();
    }

    public void move() {
        preX=x;
        preY=y;

        if (!moving) {
            return;
        }
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

        rectangle.x = x;
        rectangle.y = y;

        if (group == ENEMY) {
            if (random.nextInt(100) > 97) {
                changeDirect();
            }
            if (random.nextInt(100) > 97) {
                shot(this.shotStrategy);
            }
        }

        boundsCheck();
    }

    public void back(){
        x=preX;
        y=preY;
        if(group==ENEMY){
            changeDirect();
        }
    }

    public void changeDirect() {
        direct=(Direct.values()[random.nextInt(4)]);
    }

    public void boundsCheck() {
        boolean changFlag = false;
        if (x < 10 || y < 10 || x > GAME_WIDTH - WIDTH - 10 || y > GAME_HEIGHT - HEIGHT - 10) {
            changFlag = true;
        }
        if(changFlag){
            back();
            if (group == ENEMY) {
                changeDirect();
            }
        }
    }

    public void shot(ShotStrategy shotStrategy) {
        shotStrategy.shot(this);
    }

    public void die() {
        gameModel.gameObjects.remove(this);
        if(group==FRIEND){
            gameModel.setPlayer1();
        }
    }
}
