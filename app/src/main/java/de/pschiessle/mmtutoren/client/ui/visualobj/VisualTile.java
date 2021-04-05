package de.pschiessle.mmtutoren.client.ui.visualobj;

import java.awt.image.BufferedImage;
import java.util.List;

public class VisualTile {
    private TileType tileType;
    private List<VisualEntity> entities;

    private boolean isHoverable = false;
    private boolean isHovered = false;

    public VisualTile(TileType tileType, List<VisualEntity> entities) {
        this.tileType = tileType;
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

}
