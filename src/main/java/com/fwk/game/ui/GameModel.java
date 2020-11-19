package com.fwk.game.ui;

import com.fwk.game.colliders.CollideChain;
import com.fwk.game.entity.GameObject;
import com.fwk.game.entity.Tank;
import com.fwk.game.entity.Wall;
import com.fwk.game.enums.Direct;
import com.fwk.game.enums.Group;
import com.fwk.game.manager.ResourceManager;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.fwk.game.constant.TankConstant.*;

public class GameModel implements Serializable {
    public Rectangle rectangle = new Rectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
    public List<GameObject> gameObjects = new ArrayList();
    public Tank player1;

    private CollideChain chains = new CollideChain();
    private static GameModel instance = new GameModel();

    private GameModel() {
        //加载资源
        ResourceManager.load();
    }

    public void init() {
        //添加玩家1
        setPlayer1();
        //添加敌人
        for (int i = 0; i < ENEMY_NUM; i++) {
            new Tank(i * GAME_WIDTH / 5 + 10, 50, Direct.DOWN, Group.ENEMY);
        }
        //添加墙
        new Wall(300, 150, 50, 200);
        new Wall(500, 150, 50, 200);
    }

    public static GameModel getInstance() {
        return instance;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                chains.collide(o1, o2);
            }
        }

    }

    public void setPlayer1() {
        player1 = new Tank(630, 668, Direct.UP, Group.FRIEND);
        player1.moving = false;
    }

    public void saveGame() {
        File file = new File("d:/workspace/Tank/autoSave.data");
        ObjectOutputStream oos = null;
        try {
            OutputStream os = new FileOutputStream(file);
            oos = new ObjectOutputStream(os);
            oos.writeObject(player1);
            oos.writeObject(gameObjects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadGame() {
        File file = new File("d:/workspace/Tank/autoSave.data");
        ObjectInputStream ois = null;
        try {
            InputStream is = new FileInputStream(file);
            ois = new ObjectInputStream(is);
            player1 = (Tank) ois.readObject();
            gameObjects = (List) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
