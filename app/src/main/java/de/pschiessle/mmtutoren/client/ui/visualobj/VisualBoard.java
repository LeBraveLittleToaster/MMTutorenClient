package de.pschiessle.mmtutoren.client.ui.visualobj;

import java.util.List;
import java.util.Optional;

public class VisualBoard {
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
        if(iX >= 0 && iX < tiles.length && iY >= 0 && iY < tiles[iX].length){
            return Optional.of(tiles[iX][iY]);
        }
        return Optional.empty();
    }
}
