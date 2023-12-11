package org.group7.model;

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
    }

//    public static Goal getInstance() {
//        if(instance == null){
//            instance = new Goal();
//        }
//        return instance;
//    }
}
