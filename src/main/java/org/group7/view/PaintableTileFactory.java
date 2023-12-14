package org.group7.view;

import org.group7.model.Tile;

public class PaintableTileFactory {

    public static PaintableTile createTile(Tile tile) {
        return new PaintableTile(tile);
    }
}
