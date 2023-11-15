package org.group7;

import org.group7.view.DrawBoard;

public class Main {

    public static void main(String[] args) {
        DrawBoard drawBoard = new DrawBoard();
        new GameWindow("TurboFia", drawBoard);
    }

}