package de.pschiessle.mmtutoren.client.ui.visualobj;

import de.pschiessle.mmtutoren.client.ui.ImageRessourceLoader;
import de.pschiessle.mmtutoren.client.ui.visualobj.paint.VisualPaint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import static de.pschiessle.mmtutoren.client.ui.Board.TILE_SOURCE_SIZE;

public class VisualTile extends VisualPaint {
    private TileType tileType;

    private int x;
    private int y;
    private List<VisualEntity> entities;

    private boolean isHoverable = false;
    private boolean isHovered = false;

    public VisualTile(TileType tileType, int x, int y, List<VisualEntity> entities) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
        this.entities = entities;
    }

    public boolean isHoverable() {
        return isHoverable;
    }

    public void setHoverable(boolean hoverable) {
        isHoverable = hoverable;
    }

    public boolean isHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }


    public List<VisualEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<VisualEntity> entities) {
        this.entities = entities;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public void paint(Graphics g, VisualCamera camera) {
        List<BufferedImage> grassImgs = ImageRessourceLoader.getInstance().getGrassImgs();
        BufferedImage rockImg = ImageRessourceLoader.getInstance().getRockImg();
        BufferedImage hightlightImg = ImageRessourceLoader.getInstance().getHightlightImg();
        Random ran = new Random();

        int size = (int) (TILE_SOURCE_SIZE * camera.getZoom());
        int posX = x * size + camera.getOffsetX();
        int posY = y * size + camera.getOffsetY();
        if (posX > -size && posX < g.getClipBounds().width && posY > -size && posY < g.getClipBounds().height) {
            g.drawImage(grassImgs.get(ran.nextInt(grassImgs.size())), posX, posY, size, size, null);
            if (this.tileType == TileType.BLOCKED_GRASS) {
                g.drawImage(rockImg, posX, posY, size, size, null);
            }
            if (this.isHovered()) {
                g.drawImage(hightlightImg, posX, posY, size, size, null);
            }
        }
    }
}
