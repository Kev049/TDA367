package org.group7.model.entities.piece;

import org.group7.model.entities.Entity;
import org.group7.model.entities.EntityVisitor;
import org.group7.model.board.IMoveHandler;

import java.awt.*;

/**
 * The Piece class represents a piece on the board. It extends the Entity class.
 * 
 * @see Entity
 */
public class Piece extends Entity {

    private IMoveHandler handler;
    private final Color color;
    private boolean atGoalStretch;
    private PieceState state;

    /**
     * The constructor for the Piece class. It takes in a color and a handler as parameters.
     * 
     * @param color The parameter "color" is of type "Color". It represents the color of the piece.
     * @param handler The parameter "handler" is of type "IMoveHandler". It represents the handler
     * for handling move events.
     */
    public Piece(Color color, IMoveHandler handler) {
        this.handler = handler;
        this.color = color;
        this.atGoalStretch = false;
        this.state = new FieldState(this);
    }

    /**
     * The accept function calls the visit function of the visitor object, passing in
     * piece (itself) as a parameter.
     * 
     * @param visitor The parameter "visitor" is of type "EntityVisitor"
     */
    @Override
    public void accept(EntityVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * The getColor() function returns the color of the piece.
     * 
     * @return the color of the piece.
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * The handleCollision function calls the handleCollision method of the state object, passing in
     * the Piece object and its handler as parameters.
     * 
     * @param p The parameter "p" is of type "Piece". It represents the piece that has collided with
     * the current piece.
     */
    @Override
    public void handleCollision(Piece p) {
        state.handleCollision(p, handler);
    }

    /**
     * The function sets the state of a piece to the provided state which represents the piece
     * being on either the field or goal-stretch.
     * 
     * @param s The parameter "s" is of type PieceState, which is the state that we want to set for the
     * object.
     */
    protected void setState(PieceState s) {
        this.state = s;
    }

    /**
     * The function toggles the state of an object (from field to goal-stretch or vice versa).
     */
    public void toggleState() {
        this.state.nextState();
    }

    /**
     * The function sets the handler for handling move events.
     * 
     * @param handler The "handler" parameter is of type IMoveHandler. It is used to set the move
     * handler for an object or class.
     */
    public void setHandler(IMoveHandler handler) {
        this.handler = handler;
    }

    /**
     * The function sets the "atGoalStretch" variable to true.
     */
    public void addToGoalStretch() {
        this.atGoalStretch = true;
    }

    /**
     * The function sets the "atGoalStretch" variable to false.
     */
    public void removeFromGoalStretch() {
        this.atGoalStretch = false;
    }

    /**
     * The function returns whether the piece is at its goal-stretch.
     * 
     * @return the value of the "atGoalStretch" variable.
     */
    public boolean isAtGoalStretch() {
        return this.atGoalStretch;
    }
}
