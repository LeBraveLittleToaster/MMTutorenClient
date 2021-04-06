package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.CustomButton;
import de.pschiessle.mmtutoren.client.network.NetConst;
import de.pschiessle.mmtutoren.client.network.Networker;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Application extends JFrame {

    private Networker networker;

    public Application(){
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
        setLayout(new BorderLayout());
        add(new Board(10,10, new VisualCamera(2, 0,0)), BorderLayout.CENTER);

        CustomButton button = null;
        try {
            button = new CustomButton("hello",
                    ImageIO.read(getClass().getResource("/images/sprite_floor_rock.png")),
                    ImageIO.read(getClass().getResource("/images/sprite_floor_grass_0.png")));
            button.setSize(100,50);
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(button, BorderLayout.SOUTH);
        //TESTING ONLY
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KEY=" + e.getKeyCode());
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("Sending message");
                    networker.send("Hello World");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setSize(800,600);
        setTitle("Game me b***");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex  = new Application();
            ex.setVisible(true);
        });
    }
}
