package org.group7.model;

public class PowerUp extends Entity{

    public PowerUp(){
        super(11);   //konstruktor för Piece, offset beroende på färg för var de startar (utgår från att brädet är en array, justera offset om inre "målvägar" är del av den).

    }
    @Override
    public void handleCollision(Piece piece){
        System.out.println("hi");
    }
    @Override
    public int get_pos() {
        return 0;
    }
}
