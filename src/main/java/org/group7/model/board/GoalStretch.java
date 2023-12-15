package org.group7.model.board;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.model.board.entities.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * The GoalStretch class represents the goal stretch in the game (each player has their
 * own strip of tiles) that the pieces have to go through to reach the goal.
 */
public class GoalStretch implements Observable, IMoveHandler {
    private final int capacity = 4;
    private final int insertCapacity = capacity + 1;
    private final Tile[] tiles = new Tile[capacity];                //TODO varför finns både Tile och Insertable?
    private final IInsertable[] insertables = new IInsertable[insertCapacity];
    private final Color color;
    private final PieceExtractor handler;
    private final List<Observer> observers;
    private Goal goal;

    /**
     * The constructor of the GoalStretch class.
     * 
     * @param color The parameter "color" represents the color of the goal-stretch.
     * @param handler The parameter "handler" represents an object of type PieceExtractor which can
     * trasnfer pieces from the goal-stretch back to the field (rest of the board).
     */
    public GoalStretch(Color color, PieceExtractor handler) {
        this.color = color;
        this.goal = new Goal();
        initTiles();
        initInsertables();
        this.observers = new ArrayList<>();
        this.handler = handler;
    }

    /**
     * The function initializes an array called "insertables" by copying the elements from another
     * array called "tiles" and then inserting a "goal" object at index 4.
     */
    private void initInsertables() {
        System.arraycopy(this.tiles, 0, this.insertables, 0, capacity);
        this.insertables[4] = goal;
        //insertables.add(4, new Goal());
    }

    /**
     * The function initializes an array of Tile objects with a specified capacity.
     */
    private void initTiles() {
        for (int i = 0; i < capacity; i++) {
            this.tiles[i] = new Tile(i);
        }
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
        p.setPos(entryTileIndex);
        p.addToGoalStretch();
        this.insertables[entryTileIndex].insertPiece(p);
        //goalStretchMove(p, 0);
    }


    /**
     * The goalStretchMove function moves a piece on a game board, either to the goal stretch area or
     * to the goal tile.
     * 
     * @param p The parameter "p" is an object of type "Piece".
     * @param steps The "steps" parameter represents the number of steps that the piece should move.
     */
    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        //int oldPos = pos;
        //boolean isNotNewToGoalStretch = p.isAtGoalStretch();    // TODO varför finns detta? vad är poängen?
        removeEntity(pos);
        //pos += steps;  // där den ska
        pos = 4 - abs((pos + steps - 4));
        if(pos < 0) { //pjäsen studsar ut
            this.handler.pieceFromGoalStretchToField(p);
            p.removeFromGoalStretch();
        } else { //pjäsen hamnar antingen på målrutan eller på någon av tilesen i "goalstretch"
            p.setPos(pos);
            this.insertables[pos].insertPiece(p);
            //lägga till en if check eller liknande som lägger till/räknar antalet pjäser som går i mål?
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
    public Tile[] getTiles() {
        return this.tiles;
    }


    /**
     * The function notifies all observers by calling their update method.
     */
    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update();
        }
    }

    /**
     * The function adds an observer to a list of observers and also adds the observer to a goal
     * object.
     * 
     * @param o The parameter "o" is an instance of the Observer interface.
     */

    //TODO: CHANGE SO THAT ONLY GOAL HAS OBSERVER
    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
        this.goal.addObserver(o);
    }

    @Override
    public void returnPieceToBase(Piece p) {
        //TODO empty
    }


}
