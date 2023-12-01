package org.group7.controllers;

import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.PaintableTile;

import java.util.ArrayList;
import java.util.List;

public class BoardController{
    List<PaintableTile> paintableFieldTiles;
    List<PaintableTile> paintableBaseTiles;
    Game game;
    public BoardController(List<PaintableTile> paintableFieldTiles, List<PaintableTile> paintableBaseTiles, Game game){
        this.paintableFieldTiles = paintableFieldTiles;
        this.paintableBaseTiles = paintableBaseTiles;
        this.game = game;
        addListeners();
    }

    private void addListeners(){
        for(PaintableTile paintableTile : paintableFieldTiles){
            paintableTile.addActionListener(e ->{
                PaintableTile fieldTile = (PaintableTile) e.getSource();
                Tile tile = fieldTile.getTile();
                game.movePiece(tile);
            });
        }
        for(PaintableTile paintableTile : paintableBaseTiles){
            paintableTile.addActionListener(e -> {
                PaintableTile baseTile = (PaintableTile) e.getSource();
                Tile tile = baseTile.getTile();
            });
        }
    }
}
