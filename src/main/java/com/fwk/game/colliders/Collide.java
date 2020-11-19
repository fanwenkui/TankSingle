package com.fwk.game.colliders;

import com.fwk.game.entity.GameObject;

import java.io.Serializable;

public interface Collide extends Serializable {
   boolean collide(GameObject o1, GameObject o2);
}
