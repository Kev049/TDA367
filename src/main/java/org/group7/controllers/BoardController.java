package org.group7.controllers;

import org.group7.model.Game;
import org.group7.view.PaintableTile;

import java.util.ArrayList;
import java.util.List;

public class BoardController{
    List<PaintableTile> paintableTiles;
    BoardListener boardListener;
    public BoardController(List<PaintableTile> paintableTiles, Game game){
        this.paintableTiles = paintableTiles;
        this.boardListener = new BoardListener(game);
        addListeners();
    }

    private void addListeners(){
        for(PaintableTile paintableTile : paintableTiles){
            paintableTile.addActionListener(boardListener);
        }
    }
}
