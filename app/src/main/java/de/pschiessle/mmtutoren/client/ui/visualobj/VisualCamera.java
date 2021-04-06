package de.pschiessle.mmtutoren.client.ui.visualobj;

import javax.swing.*;
import java.util.jar.JarEntry;

public class VisualCamera {
    private float zoom;
    private int offsetX;
    private int offsetY;

    public VisualCamera(float zoom, int offsetX, int offsetY) {
        this.zoom = zoom;

        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void addOffsetX(int offsetX) {
        this.offsetX += offsetX;
    }
    public void addOffsetY(int offsetY) {
        this.offsetY += offsetY;
    }
}
