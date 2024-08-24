/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

/**
 *
 * @author Bryger
 */
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.painter.AbstractPainter;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class CustomPainter extends AbstractPainter<JXMapViewer> {
    private final AbstractPainter<JXMapViewer> waypointPainter;
    private final AbstractPainter<JXMapViewer> polylinePainter;

    public CustomPainter(AbstractPainter<JXMapViewer> waypointPainter, AbstractPainter<JXMapViewer> polylinePainter) {
        this.waypointPainter = waypointPainter;
        this.polylinePainter = polylinePainter;
    }

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        waypointPainter.paint(g, map, width, height);
        polylinePainter.paint(g, map, width, height);
    }
}
