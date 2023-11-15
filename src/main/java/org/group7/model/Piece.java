package org.group7.model;

public class Piece extends Entity {
    private int pos;
    private String colour;
    private boolean atHome; //Behövs dessa? Kanske är smidigt, annars tar vi bort
    private boolean atGoal; //Kanske helt onödigt, då man kan ha en plats i arrayen som representerar om den är hemma/i mål, dock lättare att förstå koden såhär.

    public Piece(String colour){   //konstruktor för Piece, offset beroende på färg för var de startar (utgår från att brädet är en array, justera offset om inre "målvägar" är del av den).

        this.atHome = true;
        this.atGoal = false;
        this.colour = colour;

        switch (colour){    //Offset start positions for pieces by colour, piece ska dock antagligen EJ ha koll på position. Ta isåfall bort hela switch-blocket + hjälpmetoder
            case "red":
                move_piece(0);
                break;

            case "green":
                move_piece(10);
                break;

            case "blue":
                move_piece(20);
                break;

            case "yellow":
                move_piece(30);
                break;
        }
    }

    public int get_pos(){
        return this.pos;
    }

    private void move_piece(int pos){
        this.pos += pos;
    }

    public String get_colour(){
        return this.colour;
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

}
