package org.group7.view;

import org.group7.controllers.BoardController;
import org.group7.model.Board;

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
    private HashMap<Integer, PaintableTile> indexBoxHashMap;
    private JPanel background;
    private Image image;
    private BoardController boardController;

    public BoardPanel(Board board, BoardController boardController){
        //Image image1 = new Image("src/main/resources/Board.png");
        //applyImage();
        this.boardController = boardController;
        this.setLayout(new GridBagLayout());
        //this.setBackground(Color.GRAY);
        this.gamePathTileIndex = new ArrayList<>(40); //Index for tiles that match game path
        this.indexBoxHashMap = new HashMap<>(); //Hashmap that matches tile with index
        //drawPieces();
        drawBoardTiles();
        storeBoardTileIndex();
        initGamePathTileIndex();
    }

    private void applyImage(){
        try{
            this.image = ImageIO.read(new File("src/main/resources/Board.png"));
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
                //Put the box in hashmap with matching index as key for later use
                indexBoxHashMap.put(tileIndex, (PaintableTile) component);
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
                //Box box = new Box(Box.HEIGHT);
                PaintableTile tile = new PaintableTile();
                tile.addActionListener(boardController);
                tile.setPreferredSize(new Dimension(91, 91));
//              tile.setOpaque(false);
                tile.setContentAreaFilled(false);
                tile.setBorderPainted(false);
                //box.setBorder(BorderFactory.createLineBorder(Color.black));
                //Add to panel
                this.add(tile, c);
            }
        }
    }

    private void initGamePathTileIndex(){
        Collections.addAll(this.gamePathTileIndex, 44, 45, 46, 47, 48, 37, 26, 15, 4, 5, 6, 17, 28, 39, 50,
        51, 52, 53, 54, 65, 76, 75, 74, 73, 72, 83, 94, 105, 116, 115, 114, 102, 92, 81, 70, 69, 68, 67, 66, 55);
    }

    public List<Integer> getGamePathTileIndexes(){
        return this.gamePathTileIndex;
    }

    public HashMap<Integer, PaintableTile> getIndexBoxHashMap(){
        return this.indexBoxHashMap;
    }

    public List<PaintableTile> getPaintableTiles() {
        return paintableTiles;
    }
}
