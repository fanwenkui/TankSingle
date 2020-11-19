package com.fwk.game.strategy;

import com.fwk.game.entity.Tank;

import java.io.Serializable;

public abstract class ShotStrategy implements Serializable {
    public abstract void shot(Tank tank);
}
