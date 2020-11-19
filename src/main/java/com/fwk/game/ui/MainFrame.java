package com.fwk.game.ui;

import com.fwk.game.entity.Tank;
import com.fwk.game.enums.Direct;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.fwk.game.constant.TankConstant.GAME_HEIGHT;
import static com.fwk.game.constant.TankConstant.GAME_WIDTH;

public class MainFrame extends Frame {
    private Image bufferImage = null;
    private GameModel gameModel = GameModel.getInstance();

    public MainFrame() {
        this.setTitle("坦克大战(月檬版)");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListens());
        //初始化
        this.gameModel.init();
    }

    @Override
    public void update(Graphics g) {
        if (bufferImage == null) {
            bufferImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //双缓冲，解决闪烁
        Graphics offset = bufferImage.getGraphics();
        Color c = offset.getColor();
        offset.setColor(Color.BLACK);
        offset.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        offset.setColor(c);
        paint(offset);
        g.drawImage(bufferImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
    }

    class MyKeyListens extends KeyAdapter {
        private boolean up = false, down = false, left = false, right = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_Z:
                    gameModel.player1.shot(gameModel.player1.shotStrategy);
                    break;
                case KeyEvent.VK_X:
                    gameModel.player1.shot(gameModel.player1.superShotStrategy);
                    break;
                case KeyEvent.VK_S:
                    gameModel.saveGame();
                    break;
                case KeyEvent.VK_L:
                    gameModel.loadGame();
            }
            setTankDirect();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
            }
            setTankDirect();
        }

        private void setTankDirect() {
            Tank player1 = gameModel.player1;
            if (!(up || down || left || right)) {
                player1.moving=false;
            } else {
                player1.moving=true;
                if (up) {
                    player1.direct=Direct.UP;
                }
                if (down) {
                    player1.direct=Direct.DOWN;
                }
                if (left) {
                    player1.direct=Direct.LEFT;
                }
                if (right) {
                    player1.direct=Direct.RIGHT;
                }
            }
        }
    }
}
