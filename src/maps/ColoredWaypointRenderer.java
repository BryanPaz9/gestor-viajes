package maps;

import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointRenderer;
import org.jxmapviewer.viewer.WaypointPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import org.jxmapviewer.JXMapViewer;

/**
 * Renderer for waypoints that allows specifying a color for the waypoint.
 */
public class ColoredWaypointRenderer implements WaypointRenderer<Waypoint> {
    private final Color color;

    public ColoredWaypointRenderer(Color color) {
        this.color = color;
    }

    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer mapViewer, Waypoint waypoint) {
        // Convert waypoint position to screen coordinates
        Point2D pt = mapViewer.getTileFactory().geoToPixel(waypoint.getPosition(), mapViewer.getZoom());

        // Convert Point2D to Point for drawing
        Point point = new Point((int) pt.getX(), (int) pt.getY());

        // Draw the waypoint
        g.setColor(color);
        g.fillOval(point.x - 5, point.y - 5, 10, 10); // Draw a circle with radius 5
    }
}
