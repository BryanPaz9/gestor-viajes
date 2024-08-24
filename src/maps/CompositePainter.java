/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

/**
 *
 * @author Bryger
 */
import org.jxmapviewer.painter.AbstractPainter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;
import java.util.List;

public class CompositePainter<T extends JXMapViewer> extends AbstractPainter<T> {
    private final List<AbstractPainter<T>> painters;

    public CompositePainter(List<AbstractPainter<T>> painters) {
        this.painters = painters;
    }

    @Override
    protected void doPaint(Graphics2D g, T map, int width, int height) {
        for (AbstractPainter<T> painter : painters) {
            painter.paint(g, map, width, height);
        }
    }
}
