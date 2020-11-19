package com.fwk.game.constant;

import static com.fwk.game.manager.PropertiesManager.PROPERTIES;

public class TankConstant {
    public static int ENEMY_NUM = PROPERTIES.getInt("tank.enemy.num");
    public static String IMG_FRIEND_TANK = PROPERTIES.getString("tank.img.friend");
    public static String IMG_BULLET = PROPERTIES.getString("tank.img.bullet");
    public static int GAME_WIDTH = PROPERTIES.getInt("tank.frame.width");
    public static int GAME_HEIGHT = PROPERTIES.getInt("tank.frame.height");
}
