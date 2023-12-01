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

    private List<PaintableTile> paintableTiles;
    private List<Integer> gamePathTileIndex;
    private List<Integer> redGoalPathTileIndex;     //TODO använd Map istället för 4 separate listor (skicka färg, få lista)
    private List<Integer> greenGoalPathTileIndex;
    private List<Integer> yellowGoalPathTileIndex;
    private List<Integer> blueGoalPathTileIndex;
    private List<PaintablePiece> paintablePieces;

    private HashMap<Integer, PaintableTile> indexTileHashMap;
    private final int totalAmountTiles = 121;
    private Image image;

    public BoardPanel(List<PaintableTile> paintableTiles, List<PaintablePiece> paintablePieces){
        this.paintableTiles = paintableTiles;
        this.paintablePieces = paintablePieces;
        this.gamePathTileIndex = new ArrayList<>(40); //Index for tiles that match game path
        this.redGoalPathTileIndex = new ArrayList<>(4);
        this.greenGoalPathTileIndex = new ArrayList<>(4);
        this.yellowGoalPathTileIndex = new ArrayList<>(4);
        this.blueGoalPathTileIndex = new ArrayList<>(4);
        this.indexTileHashMap = new HashMap<>(totalAmountTiles); //Hashmap that matches tile with index
        this.setLayout(new GridBagLayout());
        applyImage();
        drawPieces();
        drawBoardTiles();
        storeBoardTileIndex();
        initGamePathTileIndex();
        initAllGoals();
    }

    private void drawPieces() {
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
            if(component instanceof PaintableTile){
                //Put the tile in hashmap with matching index as key for later use
                indexTileHashMap.put(tileIndex, (PaintableTile) component);
                tileIndex++;
            }
        }
    }

    private void drawBoardTiles(){
        GridBagConstraints c = new GridBagConstraints();
        int index = 0;
        for(int y = 0; y < 11; y++) {
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for (int x = 0; x < 11; x++) {
                c.gridx = x;
                //This will create a 11x11 grid of boxes of equal size.
                PaintableTile tile = paintableTiles.get(index);
                index++;
                //Add to panel
                this.add(tile, c);
            }
        }
    }

    private void initGamePathTileIndex(){
        Collections.addAll(this.gamePathTileIndex, 44, 45, 46, 47, 48, 37, 26, 15, 4, 5, 6, 17, 28, 39, 50,
        51, 52, 53, 54, 65, 76, 75, 74, 73, 72, 83, 94, 105, 116, 115, 114, 103, 92, 81, 70, 69, 68, 67, 66, 55);
    }

    private void initAllGoals(){
        initRedGoalPathTileIndex();
        initGreenGoalPathTileIndex();
        initYellowGoalPathTileIndex();
        initBlueGoalPathTileIndex();
    }

    private void initRedGoalPathTileIndex(){
        Collections.addAll(this.redGoalPathTileIndex, 56, 57, 58, 59);
    }

    private void initGreenGoalPathTileIndex(){
        Collections.addAll(this.greenGoalPathTileIndex, 16, 27, 38, 59);
    }

    private void initYellowGoalPathTileIndex(){
        Collections.addAll(this.yellowGoalPathTileIndex, 64, 63, 62, 61);
    }

    private void initBlueGoalPathTileIndex(){
        Collections.addAll(this.blueGoalPathTileIndex, 104, 93, 82, 71);
    }
    public List<Integer> getGamePathTileIndexes(){
        return this.gamePathTileIndex;
    }

    public HashMap<Integer, PaintableTile> getindexTileHashMap(){
        return this.indexTileHashMap;
    }
}