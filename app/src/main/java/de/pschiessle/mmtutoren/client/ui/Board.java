package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.ui.visualobj.TileType;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualBoard;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {

    public static final int TILE_SOURCE_SIZE = 32;

    private int sizeX;
    private int sizeY;

    private Integer startPosX;
    private Integer startPosY;

    private final VisualCamera camera;

    private final VisualBoard board;

    private List<BufferedImage> grassImg;
    private BufferedImage rockImg;
    private BufferedImage hightlightImg;

    private VisualTile hoveredTile = null;

    private final Board self = this;

    public Board(int sizeX, int sizeY, VisualCamera camera) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = generateBoard(sizeX, sizeY);
        this.camera = camera;
        this.startPosX = camera.getOffsetX();
        this.startPosY = camera.getOffsetY();
        loadPictures();
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
                if(hoveredTile != null && e.getButton() == MouseEvent.BUTTON3) onHoveredTileClicked(hoveredTile);
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
                int posX = e.getX() - camera.getOffsetX();
                int posY = e.getY() - camera.getOffsetY();
                int iX = (int)(posX / (camera.getZoom() * TILE_SOURCE_SIZE));
                int iY = (int)(posY / (camera.getZoom() * TILE_SOURCE_SIZE));
                board.getTile(iX, iY).ifPresent(tile -> {
                    if(hoveredTile != null) hoveredTile.setHovered(false);
                    tile.setHovered(true);
                    hoveredTile = tile;
                    self.repaint();
                });
            }
        });
    }

    private void onHoveredTileClicked(VisualTile hoveredTile) {
        System.out.println("Clicked on Tile " + hoveredTile.getTileType());
    }

    public void loadPictures() {
        try {
            BufferedImage grass0 = ImageIO.read(getClass().getResource("/images/sprite_floor_grass_0.png"));
            BufferedImage grass1 = ImageIO.read(getClass().getResource("/images/sprite_floor_grass_1.png"));
            BufferedImage grass2 = ImageIO.read(getClass().getResource("/images/sprite_floor_grass_2.png"));
            BufferedImage highlight0 = ImageIO.read(getClass().getResource("/images/sprite_highlight_0.png"));
            BufferedImage rock = ImageIO.read(getClass().getResource("/images/sprite_floor_rock.png"));
            grassImg = List.of(grass0, grass1, grass2);
            rockImg = rock;
            hightlightImg = highlight0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        VisualTile[][] tiles = board.getTiles();
        Random ran = new Random();
        int size = (int) (TILE_SOURCE_SIZE * camera.getZoom());
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                int posX = x * size + camera.getOffsetX();
                int posY = y * size + camera.getOffsetY();
                if(posX > -size && posX < g.getClipBounds().width && posY > -size && posY < g.getClipBounds().height ) {
                    g.drawImage(grassImg.get(ran.nextInt(grassImg.size())), posX, posY, size, size, null);
                    if (tiles[x][y].getTileType() == TileType.BLOCKED_GRASS) {
                        g.drawImage(rockImg, posX, posY, size, size, null);
                    }
                    if (tiles[x][y].isHovered()) {
                        g.drawImage(hightlightImg, posX, posY, size, size, null);
                    }
                }
            }
        }
    }

    private VisualBoard generateBoard(int sizeX, int sizeY) {
        VisualTile[][] tiles = new VisualTile[sizeX][sizeY];
        Random rand = new Random();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tiles[x][y] = new VisualTile(rand.nextBoolean() ? TileType.GRASS : TileType.BLOCKED_GRASS, new LinkedList<>());
            }
        }
        return new VisualBoard(tiles, new LinkedList<>());
    }
}
