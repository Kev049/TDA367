package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

public interface EntityVisitor {
    void visit(PowerUp powerUp);

    void visit(Piece piece);
}
