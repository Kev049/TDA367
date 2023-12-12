package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

public class Tile implements IInsertable {

    private final int index;
    private IEntity entity;

    public Tile(int index) {
        this.entity = null;
        this.index = index;
    }

    public void insertPiece(Piece p) {
        if(this.entity != null){     // if collision
            this.entity.handleCollision(p);
        } else {                    // else
            this.entity = p;
            this.entity.setPos(index);
        }
    }

    public void insertPowerUp(PowerUp powerUp){
        if(this.entity == null){
            this.entity = powerUp;
            this.entity.setPos(index);
        }
    }

    public IEntity getEntity(){
        return this.entity;
    }

    public void removeEntity() {
        this.entity.setPos(-1);
        this.entity = null;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEmpty() {
        return this.entity == null;
    }

}
