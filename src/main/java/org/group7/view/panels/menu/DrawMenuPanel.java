package org.group7.view.panels.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class DrawMenuPanel extends JPanel {
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

    private void applyImage() {
        try {
            URL menuPath = DrawMenuPanel.class.getClassLoader().getResource("Menu.png");
            image = ImageIO.read(menuPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(680, 0, 0, 0);
        initPlayerButton(c);
    }

    private void initPlayerButton(GridBagConstraints c) {
        int width = 400;
        int height = 100;
        fourPlayerMenuButton.setFocusPainted(false);
        fourPlayerMenuButton.setContentAreaFilled(false);
        fourPlayerMenuButton.setBorderPainted(false);
        fourPlayerMenuButton.setPreferredSize(new Dimension(width, height));
        fourPlayerMenuButton.setMaximumSize(new Dimension(width, height));
        this.add(fourPlayerMenuButton, c);
    }

    public JButton getFourPlayerMenuButton() {
        return this.fourPlayerMenuButton;
    }
}

