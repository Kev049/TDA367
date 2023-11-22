package org.group7;

import org.group7.model.Tile;
import org.group7.view.DrawBoard;
//import controller.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DrawBoard drawBoard = new DrawBoard(new HashSet<>());
        //Game.addObserver(drawBoard);
        List<Tile> tiles = initTiles();
        new GameWindow("TurboFia", drawBoard);
    }

    public static List<Tile> initTiles(){
        List<Tile> tiles = new ArrayList<>(40);
        for(int i = 0; i < 40; i++){
            tiles.add(new Tile());
        }
        return tiles;
    }
}