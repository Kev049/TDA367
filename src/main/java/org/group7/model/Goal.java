package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

//public class Goal implements IInsertable, Observable {
public class Goal implements IInsertable {
    private Piece piece;
    //private static Goal instance;

    public Goal(){

    }
    @Override
    public void insertPiece(Piece p) {
        this.piece = p;
        removePiece();
    }

    @Override
    public void removePiece() {
        this.piece.setPos(-1);
        this.piece = null;
        System.out.println("goal!");
        // om Goal är Observable så kan vi lägga till at vi räknar pjäser i mål eller liknade här
    }


//    public static Goal getInstance() {
//        if(instance == null){
//            instance = new Goal();
//        }
//        return instance;
//    }
}
