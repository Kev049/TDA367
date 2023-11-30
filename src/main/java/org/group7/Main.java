package org.group7;

import org.group7.controllers.GameController;
import org.group7.model.Board;
import org.group7.model.Game;
import org.group7.view.DrawPanel;
import org.group7.view.BoardPanel;
//import controller.Game;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game();
        GameController gameController = new GameController(game);
        //Game.addObserver(drawBoard);
        new GameWindow("TurboFia", board, gameController);
    }
}