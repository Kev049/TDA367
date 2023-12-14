package org.group7.view;

import org.group7.model.board.Tile;
import org.group7.view.paintables.PaintableTile;

public class PaintableTileFactory {
    public static PaintableTile createTile(Tile tile) {
        return new PaintableTile(tile);
    }
}
