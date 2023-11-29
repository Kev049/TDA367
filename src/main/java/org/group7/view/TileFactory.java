package org.group7.view;

import org.group7.controllers.BoardListener;
import org.group7.model.Tile;

public class TileFactory {
    public static PaintableTile createTile(Tile tile,  BoardListener boardController){
            return new PaintableTile(tile, boardController);
    }
}
