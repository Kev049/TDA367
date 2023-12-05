package org.group7.controllers;

import org.group7.model.*;
import org.group7.view.BoardPanel;
import org.group7.view.PaintableBase;
import org.group7.view.PaintablePiece;
import org.group7.view.PaintableTile;

import java.util.ArrayList;
import java.util.List;

public class BoardController{
    List<PaintableTile> paintableFieldTiles;
    List<PaintableBase> paintableBases;
    Game game;
    Board board;
    BoardPanel boardPanel;
    public BoardController(List<PaintableTile> paintableFieldTiles, List<PaintableBase> paintableBases,
                           Game game, Board board, BoardPanel boardPanel){
        this.board = board;
        this.boardPanel = boardPanel;
        this.paintableFieldTiles = paintableFieldTiles;
        this.paintableBases = paintableBases;
        this.game = game;
        addListeners();
    }

    private void addListeners(){
        for(PaintableTile paintableTile : paintableFieldTiles){
            paintableTile.addActionListener(e ->{
                PaintableTile eventPaintableTile = (PaintableTile) e.getSource();
                Tile tile = eventPaintableTile.getTile();
                game.movePiece(tile);
            });
        }
        for(PaintableBase paintableBase : paintableBases){
            paintableBase.addActionListener(e -> {
                PaintableBase eventPaintableBase = (PaintableBase) e.getSource();
                Base base = eventPaintableBase.getBase();
                board.pieceFromBaseToField(base);
                //PaintablePiece paintablePiece = eventPaintableBase.removePaintablePiece(p);
                //boardPanel.addPieceToStartTile(paintablePiece);
                boardPanel.repaint();
                boardPanel.revalidate();
            });
        }
    }
}
