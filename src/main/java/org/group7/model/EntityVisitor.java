package org.group7.model;

public interface EntityVisitor {
    void visit(PowerUp powerUp);
    void visit(Piece piece);
}
