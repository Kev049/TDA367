package org.group7.controllers;

import org.group7.model.Base;
import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.PaintableBase;
import org.group7.view.PaintableTile;

import java.util.ArrayList;
import java.util.List;

public class BoardController{
    List<PaintableTile> paintableFieldTiles;
    List<PaintableBase> paintableBases;
    Game game;
    public BoardController(List<PaintableTile> paintableFieldTiles, List<PaintableBase> paintableBases, Game game){
        this.paintableFieldTiles = paintableFieldTiles;
        this.paintableBases = paintableBases;
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
        for(PaintableBase paintableBase : paintableBases){
            paintableBase.addActionListener(e -> {
                PaintableBase baseGUI = (PaintableBase) e.getSource();
                Base base = baseGUI.getBase();
            });
        }
    }
}
