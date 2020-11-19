package com.fwk.game.strategy;

import com.fwk.game.entity.Bullet;
import com.fwk.game.entity.Tank;

public class DefaultShotStrategy extends ShotStrategy {
    @Override
    public void shot(Tank tank) {
        int bx = 0, by = 0;
        switch (tank.direct) {
            case UP:
                bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                by = tank.y - Bullet.HEIGHT;
                break;
            case DOWN:
                bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                by = tank.y + Tank.HEIGHT;
                break;
            case LEFT:
                bx = tank.x - Bullet.HEIGHT;
                by = tank.y + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                break;
            case RIGHT:
                bx = tank.x + Tank.HEIGHT;
                by = tank.y + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                break;
        }

        new Bullet(bx, by, tank.direct, tank.group);
    }
}
