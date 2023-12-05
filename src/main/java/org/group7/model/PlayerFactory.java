package org.group7.model;

import java.awt.*;

public class PlayerFactory {
    public static Player createPlayer(Color color){ //byt till abstract factory pattern?
        return new Player(color);
    }
}
