package org.group7.view;

import org.group7.controllers.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class DrawBoard extends JPanel implements Observer {
    private Image BackgroundImage;
    //private final

    public DrawBoard(){
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(700, 700));
        setBackground(Color.red);
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        try {
//            Image i = ImageIO.read(
//                    Objects.requireNonNull(
//                            DrawBoard.class.getResourceAsStream("group7/Board.png")
//                    )
//            );
//        } catch (Exception e) {
//            System.out.println("jjj");
//        }
//
//        g.drawImage(i, 100, 100, null);

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

