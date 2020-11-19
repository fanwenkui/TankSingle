package com.fwk.game.colliders;

import com.fwk.game.entity.GameObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CollideChain implements Collide {
    private List<Collide> collides = new ArrayList();

    public CollideChain() {
        add(new BulletTankCollide());
        add(new BulletWallCollide());
        add(new TankTankCollide());
        add(new TankWallCollide());
    }

    public void add(Collide collide) {
        this.collides.add(collide);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i=0;i<collides.size();i++) {
            if (!collides.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
