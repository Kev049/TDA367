package org.group7.view;

import javax.swing.*;

import org.group7.controllers.BoardController;
import org.group7.model.Tile;

public class PaintableTile extends JButton {

    private Tile tile = null;
    private BoardController boardController;

//    public PaintableTile(Tile tile, BoardController c){
//        this.tile = tile;
//        addActionListener(c);
//    }

    public PaintableTile(){
    }

}
