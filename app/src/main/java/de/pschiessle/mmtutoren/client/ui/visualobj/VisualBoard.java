package de.pschiessle.mmtutoren.client.ui.visualobj;

import de.pschiessle.mmtutoren.client.ui.visualobj.paint.VisualPaint;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static de.pschiessle.mmtutoren.client.ui.Board.TILE_SOURCE_SIZE;

public class VisualBoard extends VisualPaint {
    private VisualTile[][] tiles;
    private List<VisualEntity> entities;

    public VisualBoard(VisualTile[][] tiles, List<VisualEntity> entities) {
        this.tiles = tiles;
        this.entities = entities;
    }

    public void setTiles(VisualTile[][] tiles) {
        this.tiles = tiles;
    }

    public List<VisualEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<VisualEntity> entities) {
        this.entities = entities;
    }

    public VisualTile[][] getTiles() {
        return tiles;
    }

    public Optional<VisualTile> getTile(int iX, int iY) {
        if (iX >= 0 && iX < tiles.length && iY >= 0 && iY < tiles[iX].length) {
            return Optional.of(tiles[iX][iY]);
        }
        return Optional.empty();
    }

    @Override
    public void paint(Graphics g, VisualCamera camera) {
        Random ran = new Random();
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].paint(g, camera);
            }
        }
    }
}
