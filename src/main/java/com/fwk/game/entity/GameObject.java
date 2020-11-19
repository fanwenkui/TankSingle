package com.fwk.game.entity;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    public int x;
    public int y;
    public Rectangle rectangle;

    public abstract void paint(Graphics g);
}
