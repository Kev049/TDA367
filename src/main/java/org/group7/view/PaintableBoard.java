package org.group7.view;

import org.group7.model.Board;

import java.util.List;

public class PaintableBoard {

    private List<PaintableTile> paintableTiles;

    public PaintableBoard(Board board){


    }

    public List<PaintableTile> getPaintableTiles() {
        return paintableTiles;
    }
}
