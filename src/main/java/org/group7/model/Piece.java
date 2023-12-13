package org.group7.model;

import java.awt.*;

public class Piece extends Entity {
    private int pos;
    public IMoveHandler handler;
    private final Color color;
    private boolean atGoalStretch;

    private PieceState state;

    public Piece(Color color, IMoveHandler handler) {
        this.handler = handler;
        this.color = color;
        this.atGoalStretch = false;
        this.state = new FieldState(this);
    }

    @Override
    public void accept(EntityVisitor visitor){
        visitor.visit(this);
    }
    public Color getColor(){
        return this.color;
    }

    @Override
    public void handleCollision(Piece p) {
        state.handleCollision(p,handler);
    }

    public void enableGoalState() {
        this.state = new GoalState(this);
    } //TODO borde antagligen flytta detta beroendet till State-klasserna

    public void enableFieldState() {
        this.state = new FieldState(this);
    } //TODO borde antagligen flytta detta beroendet till State-klasserna

    public void setHandler(IMoveHandler handler) {
        this.handler = handler;
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
}
