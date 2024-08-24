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
import org.jxmapviewer.painter.AbstractPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class PolylinePainter extends AbstractPainter<JXMapViewer> {
    private List<GeoPosition> waypoints;
    private Color lineColor = Color.GREEN;
    private int lineWidth = 3;

    public void setWaypoints(List<GeoPosition> waypoints) {
        this.waypoints = waypoints;
        firePropertyChange("waypoints", null, waypoints);
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
        firePropertyChange("lineColor", null, color);
    }

    public void setLineWidth(int width) {
        this.lineWidth = width;
        firePropertyChange("lineWidth", null, width);
    }

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        if (waypoints == null || waypoints.size() < 2) {
            return;
        }

        // Set the color and stroke for the line
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineWidth));

        // Convert GeoPosition to pixel coordinates
        Point2D prevPoint = map.getTileFactory().geoToPixel(waypoints.get(0), map.getZoom());

        for (int i = 1; i < waypoints.size(); i++) {
            Point2D currentPoint = map.getTileFactory().geoToPixel(waypoints.get(i), map.getZoom());
            g.drawLine((int) prevPoint.getX(), (int) prevPoint.getY(), (int) currentPoint.getX(), (int) currentPoint.getY());
            prevPoint = currentPoint;
        }
    }
}
