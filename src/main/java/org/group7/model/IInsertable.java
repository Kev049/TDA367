package org.group7.model;

public interface IInsertable {
    void insertPiece(Piece p);

    void removeEntity();    //TODO ska vi ha detta interface måste vi använda det på alla ställen
}
