package org.group7.view;

import org.group7.model.Piece;
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
    private List<Point> baseBoxPoints;
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
        this.baseBoxPoints = new ArrayList<>(16);
        this.indexBoxHashMap = new HashMap<>(TOTAL_AMOUNT_TILES);
        this.setLayout(new GridBagLayout());
        applyImage();
        initBaseBoxPoints();
        addFieldBoxes();
        addBaseBoxes();
        storeBoardTileIndex();
        initTileIndices();
        addBoardComponents();
    }

    private void applyImage(){
        try{
            this.image = ImageIO.read(new File("src/main/resources/board5.png"));
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

    public void refreshPaintableTiles(){
        for(PaintableTile paintableTile : paintableGoalTiles){
            paintableTile.removeAll();
            Tile tile = paintableTile.getTile();
            if(!tile.isEmpty()){
                paintableTile.add(PaintableEntityFactory.makePieceImage(tile.getPiece()));
            }
        }
        for(PaintableTile paintableTile : paintableFieldTiles){
            paintableTile.removeAll();
            Tile tile = paintableTile.getTile();
            if(!tile.isEmpty()){
                //TODO: Kom på ett sätt att getta paintablePiece motsvarande piece på rutan
                paintableTile.add(PaintableEntityFactory.makePieceImage(tile.getPiece()));
            }
        }
        for(PaintableBase paintableBase : paintableBases){
            paintableBase.redrawPieces();
        }
        this.repaint();
        this.revalidate();
    }

    private void addFieldBoxes(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        for(int y = 0; y < 11; y++) {
            c.gridy = y;
            for (int x = 0; x < 11; x++) {
                c.gridx = x;
                c.gridwidth = 1;
                c.gridheight = 1;
                Box box = new Box(BoxLayout.LINE_AXIS);
                box.setPreferredSize(new Dimension(91, 91));
                this.add(box, c);
            }
        }
    }

    private void addBaseBoxes(){
        remove1x1Boxes();
        addBaseBox(1,1);
        addBaseBox(1, 8);
        addBaseBox(8,1);
        addBaseBox(8,8);
    }

    private void addBaseBox(int row, int col) {
        Box box = new Box(BoxLayout.LINE_AXIS);
        box.setBackground(Color.black);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = col;
        c.gridy = row;
        c.gridwidth = 2;
        c.gridheight = 2;
        this.add(box, c);
    }

    private void remove1x1Boxes() {
        Component[] components = this.getComponents();
        int index = 0;
        for (Component component : components){
            if(index == 16){
                break; //"you're breaking my heart" - Padmé Amidala in ROTS
                        //"the women and the children too" - Anakin Skywalker (Gigachad) in the same trilogy
            }
            Point basePoint = baseBoxPoints.get(index);
            GridBagConstraints gbc = ((GridBagLayout) this.getLayout()).getConstraints(component);
            if (gbc.gridx == basePoint.getY() && gbc.gridy == basePoint.getX()) {
                this.remove(component);
                index++;
            }
        }
    }

    private void addBoardComponents(){
        addTilesToBox(fieldTileIndices, paintableFieldTiles);
        addBaseToBox(baseTileIndices, paintableBases);
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

    private void addBaseToBox(List<Integer> tileIndices, List<PaintableBase> paintableBase){
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
        Collections.addAll(this.fieldTileIndices, 36, 37, 38, 39, 40, 29, 20, 13, 4, 5, 6, 15, 22, 31, 42,
                43, 44, 45, 46, 57, 68, 67, 66, 65, 64, 75, 84, 91, 100, 99, 98, 89, 82, 73, 62, 61, 60, 59, 58, 47);
    }

    private void initBaseTileIndices(){
        Collections.addAll(this.baseTileIndices, 105, 106, 108, 107);
    }

    private void initGoalTileIndices(){
        Collections.addAll(this.goalTileIndices, 48, 49, 50, 51, 14, 21, 30, 41, 56, 55, 54, 53,
                90, 83, 74, 63);
    }

    private void initBaseBoxPoints(){
        Collections.addAll(baseBoxPoints, new Point(1, 1), new Point(1, 2), new Point(1, 8),
                new Point(1, 9), new Point(2, 1), new Point(2, 2), new Point(2, 8),
                new Point(2,9), new Point(8, 1), new Point(8, 2), new Point(8,8),
                new Point(8, 9), new Point(9,1), new Point(9,2), new Point(9,8),
                new Point(9,9));
    }
}
