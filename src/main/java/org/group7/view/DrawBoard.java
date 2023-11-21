package org.group7.view;

import org.group7.controllers.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Set;

public class DrawBoard extends JPanel implements Observer {
    private Set<PaintPieces> pieces;
    private Image BackgroundImage;
    //private final

    public DrawBoard(Set<PaintPieces> pieces) {
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
    //}

    @Override
    public void update() {
        repaint();
    }



    /*Koordinater notes:
    heliga koordinater för ruta 0 494 410
    45 pixlar på pieces,
    65 pixlar på tiles

    Arraylist<Point> coordinates = new Arraylist;

    coordinates[0] = new Point(494,410); //START RÖD

    coordinates[1] = new Point(581,410);
    coordinates[2] = new Point(668,410);
    coordinates[3] = new Point(775,410);
    coordinates[4] = new Point(842,410);

    coordinates[5] = new Point(842,323);
    coordinates[6] = new Point(842,236);
    coordinates[7] = new Point(842,149);
    coordinates[8] = new Point(842,62);

    coordinates[9] = new Point(929,62);
    coordinates[10] = new Point(1016,62); //START GRÖN

    coordinates[11] = new Point(1016,149);
    coordinates[12] = new Point(1016,236);
    coordinates[13] = new Point(1016,323);
    coordinates[14] = new Point(1016,410);

    coordinates[15] = new Point(1103,410);
    coordinates[16] = new Point(1190,410);
    coordinates[17] = new Point(1277,410);
    coordinates[18] = new Point(1364,410);

    coordinates[19] = new Point(1364,497);
    coordinates[20] = new Point(1364,584); //START BLÅ

    coordinates[21] = new Point(1277,584);
    coordinates[22] = new Point(1190,584);
    coordinates[23] = new Point(1103,584);
    coordinates[24] = new Point(1016,584);

    coordinates[25] = new Point(1016,671);
    coordinates[26] = new Point(1016,758);
    coordinates[27] = new Point(1016,845);
    coordinates[28] = new Point(1016,932);

    coordinates[29] = new Point(929,932); //START GUL
    coordinates[30] = new Point(842,932);

    coordinates[31] = new Point(842,845);
    coordinates[32] = new Point(842,758);
    coordinates[33] = new Point(842,671);
    coordinates[34] = new Point(842,584);

    coordinates[35] = new Point(755,584);
    coordinates[36] = new Point(668,584);
    coordinates[37] = new Point(581,584);
    coordinates[38] = new Point(494,584);

    coordinates[39] = new Point(494,497);

    coordinates[40] = new Point(581,497); //Red nest
    coordinates[40] = new Point(668,497);
    coordinates[40] = new Point(755,497);
    coordinates[40] = new Point(842,497);

    coordinates[40] = new Point(929,62); //Green nest
    coordinates[40] = new Point(929,149);
    coordinates[40] = new Point(929,238);
    coordinates[40] = new Point(929,323);

    coordinates[40] = new Point(1277,497); //Blue nest
    coordinates[40] = new Point(1190,497);
    coordinates[40] = new Point(1103,497);
    coordinates[40] = new Point(1016,497);

    coordinates[40] = new Point(1016,845); //Yellow nest
    coordinates[40] = new Point(0,0);
    coordinates[40] = new Point(0,0);
    coordinates[40] = new Point(0,0);

    sen nästena, går inte nu ingen märker ingen kommer orka


     */

}

