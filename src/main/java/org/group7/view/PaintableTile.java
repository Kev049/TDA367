package org.group7.view;

import javax.swing.*;

import org.group7.controllers.BoardController;
import org.group7.model.Tile;

public class PaintableTile extends JButton {

    private Tile tile = null;

    public PaintableTile(Tile tile, BoardController c){
        this.tile = tile;
        addActionListener(c);
    }

}

/*
    private void drawTiles(JPanel boardPanel){
        GridBagConstraints c = new GridBagConstraints();
        for(int y = 0; y < 11; y++){
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for(int x = 0; x < 11; x++){
                c.gridx = x;
                This will create a 11x11 grid of boxes of equal size.
    Box box = new Box(Box.HEIGHT);
                box.setPreferredSize(new Dimension(91, 91));
                        box.setBorder(BorderFactory.createLineBorder(Color.black));

                        //Save the coordinates for box
                        Point coordinate = new Point(x, y);
                        this.boardTileCoordinates.add(coordinate);

                        //Put the box in hashmap with matching coordinates as key for later use
                        boxPointHashMap.put(coordinate, box);
                        boardPanel.add(box, c);
                        }
                        }
                        }
 */