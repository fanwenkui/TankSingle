package com.fwk.game.colliders;

import com.fwk.game.entity.GameObject;
import com.fwk.game.entity.Tank;
import com.fwk.game.entity.Wall;

public class TankWallCollide implements Collide {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Tank){
            Wall wall=(Wall) o1;
            Tank tank=(Tank) o2;
            if(wall.rectangle.intersects(tank.rectangle)){
                tank.back();
            }
        }else if(o2 instanceof Wall && o1 instanceof Tank){
            return collide(o2,o1);
        }
        return true;
    }
}
