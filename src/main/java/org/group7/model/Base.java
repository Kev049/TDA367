package org.group7.model;

public class Base {

    private int capacity;
    private int pieceAmount;
    private Entity[] pieces;

    public Base(int capacity) {
        this.capacity = capacity;
        this.pieceAmount = capacity;
        this.pieces = new Entity[capacity];
        for (int i = 0; i < capacity; i++) {
            this.pieces[capacity] = new Piece(Colour.RED); //TODO implement Player
        }
    }

    public Entity removeEntity() {
        if (this.pieceAmount > 0) {
            Entity e = this.pieces[--this.pieceAmount];
            this.pieces[this.pieceAmount] = null;
            return e;
        }
        return null;
    }

    public void addEntity(Entity e) {
        if (this.pieceAmount < this.capacity) {
            this.pieces[this.pieceAmount++] = e;
        }
    }
}
