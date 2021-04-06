package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.ui.visualobj.TileType;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualBoard;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Random;

public class Board extends JPanel {

    public static final int TILE_SOURCE_SIZE = 32;

    private int sizeX;
    private int sizeY;

    private Integer startPosX;
    private Integer startPosY;

    private final VisualCamera camera;
    private final VisualBoard board;
    private final HighlightManager highlightManager;

    private final Board self = this;

    public Board(int sizeX, int sizeY, VisualCamera camera) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = generateBoard(sizeX, sizeY);
        this.camera = camera;
        this.highlightManager = new HighlightManager(board, this, camera,false);
        this.startPosX = camera.getOffsetX();
        this.startPosY = camera.getOffsetY();

        addListeners();
    }

    private void addListeners() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //if(hoveredTile != null && e.getButton() == MouseEvent.BUTTON3) onHoveredTileClicked(hoveredTile);
                startPosX = null;
                startPosY = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(startPosX == null || startPosY == null){
                    startPosX = e.getX();
                    startPosY = e.getY();
                }else{
                    int offsetX = e.getX() - startPosX;
                    int offsetY = e.getY() - startPosY;
                    camera.addOffsetX(offsetX);
                    camera.addOffsetY(offsetY);
                    startPosX = e.getX();
                    startPosY = e.getY();
                    self.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private void onHoveredTileClicked(VisualTile hoveredTile) {
        System.out.println("Clicked on Tile " + hoveredTile.getTileType());
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.paint(g, this.camera);
    }

    private VisualBoard generateBoard(int sizeX, int sizeY) {
        VisualTile[][] tiles = new VisualTile[sizeX][sizeY];
        Random rand = new Random();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tiles[x][y] = new VisualTile(rand.nextBoolean() ? TileType.GRASS : TileType.BLOCKED_GRASS, x, y, new LinkedList<>());
            }
        }
        return new VisualBoard(tiles, new LinkedList<>());
    }
}
