package org.group7.model;

import org.group7.model.Entity;

public class Tile {

    private Entity entity;

    public Tile() {
        entity = null;
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

}
