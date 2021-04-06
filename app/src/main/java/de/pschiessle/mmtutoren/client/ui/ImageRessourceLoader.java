package de.pschiessle.mmtutoren.client.ui;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ImageRessourceLoader {

    private static ImageRessourceLoader INSTANCE = null;
    private List<BufferedImage> grassImgs;
    private BufferedImage rockImg;
    private BufferedImage hightlightImg;

    private boolean isLoaded = false;

    public static ImageRessourceLoader getInstance() {
        if (INSTANCE == null) INSTANCE = new ImageRessourceLoader();
        return INSTANCE;
    }

    private ImageRessourceLoader() {

    }

    public void reloadImages(){
        isLoaded = false;
        loadImages();
    }

    public void loadImages() {
        if (!isLoaded) {
            try {
                grassImgs = List.of(
                        ImageIO.read(getClass().getResource("/images/sprite_floor_grass_0.png")),
                        ImageIO.read(getClass().getResource("/images/sprite_floor_grass_1.png")),
                        ImageIO.read(getClass().getResource("/images/sprite_floor_grass_2.png"))
                );
                rockImg = ImageIO.read(getClass().getResource("/images/sprite_floor_rock.png"));
                hightlightImg = ImageIO.read(getClass().getResource("/images/sprite_highlight_0.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            isLoaded = true;
        }
    }

    public List<BufferedImage> getGrassImgs() {
        return grassImgs;
    }

    public BufferedImage getRockImg() {
        return rockImg;
    }

    public BufferedImage getHightlightImg() {
        return hightlightImg;
    }
}
