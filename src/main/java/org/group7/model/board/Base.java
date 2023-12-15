package org.group7.model.board;

import org.group7.model.board.entities.EntityFactory;
import org.group7.model.board.entities.piece.Piece;

import java.awt.Color;

/**
 * The Base class represents a base in the game. It is used to store pieces of a certain color.
 * The base can hold a specified number of pieces. The base can also remove and add pieces.
 */
public class Base {

    private final int capacity;       //Går att ändra om man vill byta antal pjäser (istället för standard som är 4)
    private int pieceAmount;
    private final Piece[] pieces;
    private final Color color;

    /**
     * The constructor of the Base class.
     * 
     * @param capacity The parameter "capacity" is of type "int" and represents the number of pieces
     * that the base can hold.
     * @param color The parameter "color" is of type "Color" and represents the color of the base.
     * @param handler The parameter "handler" is of type "IMoveHandler" and is used to handle the movement
     * of the pieces in the game.
     */
    public Base(int capacity, Color color, IMoveHandler handler) {
        this.capacity = capacity;
        this.pieceAmount = capacity;
        this.pieces = new Piece[capacity];
        this.color = color;
        initPieces(handler);
    }

    /**
     * The function initializes a specified number of pieces of the base's color.
     * 
     * @param handler The "handler" parameter is an instance of the IMoveHandler interface. It is used
     * to handle the movement of the pieces in the game.
     */
    private void initPieces(IMoveHandler handler) { //Kan nog delas upp till 2 metoder
        for (int i = 0; i < capacity; i++) {
            Piece piece = EntityFactory.createPiece(color, handler);
            this.pieces[i] = piece;
        }
    }


    /**
     * The removePiece() function removes a piece from the base and returns it.
     * 
     * @return The method returns an object of type "Piece" (the removed piece) or null.
     */
    public Piece removePiece() {
        if (this.pieceAmount > 0) {
            Piece p = this.pieces[--this.pieceAmount];
            this.pieces[this.pieceAmount] = null;
            return p;
        }
        return null;
    }

    /**
     * The addPiece function adds a Piece object to the base if there is still capacity.
     * 
     * @param p The parameter "p" is of type "Piece", which represents a piece that we want to add to
     * the base.
     */
    public void addPiece(Piece p) {
        if (this.pieceAmount < capacity) {
            pieces[pieceAmount++] = p;
        }
    }

    /**
     * The function checks if the pieceAmount variable is equal to 0.
     * 
     * @return The method returns a boolean value, which indicates whether the pieceAmount is
     * equal to 0 or not.
     */
    public boolean isEmpty() {
        return (this.pieceAmount == 0);
    }

    //Getters

    /**
     * Returns an array of Piece objects in the base.
     * 
     * @return An array of Piece objects.
     */
    public Piece[] getPieces() {
        return this.pieces;
    }

    /**
     * Returns the color of the base.
     * 
     * @return The color of the base.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the value of the pieceAmount variable.
     * 
     * @return the value of the variable "pieceAmount".
     */
    public int getPieceAmount() {
        return this.pieceAmount;
    }

}
