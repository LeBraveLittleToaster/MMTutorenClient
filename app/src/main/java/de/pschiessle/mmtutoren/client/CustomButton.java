package de.pschiessle.mmtutoren.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class CustomButton extends JButton {

    private final BufferedImage image;
    private final BufferedImage hoverImg;

    private CustomButton self = this;


    public CustomButton(String text, BufferedImage image, BufferedImage hoverImg) {
        super(text);
        this.image = image;
        this.hoverImg = hoverImg;
        setIcon(new ImageIcon(image.getScaledInstance( 100,50,  java.awt.Image.SCALE_SMOOTH ) ));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(hoverImg.getScaledInstance( 100,50,  java.awt.Image.SCALE_SMOOTH ) ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon(image.getScaledInstance( 100,50,  java.awt.Image.SCALE_SMOOTH ) ));
            }
        });
    }

}
