package org.group7.view;

import org.group7.controllers.Observer;

import javax.swing.*;
import java.awt.*;

public class DrawBoard extends JPanel implements Observer {


    @Override
    public void update() {
        repaint();
    }
}

