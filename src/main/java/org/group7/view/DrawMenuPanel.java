package org.group7.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrawMenuPanel extends JPanel{
    private Image image;
    private JButton fourPlayerMenuButton = new JButton();
    public DrawMenuPanel() {
        this.setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1920, 1080));
        applyImage();
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null); // see javadoc for more info on the parameters
    }
    private void applyImage(){
        try{
            this.image = ImageIO.read(new File("src/main/resources/Menu.png"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void initComponents(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(100, 0, 0, 0);
        fourPlayerMenuButton.setFocusPainted(false);
        fourPlayerMenuButton.setContentAreaFilled(false);
        fourPlayerMenuButton.setBorderPainted(false);
        fourPlayerMenuButton.setPreferredSize(new Dimension(400, 100));
        fourPlayerMenuButton.setMaximumSize(new Dimension(400, 100));
        this.add(fourPlayerMenuButton, c);
    }

    public JButton getFourPlayerMenuButton(){
        return this.fourPlayerMenuButton;
    }
}

