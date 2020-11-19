package com.fwk.game.colliders;

import com.fwk.game.entity.Bullet;
import com.fwk.game.entity.Explode;
import com.fwk.game.entity.GameObject;
import com.fwk.game.entity.Tank;

public class BulletTankCollide implements Collide {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet=(Bullet) o1;
            Tank tank=(Tank) o2;
            if(bullet.rectangle.intersects(tank.rectangle) && bullet.group!=tank.group){
                bullet.die();
                tank.die();
                int eX = tank.x + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = tank.y + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(eX,eY);
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
