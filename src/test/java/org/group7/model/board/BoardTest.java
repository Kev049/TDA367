package org.group7.model.board;

import org.group7.model.entities.Entity;
import org.group7.model.entities.EntityFactory;
import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.BasePowerUp;
import org.group7.model.entities.powerups.LaserPowerUp;
import org.group7.model.entities.powerups.LightningPowerUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;
    Color[] colorArray = new Color[4];;

    @BeforeEach
    void setUp() {
        colorArray[0] = Color.RED;
        colorArray[1] = Color.GREEN;
        colorArray[2] = Color.BLUE;
        colorArray[3] = Color.YELLOW;
        board = new Board(colorArray);
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void pieceAddedToFieldCanBeFoundInTile() {
        Piece piece = new Piece(Color.RED, board);
        if(!board.getFieldTiles()[0].isEmpty()){
            board.getFieldTiles()[0].removeEntity();
        }
        int index = 0;
        board.addPiece(piece, index);
        assertEquals(piece, board.getFieldTiles()[index].getEntity());
    }

    @Test
    void pieceAddedToFieldWillNotBeInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        int index = 0;
        board.addPiece(piece, index);
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void PieceAddedToGoalStretchWillBeInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 0);
        assertTrue(piece.isAtGoalStretch());
    }

    @Test
    void returnPieceToBaseCannotReturnPieceIfBaseIsFull() {
        Piece piece = new Piece(Color.RED, board);
        board.addPiece(piece, 0);
        board.returnPieceToBase(piece);
        assertNotEquals(piece, board.getBases().get(0).getPieces()[0]);
    }

    @Test
    void returnPieceToBaseWillReturnPieceIfBaseIsNotFull() {
        Color color = Color.RED;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 0);
        board.returnPieceToBase(piece);
        assertEquals(piece, board.getBaseFromColor(color).getPieces()[3]);
    }

    @Test
    void returnPieceToBaseWillReturnToSameColorBase(){
        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 0);
        for(int j = 0; j < 3; j++){
            board.extractPieceFromBase(color);
        }
        board.returnPieceToBase(piece);
        assertEquals(piece, board.getBaseFromColor(color).getPieces()[0]);
    }

    @Test
    void returnPieceToBaseWillNotReturnPieceToBaseOfOtherColor(){

        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 0);
        int piecesInBoards = 0;
        for(Base base : board.getBases()){
            while(!base.isEmpty()){
                base.removePiece();
            }
            piecesInBoards += base.getPieceAmount();
        }
        board.returnPieceToBase(piece);
        assertEquals(board.getBaseFromColor(color).getPieceAmount(), piecesInBoards + 1);
    }
    @Test
    void insertPiecesAndPowerUpsAndRemoveFromEightTilesInField() { //kolla med Liam hur denna ska funka
        Tile[] field = board.getFieldTiles();
        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 10);
        Piece piece1 = board.extractPieceFromBase(color);
        board.addPiece(piece1, 11);
        BasePowerUp basePowerUp = EntityFactory.createBasePowerUp(board);
        field[12].insertPowerUp(basePowerUp);
        LightningPowerUp lightningPowerUp = EntityFactory.createLightningPowerUp(board);
        field[13].insertPowerUp(lightningPowerUp);
        LaserPowerUp laserPowerUp = EntityFactory.createLaserPowerUp(board);
        field[14].insertPowerUp(laserPowerUp);
        board.removeEntitiesFromField(9, 9);

        int numberOfNullEntities = 0;
        for(int i = 10; i < 15; i++){
            if(field[i].isEmpty()){
                numberOfNullEntities++;
            }
        }
        assertEquals(5, numberOfNullEntities);
    }

    @Test
    void removePowerUpFromField() {
        Tile[] field = board.getFieldTiles();
        BasePowerUp basePowerUp = EntityFactory.createBasePowerUp(board);
        field[0].insertPowerUp(basePowerUp);
        board.removePowerUpFromField(basePowerUp);
        assertNull(field[0].getEntity());
    }

    @Test
    void extractPieceFromBaseReturnsPieceOfCorrectColor() {
        Color color = Color.RED;
        Piece piece = board.extractPieceFromBase(color);
        assertEquals(color, piece.getColor());
    }

    @Test
    void extractPieceFromBaseRemovesOnePieceFromBase() {
        Color color = Color.RED;
        int piecesInBase = board.getBaseFromColor(color).getPieceAmount();
        board.extractPieceFromBase(color);
        assertEquals(piecesInBase - 1, board.getBaseFromColor(color).getPieceAmount());
    }

    @Test
    void pieceFromBaseToFieldAddsPieceToField() { //TODO: Kolla på denna och avgör om den är för "hårdkodad"
        Color color = Color.RED;
        Entity willBeNull = board.getFieldTiles()[0].getEntity();
        board.pieceFromBaseToField(color, 1);
        Entity entity = board.getFieldTiles()[0].getEntity();
        assertNotEquals(entity, willBeNull);
    }

    @Test
    void pieceNoLongerAtGoalStretchIfBouncesToField() {
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 3);
        board.movePiece(piece,6);
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void pieceCanBounceToField(){
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 3);
        removeEntityIfTileIsNotEmpty(board, 39);
        board.movePiece(piece,6);
        assertEquals(piece, board.getFieldTiles()[39].getEntity());
    }

    @Test
    void pieceCanBeMovedInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        int steps = 1;
        int diceRoll = 2;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        board.getGoalTiles().get(steps+diceRoll).getEntity();
    }

    @Test
    void pieceBouncesWhenItMovesFurtherThanGoal() {
        Piece piece = new Piece(Color.RED, board);
        int goalPos = 4;
        int steps = 1;
        int diceRoll = 6;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertTrue(piece.getPos() < goalPos);
    }

    @Test
    void pieceDoesntBounceWhenItDoesntMovePastGoal() {
        Piece piece = new Piece(Color.RED, board);
        int goalPos = 4;
        int steps = 1;
        int diceRoll = 2;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertTrue(piece.getPos() < goalPos);
    }

    @Test
    void goalRemovesPieceWhenItMovesToGoal() {
        Piece piece = new Piece(Color.RED, board);
        int steps = 1;
        int diceRoll = 3;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertEquals( -1,piece.getPos());
    }

    @Test
    void movePieceWillMovePieceAsMuchAsDiceRoll() {
        int diceRoll = 1;
        Piece piece = new Piece(Color.GREEN, board);
        board.addPiece(piece, 0);
        int piecePos = piece.getPos();
        board.movePiece(piece, diceRoll);
        assertEquals(piecePos + diceRoll, piece.getPos());
    }

    @Test
    void movePieceWillMovePieceToGoalStretchIfItMovesPastLastTile(){
        Piece piece = new Piece(Color.RED, board);
        int lastTileForRed = 39;
        board.addPiece(piece, lastTileForRed);
        board.movePiece(piece, 1);
        assertTrue(piece.isAtGoalStretch());
    }

    @Test
    void pieceWillBeRemovedItIsAddedToGoal(){
        Color color = Color.RED;
        int initialAmountOfRedPieces = board.getPieceAmount(color);
        Piece piece = board.getBaseFromColor(Color.red).removePiece();
        board.addPieceToGoalStretch(piece, 0);
        int goalPos = 4;
        board.movePiece(piece, 4);
        assertEquals(initialAmountOfRedPieces - 1,board.getPieceAmount(color));
    }

    @Test
    void spawnAmountOfPowerupsThatIsMoreThanOrEqualsToThree() {
        board.spawnPowerUps();
        Tile[] field = board.getFieldTiles();
        int numberOfPowerUps = 0;
        for(Tile tile : field){
            if(tile.getEntity() instanceof BasePowerUp || tile.getEntity() instanceof LightningPowerUp || tile.getEntity() instanceof LaserPowerUp){
                numberOfPowerUps++;
            }
        }
        Boolean hasSpawnedRightAmountOfPowerUps = null;
        if(numberOfPowerUps <= 3){
            hasSpawnedRightAmountOfPowerUps = false;
        }
        else{
            hasSpawnedRightAmountOfPowerUps = true;
        }
        assertTrue(hasSpawnedRightAmountOfPowerUps);
    }

    @Test
    void getBases() {
        List<Base> bases = board.getBases();
        assertEquals(4, bases.size());
    }

    @Test
    void getRightColorOfBaseFromBoard() {
        Color color = Color.RED;
        Base base = board.getBaseFromColor(color);
        assertEquals(color, base.getColor());
    }

    @Test
    void getRightAmountOfFieldTilesFromBoard() {
        Tile[] field = board.getFieldTiles();
        assertEquals(40, field.length);
    }

    @Test
    void getRightAmountOfGoalStretchTilesFromBoard() {
        List<IInsertable> goalStretchTiles = board.getGoalTiles();
        assertEquals(16, goalStretchTiles.size());
    }

    @Test
    void getRightAmountOfPiecesOfRightColorFromBoard() {
        Color color = Color.RED;
        int amountOfPieces = board.getPieceAmount(color);
        assertEquals(4, amountOfPieces);
    }

    @Test
    void pieceHasCollidedWithAnotherPieceOfOtherColorAndSentItToBase(){
        Piece piece = new Piece(Color.RED, board);
        Piece piece1 = new Piece(Color.GREEN, board);
        removeEntityIfTileIsNotEmpty(board, 0);
        removeEntityIfTileIsNotEmpty(board, 1);
        board.addPiece(piece, 0);
        board.addPiece(piece1, 1);
        board.movePiece(piece, 1);
        assertEquals(piece1.getPos(), -1);
    }

    @Test
    void pieceHasCollidedWithAnotherPieceOfSameColorAndMovedPast(){
        Piece piece = new Piece(Color.RED, board);
        Piece piece1 = new Piece(Color.RED, board);
        removeEntityIfTileIsNotEmpty(board, 0);
        removeEntityIfTileIsNotEmpty(board, 1);
        board.addPiece(piece, 0);
        board.addPiece(piece1, 1);
        board.movePiece(piece, 1);
        assertEquals(piece.getPos(), 2);
    }

    @Test
    void pieceHasCollidedWithLightningPowerUpAndMovedTwoSteps(){
        Piece piece = new Piece(colorArray[0], board);
        LightningPowerUp lightningPowerUp = EntityFactory.createLightningPowerUp(board);
        removeEntityIfTileIsNotEmpty(board, 0);
        removeEntityIfTileIsNotEmpty(board, 2);
        board.addPiece(piece, 0);
        board.getFieldTiles()[2].insertPowerUp(lightningPowerUp);
        board.movePiece(piece, 2);
        assertEquals(piece.getPos(), 4);
    }

    @Test
    void pieceHasCollidedWithBasePowerUpAndExtractedPieceFromBase(){
        Piece piece = board.extractPieceFromBase(colorArray[0]);
        BasePowerUp basePowerUp = EntityFactory.createBasePowerUp(board);
        Base redBase = board.getBases().get(0);
        removeEntityIfTileIsNotEmpty(board, 0);
        removeEntityIfTileIsNotEmpty(board, 2);
        board.addPiece(piece, 0);
        board.getFieldTiles()[2].insertPowerUp(basePowerUp);
        board.movePiece(piece, 2);
        assertEquals(redBase.getPieceAmount(), 2);
    }

    @Test
    void pieceHasCollidedWithLaserPowerUpAndRemovedPieceFromField(){
        Piece redPiece = board.extractPieceFromBase(colorArray[0]);
        Piece bluePiece = board.extractPieceFromBase(colorArray[2]);
        LaserPowerUp laserPowerUp = EntityFactory.createLaserPowerUp(board);
        removeEntityIfTileIsNotEmpty(board, 0);
        removeEntityIfTileIsNotEmpty(board, 5);
        board.addPiece(redPiece, 0);
        board.addPiece(bluePiece, 5);
        removeEntityIfTileIsNotEmpty(board, 2);
        board.getFieldTiles()[2].insertPowerUp(laserPowerUp);
        board.movePiece(redPiece, 2);
        assertTrue(bluePiece.getPos() == -1);
    }

    void removeEntityIfTileIsNotEmpty(Board board, int index) {
        Tile[] field = board.getFieldTiles();
        if(!field[index].isEmpty()){
            field[index].removeEntity();
        }
    }

}