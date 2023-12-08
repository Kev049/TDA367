package org.group7.model;

public interface IEntity {
    public void handleCollision(Piece p);

    public int getPos();

    public void setPos(int pos);
}
