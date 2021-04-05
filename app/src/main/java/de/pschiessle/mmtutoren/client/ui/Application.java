package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.network.NetConst;
import de.pschiessle.mmtutoren.client.network.Networker;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private Networker networker;

    public Application(){
        initUI();
        initNetwork();
    }

    private void initNetwork() {
        this.networker = new Networker(NetConst.DEFAULT_URL, System.out::println);
    }

    private void initUI() {
        add(new Board(10,10, new VisualCamera(2, 0,0)));

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
