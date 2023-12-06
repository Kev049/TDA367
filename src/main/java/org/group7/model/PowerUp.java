package org.group7.model;

public class PowerUp implements IEntity{ //borde väl vara en abstrakt klass?

    public PowerUp(){
           //konstruktor för Piece, offset beroende på färg för var de startar (utgår från att brädet är en array, justera offset om inre "målvägar" är del av den).

    }

    public void handleCollision(Piece piece){
        System.out.println("hi");
    }

    public int getPos() {
        return 0;
    }
}
