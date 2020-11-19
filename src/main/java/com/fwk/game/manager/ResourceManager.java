package com.fwk.game.manager;

import com.fwk.game.constant.TankConstant;
import com.fwk.game.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static com.fwk.game.constant.TankConstant.IMG_BULLET;
import static com.fwk.game.constant.TankConstant.IMG_FRIEND_TANK;

public class ResourceManager {
    public static BufferedImage tankUp, tankDown, tankLeft, tankRight;
    public static BufferedImage bulletUp, bulletDown, bulletLeft, bulletRight;
    public static BufferedImage[] explodes = new BufferedImage[16];

    public static void load() {
        try {
            tankUp = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/" + IMG_FRIEND_TANK + ".png"));
            tankDown = ImageUtil.rotateImage(tankUp, 180);
            tankLeft = ImageUtil.rotateImage(tankUp, -90);
            tankRight = ImageUtil.rotateImage(tankUp, 90);

            bulletUp = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/"+IMG_BULLET+".png"));
            bulletDown = ImageUtil.rotateImage(bulletUp, 180);
            bulletLeft = ImageUtil.rotateImage(bulletUp, -90);
            bulletRight = ImageUtil.rotateImage(bulletUp, 90);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
