package org.group7.model;


public class Board {

    private Base[] bases;
    private Tile[] field;
    private Tile[][] goal;

     public Board() {
         this.bases = new Base[4];
         this.field = new Tile[40];
         this.goal = new Tile[4][4];       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktioner
     }

     public void addEntityToBase(int playerNr, Entity e) {
         this.bases[playerNr].addEntity(e);
     }

    public Entity removeEntityFromBase(int playerNr) {
        return this.bases[playerNr].removeEntity();
    }

     //public void addEntityToField();

}
