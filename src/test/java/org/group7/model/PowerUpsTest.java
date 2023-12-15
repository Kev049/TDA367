package org.group7.model;

import org.group7.model.board.Board;
import org.group7.model.entities.powerups.BasePowerUp;
import org.group7.model.entities.powerups.LaserPowerUp;
import org.group7.model.entities.powerups.LightningPowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PowerUpsTest {
    Board board;
    Color[] colorArray = new Color[4];
    @BeforeEach

    void setUp(){
        colorArray[0] = Color.RED;
        colorArray[1] = Color.GREEN;
        colorArray[2] = Color.BLUE;
        colorArray[3] = Color.YELLOW;
        board = new Board(colorArray);
    }
    @Test
    void getPosGivesCorrectionPosition(){
        LightningPowerUp lightningPowerUp = new LightningPowerUp(board);
        lightningPowerUp.setPos(0);
        assertEquals(lightningPowerUp.getPos(),0);
    }

    @Test
    void setPosChangesPosition(){
        LightningPowerUp lightningPowerUp = new LightningPowerUp(board);
        lightningPowerUp.setPos(0);
        int prevPos = lightningPowerUp.getPos();
        lightningPowerUp.setPos(1);
        assertNotEquals(prevPos, lightningPowerUp.getPos());
    }

    @Test
    void getLightningPowerUpNameGivesRightName(){
        LightningPowerUp lightningPowerUp = new LightningPowerUp(board);
        assertEquals(lightningPowerUp.getPowerUpName(),"Lightning");
    }

    @Test
    void getLaserPowerUpNameGivesRightName(){
        LaserPowerUp laserPowerUp = new LaserPowerUp(board);
        assertEquals(laserPowerUp.getPowerUpName(),"Laser");
    }

    @Test
    void getBasePowerUpNameGivesRightName(){
        BasePowerUp basePowerUp = new BasePowerUp(board);
        assertEquals(basePowerUp.getPowerUpName(),"Base");
    }
}


