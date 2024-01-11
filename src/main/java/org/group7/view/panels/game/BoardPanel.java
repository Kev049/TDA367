package org.group7.view.panels.game;

import org.group7.controller.observe.Observer;
import org.group7.model.board.IInsertable;
import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;
import org.group7.view.PaintableEntityFactory;
import org.group7.view.paintables.PaintablePowerUp;
import org.group7.view.paintables.PaintableBase;
import org.group7.view.paintables.PaintablePiece;
import org.group7.view.paintables.PaintableTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BoardPanel extends JPanel implements Observer{
    private final List<PaintableTile> paintableFieldTiles;
    private final List<PaintableBase> paintableBases;
    private final List<PaintableTile> paintableGoalStretchTiles;
    private final List<PaintablePiece> paintablePieces;
    private final List<Integer> fieldTileIndices;
    private final List<Integer> baseTileIndices;
    private final List<Integer> goalTileIndices;
    private final List<Point> baseBoxPoints;
    private final HashMap<Integer, Box> indexBoxHashMap;
    private final HashMap<Piece, PaintablePiece> piecePaintablePieceHashMap;
    private static final int TOTAL_AMOUNT_TILES = 121;
    private static final int FIELD_START_CAPACITY = 40;
    private static final int BASE_TILES_CAPACITY = 16;
    private static final int GOAL_TILES_CAPACITY = 16;
    private Image image;
    private PaintableEntityFactory paintableEntityFactory;

    public BoardPanel(List<PaintableTile> paintableFieldTiles,
                      List<PaintableBase> paintableBases,
                      List<PaintableTile> paintableGoalStretchTiles,
                      List<PaintablePiece> paintablePieces) {

        this.paintableFieldTiles = paintableFieldTiles;
        this.paintableBases = paintableBases;
        this.paintableGoalStretchTiles = paintableGoalStretchTiles;
        this.paintablePieces = paintablePieces;
        this.fieldTileIndices = new ArrayList<>(FIELD_START_CAPACITY);
        this.baseTileIndices = new ArrayList<>(BASE_TILES_CAPACITY);
        this.goalTileIndices = new ArrayList<>(GOAL_TILES_CAPACITY);
        this.baseBoxPoints = new ArrayList<>(BASE_TILES_CAPACITY);
        this.indexBoxHashMap = new HashMap<>(TOTAL_AMOUNT_TILES);
        this.piecePaintablePieceHashMap = new HashMap<>();
        this.paintableEntityFactory = new PaintableEntityFactory();
        this.setLayout(new GridBagLayout());
        initBaseBoxPoints();
        initTileIndices();
        initPaintablePiecesMap();
        applyImage();
        addFieldBoxes();
        addBaseBoxes();
        storeBoardTileIndex();
        addBoardComponents();
        drawBoard();
    }

    private void applyImage() {
        try {
            URL path = BoardPanel.class.getClassLoader().getResource("Board.png");
            this.image = ImageIO.read(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null); // see javadoc for more info on the parameters
    }

    private void storeBoardTileIndex() {
        int tileIndex = 0;
        for (Component component : this.getComponents()) {
            if (component instanceof Box box) {
                //Put the tile in hashmap with matching index as key for later use
                indexBoxHashMap.put(tileIndex, box);
                tileIndex++;
            }
        }
    }
    @Override
    public void update(){
        drawBoard();
    }
    public void drawBoard() {
        drawPaintableTiles(paintableGoalStretchTiles);
        drawPaintableTiles(paintableFieldTiles);
        redrawPaintableBases(paintableBases);
        this.repaint();
        this.revalidate();
    }

    private void drawPaintableTiles(List<PaintableTile> paintableGoalStretchTiles) {
        for (PaintableTile paintableTile : paintableGoalStretchTiles) {
            IInsertable tile = paintableTile.getTile();
            paintableTile.removeAll();
            if (!(tile.isEmpty())) {
                if (tile.getEntity() instanceof Piece) {
                    PaintablePiece paintablePiece = piecePaintablePieceHashMap.get((Piece) tile.getEntity());
                    paintableTile.add(paintablePiece);
                } else {
                    PaintablePowerUp paintablePowerUp = paintableEntityFactory.makePaintedPowerUp((PowerUp) tile.getEntity());
                    paintableTile.add(paintablePowerUp);
                }
            }
        }
    }

    private void redrawPaintableBases(List<PaintableBase> paintableBases) {
        for (PaintableBase paintableBase : paintableBases) {
            paintableBase.redrawPieces();
        }
    }

    private void addFieldBoxes() {
        int boxSize = 91;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        for (int y = 0; y < 11; y++) {
            c.gridy = y;
            for (int x = 0; x < 11; x++) {
                c.gridx = x;
                c.gridwidth = 1;
                c.gridheight = 1;
                Box box = new Box(BoxLayout.LINE_AXIS);
                box.setPreferredSize(new Dimension(boxSize, boxSize));
                this.add(box, c);
            }
        }
    }

    private void addBaseBoxes() {
        remove1x1Boxes();
        addBaseBox(1, 1);
        addBaseBox(1, 8);
        addBaseBox(8, 1);
        addBaseBox(8, 8);
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
        for (Component component : components) {
            if (index == 16) {
                break;
            }
            Point basePoint = baseBoxPoints.get(index);
            GridBagConstraints gbc = ((GridBagLayout) this.getLayout()).getConstraints(component);
            if (gbc.gridx == basePoint.getY() && gbc.gridy == basePoint.getX()) {
                this.remove(component);
                index++;
            }
        }
    }

    private void addBoardComponents() {
        addTilesToBox(fieldTileIndices, paintableFieldTiles);
        addBaseToBox(baseTileIndices, paintableBases);
        addTilesToBox(goalTileIndices, paintableGoalStretchTiles);
    }

    private void addTilesToBox(List<Integer> tileIndices, List<PaintableTile> paintableTiles) {
        int i = 0;
        for (int index : tileIndices) {
            Box box = indexBoxHashMap.get(index);
            box.add(paintableTiles.get(i++));
        }
    }

    private void addBaseToBox(List<Integer> tileIndices, List<PaintableBase> paintableBase) {
        int i = 0;
        for (int index : tileIndices) {
            Box box = indexBoxHashMap.get(index);
            box.add(paintableBase.get(i++));
        }
    }

    private void initTileIndices() {
        initFieldTileIndices();
        initBaseTileIndices();
        initGoalTileIndices();
    }

    private void initFieldTileIndices() {
        Collections.addAll(this.fieldTileIndices, 36, 37, 38, 39, 40, 29, 20, 13, 4, 5, 6, 15, 22, 31, 42,
                43, 44, 45, 46, 57, 68, 67, 66, 65, 64, 75, 84, 91, 100, 99, 98, 89, 82, 73, 62, 61, 60, 59, 58, 47);
    }

    private void initBaseTileIndices() {
        Collections.addAll(this.baseTileIndices, 105, 106, 108, 107);
    }

    private void initGoalTileIndices() {
        Collections.addAll(this.goalTileIndices, 48, 49, 50, 51, 14, 21, 30, 41, 56, 55, 54, 53,
                90, 83, 74, 63);
    }

    private void initBaseBoxPoints() {
        Collections.addAll(baseBoxPoints, new Point(1, 1), new Point(1, 2), new Point(1, 8),
                new Point(1, 9), new Point(2, 1), new Point(2, 2), new Point(2, 8),
                new Point(2, 9), new Point(8, 1), new Point(8, 2), new Point(8, 8),
                new Point(8, 9), new Point(9, 1), new Point(9, 2), new Point(9, 8),
                new Point(9, 9));
    }

    private void initPaintablePiecesMap() {
        for (PaintablePiece paintablePiece : paintablePieces) {
            Piece piece = paintablePiece.getPiece();
            piecePaintablePieceHashMap.put(piece, paintablePiece);
        }
    }
}
