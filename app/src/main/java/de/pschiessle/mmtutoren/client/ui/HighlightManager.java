package de.pschiessle.mmtutoren.client.ui;

import de.pschiessle.mmtutoren.client.ui.visualobj.VisualBoard;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;
import de.pschiessle.mmtutoren.client.ui.visualobj.VisualTile;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static de.pschiessle.mmtutoren.client.ui.Board.TILE_SOURCE_SIZE;

public class HighlightManager {
    private final VisualCamera camera;
    private VisualBoard board;
    private JPanel observant;
    private VisualTile hoveredTile = null;
    private boolean isLocked = false;

    public HighlightManager(VisualBoard board, JPanel observant, VisualCamera camera, boolean isLocked) {
        this.board = board;
        this.observant = observant;
        this.camera = camera;
        this.isLocked = isLocked;
        if(!isLocked){
            addHoverListener(observant, camera);
        }
    }

    private void addHoverListener(JPanel observant, VisualCamera camera){
        observant.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

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
                    observant.repaint();
                });
            }
        });
    }
}
