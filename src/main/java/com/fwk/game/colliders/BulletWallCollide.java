package com.fwk.game.colliders;

import com.fwk.game.entity.Bullet;
import com.fwk.game.entity.GameObject;
import com.fwk.game.entity.Wall;

public class BulletWallCollide implements Collide{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Bullet){
            Wall wall=(Wall) o1;
            Bullet bullet=(Bullet) o2;
            if(wall.rectangle.intersects(bullet.rectangle)){
                bullet.die();
            }
        }else if(o1 instanceof Bullet && o2 instanceof  Wall){
            return collide(o2,o1);
        }

        return true;
    }
}
