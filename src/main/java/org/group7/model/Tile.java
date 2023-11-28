package org.group7.model;

import java.awt.Color;

public class Tile {

    private int index;
    private Entity entity;

    public Tile(int index) {
        entity = null;
        this.index = index;
    }

    public Entity getEntity() {
        return entity;
    }

    public void insertEntity(Entity e) {
        this.entity = e;
    }

    public Entity removeEntity() {
        Entity e = this.entity;
        this.entity = null;
        return e;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEmpty() {
        return this.entity == null;
    }

    public Color getEntityColor() {
        if (this.entity instanceof Piece) {
            return ((Piece) this.entity).getColor();
        }
        return null;
    }

}
