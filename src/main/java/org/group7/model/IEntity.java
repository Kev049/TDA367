package org.group7.model;

public interface IEntity {
    void accept(EntityVisitor visitor);
    void handleCollision(Piece p);
    int getPos();
    void setPos(int pos);
}
