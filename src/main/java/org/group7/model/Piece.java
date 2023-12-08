package org.group7.model;

import java.awt.*;

public class Piece implements IEntity {
    private int pos;
    private IMoveHandler handler;
    private final Color color;
    private boolean atHome; //Behövs dessa? Kanske är smidigt, annars tar vi bort
    private boolean atGoal; //Kanske helt onödigt, då man kan ha en plats i arrayen som representerar om den är hemma/i mål, dock lättare att förstå koden såhär.
    private boolean atGoalStretch;

    public Piece(Color color, IMoveHandler handler) {   //konstruktor för Piece, offset beroende på färg för var de startar (utgår från att brädet är en array, justera offset om inre "målvägar" är del av den).
        this.handler = handler;
        this.atHome = true;
        this.atGoal = false;
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public int getPos(){
        return this.pos;
    }

    public void handleCollision(Piece p) {
        if (this.color.equals(p.getColor())) {
            // Same Color, skip one tile
            this.handler.addPieceToField(p, this.pos + 1);
        } else {
            // Different color, send other to base and take its place
            int position = this.pos;
            this.handler.returnPieceToBase(this);
            this.handler.addPieceToField(p, position);
        }
    }

    public void addToGoalStretch(){
        this.atGoalStretch = true;
    }

    public void removeFromGoalStretch(){
        this.atGoalStretch = false;
    }

    public boolean isAtGoalStretch(){
        return this.atGoalStretch;
    }

    public void setPos(int index){
        this.pos = index;
    }
}
