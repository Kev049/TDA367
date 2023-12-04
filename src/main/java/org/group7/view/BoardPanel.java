package org.group7.view;

import org.group7.controllers.BoardListener;
import org.group7.model.Board;
import org.group7.model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BoardPanel extends JPanel{
    private List<PaintableTile> paintableFieldTiles;
    private List<PaintableBase> paintableBases;
    private List<PaintableTile> paintableGoalTiles;
    private List<Integer> fieldTileIndices;
    private List<Integer> baseTileIndices;
    private List<Integer> goalTileIndices;
    private HashMap<Integer, Box> indexBoxHashMap;
    private final int TOTAL_AMOUNT_TILES = 121;
    private Image image;

    public BoardPanel(List<PaintableTile> paintableFieldTiles,
                      List<PaintableBase> paintableBases,
                      List<PaintableTile> paintableGoalTiles){
        this.paintableFieldTiles = paintableFieldTiles;
        this.paintableBases = paintableBases;
        this.paintableGoalTiles = paintableGoalTiles;
        this.fieldTileIndices = new ArrayList<>(40); //Index for paintableTiles that match game path
        this.baseTileIndices = new ArrayList<>(16);
        this.goalTileIndices = new ArrayList<>(16);
        this.indexBoxHashMap = new HashMap<>(TOTAL_AMOUNT_TILES);
        this.setLayout(new GridBagLayout());
        applyImage();
        addPanelBoxes();
        storeBoardTileIndex();
        initTileIndices();
        addBoardTiles();
    }

    private void applyImage(){
        try{
            this.image = ImageIO.read(new File("src/main/resources/board2.png"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null); // see javadoc for more info on the parameters
    }

    private void storeBoardTileIndex(){
        int tileIndex = 0;
        for(Component component : this.getComponents()){
            if(component instanceof Box){
                //Put the tile in hashmap with matching index as key for later use
                indexBoxHashMap.put(tileIndex, (Box) component);
                tileIndex++;
            }
        }
    }
    private void addPanelBoxes(){
        GridBagConstraints c = new GridBagConstraints();
        for(int y = 0; y < 11; y++) {
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for (int x = 0; x < 11; x++) {
                c.gridx = x;
                //This will create a 11x11 grid of boxes of equal size.
                Box box = new Box(BoxLayout.LINE_AXIS);
                box.setPreferredSize(new Dimension(91, 91));
                if(y == 1){
                    isBaseBox(c, x, box);
                }
                else if(y == 8){
                    isBaseBox(c, x, box);
                }
                else{
                    c.gridwidth = 1;
                    c.gridheight = 1;
                    this.add(box, c);
                }
            }
        }
    }

    private void isBaseBox(GridBagConstraints c, int x, Box box) {
        if(x == 1 || x == 8){
            box.setBackground(Color.RED);
            c.gridheight = 2;
            c.gridwidth = 2;
            this.add(box, c);
        }
        else if(x != 2 && x != 9){
            c.gridwidth = 1;
            c.gridheight = 1;
            this.add(box, c);
        }
    }

    private void addBoardTiles(){
        addTilesToBox(fieldTileIndices, paintableFieldTiles);
        addBaseTilesToBox(baseTileIndices, paintableBases);
        addTilesToBox(goalTileIndices, paintableGoalTiles);
    }

    private void addTilesToBox(List<Integer> tileIndices, List<PaintableTile> paintableTiles){
        int i = 0;
        for(int index : tileIndices){
            Box box = indexBoxHashMap.get(index);
            box.add(paintableTiles.get(i));
            i++;
        }
    }

    private void addBaseTilesToBox(List<Integer> tileIndices, List<PaintableBase> paintableBase){
        int i = 0;
        for(int index : tileIndices){
            Box box = indexBoxHashMap.get(index);
            box.add(paintableBase.get(i));
            i++;
        }
    }

    private void initTileIndices(){
        initFieldTileIndices();
        initBaseTileIndices();
        initGoalTileIndices();
    }

    private void initFieldTileIndices(){
        Collections.addAll(this.fieldTileIndices, 44, 45, 46, 47, 48, 37, 26, 15, 4, 5, 6, 17, 28, 39, 50,
        51, 52, 53, 54, 65, 76, 75, 74, 73, 72, 83, 94, 105, 116, 115, 114, 103, 92, 81, 70, 69, 68, 67, 66, 55);
    }

    private void initBaseTileIndices(){
        Collections.addAll(this.baseTileIndices, 12, 19, 89, 96);
    }

    private void initGoalTileIndices(){
        Collections.addAll(this.goalTileIndices, 56, 57, 58, 59, 16, 27, 38, 49, 64, 63, 62, 61, 104, 93,
                82, 71);
    }
}
