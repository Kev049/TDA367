package org.group7;

import org.group7.view.DrawBoard;
//import controller.Game;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        DrawBoard drawBoard = new DrawBoard(new HashSet<>());
        //Game.addObserver(drawBoard);
        new GameWindow("TurboFia", drawBoard);
    }
}