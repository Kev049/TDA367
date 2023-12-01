package org.group7.model;

import java.awt.*;

public class Piece extends Entity {
    private int pos;
    private int distFromStart;
    private final Color color;
    private boolean atHome; //Behövs dessa? Kanske är smidigt, annars tar vi bort
    private boolean atGoal; //Kanske helt onödigt, då man kan ha en plats i arrayen som representerar om den är hemma/i mål, dock lättare att förstå koden såhär.

    public Piece(Color color){   //konstruktor för Piece, offset beroende på färg för var de startar (utgår från att brädet är en array, justera offset om inre "målvägar" är del av den).
        super(10); //either change entity or switch
        this.atHome = true;
        this.atGoal = false;
        this.color = color;
    }

    public int get_pos(){

        return this.pos;
    }

    public void move(int diceRoll){
        for (int i = 0; i < diceRoll; i++ ){
            this.pos += 1;
            //check if piece needs to turn in to goal line.
        }
    }

    public Color getColor(){
        return this.color;
    }

    public boolean is_home(){
        return this.atHome;
    }

    public boolean is_goal(){
        return this.atGoal;
    }

    private void set_home(boolean bol){
        this.atHome = bol;
    }

    private void set_goal(boolean bol){
        this.atGoal = bol;
    }

    public int getDistFromStart(){
        return this.distFromStart;
    }

    @Override
    public void handleCollision(Piece p) {
        if (p.getColor() != this.color) {

        }
    }
}
