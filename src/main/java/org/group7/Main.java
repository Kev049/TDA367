package org.group7;

import org.group7.model.Board;
import org.group7.view.DrawPanel;
import org.group7.view.BoardPanel;
//import controller.Game;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        //Game.addObserver(drawBoard);
        /*List<Tile> tiles = initTiles();*/
        new GameWindow("TurboFia", board);

    }

    /*public static List<Tile> initTiles(){
        List<Tile> tiles = new ArrayList<>(40);
        for(int i = 0; i < 40; i++){
            tiles.add(new Tile(i));
        }
        return tiles;
    }*/
}