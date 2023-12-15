package org.group7.model.entities.powerups;

import org.group7.model.entities.EntityFactory;
import org.group7.model.entities.powerups.BasePowerUp;
import org.group7.model.entities.powerups.LaserPowerUp;
import org.group7.model.entities.powerups.LightningPowerUp;
import org.group7.model.entities.powerups.PowerUp;
import org.group7.model.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILightningPowerUpHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpGenerator {
    private final Random powerUpGenerator;
    private IBasePowerUpHandler baseHandler;
    private ILaserPowerUpHandler laserHandler;
    private ILightningPowerUpHandler lightningHandler;
    private List<PowerUp> powerUps;

    public PowerUpGenerator(IBasePowerUpHandler baseHandler, ILaserPowerUpHandler laserHandler,
                            ILightningPowerUpHandler lightningHandler) {
        long seed = System.currentTimeMillis() * 31;
        this.powerUpGenerator = new Random(seed);
        this.powerUps = new ArrayList<>();
        this.baseHandler = baseHandler;
        this.laserHandler = laserHandler;
        this.lightningHandler = lightningHandler;
    }

    public List<PowerUp> generatePowerUps() {
        powerUps.clear();
        LightningPowerUp lightningPowerUp = EntityFactory.createLightningPowerUp(lightningHandler);
        BasePowerUp basePowerUp = EntityFactory.createBasePowerUp(baseHandler);
        LaserPowerUp laserPowerUp = EntityFactory.createLaserPowerUp(laserHandler);
        powerUps.add(lightningPowerUp);
        powerUps.add(basePowerUp);
        powerUps.add(laserPowerUp);
        return powerUps;
    }
}
