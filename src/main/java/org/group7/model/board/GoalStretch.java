package org.group7.model.board;

import org.group7.model.entities.piece.Piece;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * The GoalStretch class represents the goal stretch in the game (each player has their
 * own strip of tiles) that the pieces have to go through to reach the goal.
 */
public class GoalStretch implements IMoveHandler {
    private final int capacity = 4;
    private final IInsertable[] insertables = new IInsertable[capacity + 1];
    private final Color color;
    private final PieceExtractor handler;

    /**
     * The constructor of the GoalStretch class.
     * 
     * @param color The parameter "color" represents the color of the goal-stretch.
     * @param handler The parameter "handler" represents an object of type PieceExtractor which can
     * trasnfer pieces from the goal-stretch back to the field (rest of the board).
     */
    public GoalStretch(Color color, PieceExtractor handler) {
        this.color = color;
        initInsertables();
        this.handler = handler;
    }

    /**
     * The function initializes an array called "insertables" by copying the elements from another
     * array called "tiles" and then inserting a "goal" object at index 4.
     */
    private void initInsertables() {
        for (int i = 0; i < capacity; i++) {
            this.insertables[i] = new Tile(i);
        }
        this.insertables[capacity] = new Goal();
    }

    /**
     * The addPiece function adds a piece to a specific index in the goal stretch, updates the piece's position.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece that can be added to
     * the game board.
     * @param index The `index` parameter represents the position where the `Piece` object `p` should
     * be added.
     */
    @Override
    public void addPiece(Piece p, int index) {
        int entryTileIndex = 4 - abs(index - 4);
        p.addToGoalStretch();
        this.insertables[entryTileIndex].insertPiece(p);
    }


    /**
     * The goalStretchMove function moves a piece on a game board, either to the goal stretch area or
     * to the goal tile.
     * 
     * @param p The parameter "p" is an object of type "Piece".
     * @param steps The "steps" parameter represents the number of steps that the piece should move.
     */
    public void goalStretchMove(Piece p, int steps) {
        int pos = p.getPos();
        removeEntity(pos);
        pos = 4 - abs((pos + steps - 4));
        if (pos < 0) {  // Went out of GoalStretch
            this.handler.pieceFromGoalStretchToField(p);
            p.removeFromGoalStretch();
        } else {        // Landed inside GoalStretch
            p.setPos(pos);
            this.insertables[pos].insertPiece(p);
        }
    }

    /**
     * The removeEntity function removes an entity at a specified index from an array.
     * 
     * @param index The index parameter represents the position of the entity in the insertables array
     * that to be removed.
     */
    public void removeEntity(int index) { //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.insertables[index].removeEntity();
    }

    /**
     * The function returns the color of the goal-stretch.
     * 
     * @return the color of the goal-stretch.
     */
    public Color getColor() {
        return this.color;
    }

    
    /**
     * The function returns an array of Tile objects which represents the tiles part of the goal-stretch.
     * 
     * @return An array of Tile objects.
     */
    public IInsertable[] getTiles() {
        IInsertable[] copy = new IInsertable[capacity];
        for (int i = 0; i < capacity; i++) {
            copy[i] = this.insertables[i];
        }
        //Arrays.copyOfRange(this.insertables,0,capacity - 1);
        return copy;    //Returns the "Tiles" part of the
    }

    public IInsertable getGoal() {
        return this.insertables[capacity];
    }

    @Override
    public void returnPieceToBase(Piece p) {
        //TODO future implementation
    }


}
