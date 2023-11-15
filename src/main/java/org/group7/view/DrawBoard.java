package org.group7.view;

import org.group7.controllers.Observer;

import javax.swing.*;
import java.awt.*;

public class DrawBoard extends JPanel implements Observer {

    //private final

    public DrawBoard(){
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(700, 700));
        setBackground(Color.red);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*
        Toolkit t = Toolkit.getDefaultToolkit();
        Image background = t.getImage("Board.png");
        g.drawImage(background, 120,0,null);

         */
    }

    @Override
    public void update() {
        repaint();
    }
}

