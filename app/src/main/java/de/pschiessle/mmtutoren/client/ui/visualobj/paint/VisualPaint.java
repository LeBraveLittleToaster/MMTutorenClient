package de.pschiessle.mmtutoren.client.ui.visualobj.paint;

import de.pschiessle.mmtutoren.client.ui.visualobj.VisualCamera;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;

public abstract class VisualPaint {

    public void paint(Graphics g, VisualCamera camera){
        System.out.println("PAINT NOT IMPLEMENTED!");
    }
}
