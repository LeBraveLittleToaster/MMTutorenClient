package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.CustomButton;
import de.pschiessle.mmtutoren.client.network.NetConst;
import de.pschiessle.mmtutoren.client.network.Networker;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Application extends JFrame {

    public static final int BOTTOM_RIGHT_PANEL_WIDTH = 300;
    public static final int BOTTOM_RIGHT_PANEL_HEIGHT = 150;

    private Networker networker;

    public Application() {
        initRessources();
        initUI();
        initNetwork();
    }

    private void initRessources() {
        ImageRessourceLoader.getInstance().loadImages();
    }

    private void initNetwork() {
        this.networker = new Networker(NetConst.DEFAULT_URL, System.out::println);
        this.networker.connect();
    }

    private void initUI() {

        JLayeredPane layeredPane = new JLayeredPane();
        JPanel boardPanel = new Board(10,10, new VisualCamera(2, 0,0));
        JPanel uiPanel = new JPanel();

        ComponentListener resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                boardPanel.setBounds(0,0, layeredPane.getWidth(), layeredPane.getHeight());
                uiPanel.setBounds(
                        layeredPane.getWidth() - BOTTOM_RIGHT_PANEL_WIDTH,
                        layeredPane.getHeight() - BOTTOM_RIGHT_PANEL_HEIGHT,
                        BOTTOM_RIGHT_PANEL_WIDTH,
                        BOTTOM_RIGHT_PANEL_WIDTH);
                System.out.println("RESIZE TO=[" + getWidth() + "," + getHeight() + "]");
            }
        };
        layeredPane.addComponentListener(resizeListener);
        this.setPreferredSize(new Dimension(800, 400));
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBounds(0, 0, 200,300);

        boardPanel.setBackground(Color.BLUE);
        boardPanel.setBounds(0,0, layeredPane.getWidth(), layeredPane.getHeight());
        boardPanel.setOpaque(true);
        layeredPane.add(boardPanel, 0, 0);


        uiPanel.setBackground(Color.GREEN);
        uiPanel.setBounds(
                layeredPane.getWidth() - BOTTOM_RIGHT_PANEL_WIDTH,
                layeredPane.getHeight() - BOTTOM_RIGHT_PANEL_HEIGHT,
                BOTTOM_RIGHT_PANEL_WIDTH,
                BOTTOM_RIGHT_PANEL_WIDTH);
        uiPanel.setOpaque(true);
        layeredPane.add(uiPanel, 1, 0);
        pack();
        setVisible(true);

        setTitle("Game me b***");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
