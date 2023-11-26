package org.group7.view;

import org.group7.model.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PaintableBoard extends JPanel{

    private List<PaintableTile> paintableTiles;
    private List<Integer> gamePathTileIndex;
    private HashMap<Integer, Box> indexBoxHashMap;

    public PaintableBoard(Board board){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);
        this.gamePathTileIndex = new ArrayList<>(40); //Index for tiles that match game path
        this.indexBoxHashMap = new HashMap<>();
        drawBoardTiles();
        storeBoardTileIndex();
        initGamePathTiles();
    }

    private void storeBoardTileIndex(){
        int tileIndex = 0;
        for(Component component : this.getComponents()){
            if(component instanceof Box){
                //Put the box in hashmap with matching index as key for later use
                indexBoxHashMap.put(tileIndex, (Box) component);
                tileIndex++;
            }
        }
    }
    private void drawBoardTiles(){
        GridBagConstraints c = new GridBagConstraints();
        for(int y = 0; y < 11; y++) {
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for (int x = 0; x < 11; x++) {
                c.gridx = x;
                //This will create a 11x11 grid of boxes of equal size.
                Box box = new Box(Box.HEIGHT);
                box.setPreferredSize(new Dimension(91, 91));
                box.setBorder(BorderFactory.createLineBorder(Color.black));
                //Add to panel
                this.add(box, c);
            }
        }
    }

    private void initGamePathTiles(){
        Collections.addAll(this.gamePathTileIndex, 44, 45, 46, 47, 48, 37, 26, 15, 4, 5, 6, 17, 28, 39, 50,
        51, 52, 53, 54, 65, 76, 75, 74, 73, 72, 83, 94, 105, 116, 115, 114, 102, 92, 81, 70, 69, 68, 67, 66, 55);
    }

    public List<Integer> getGamePathTileIndexes(){
        return this.gamePathTileIndex;
    }

    public HashMap<Integer, Box> getIndexBoxHashMap(){
        return this.indexBoxHashMap;
    }

    public List<PaintableTile> getPaintableTiles() {
        return paintableTiles;
    }
}
