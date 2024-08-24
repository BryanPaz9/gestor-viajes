package maps;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.GeoPosition;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import java.util.List;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;

public class MapViaje extends JFrame {
    private JXMapViewer mapViewer;
    private Set<Waypoint> waypointsOrigen;
    private Set<Waypoint> waypointsDestino;
    private WaypointPainter<Waypoint> waypointPainterOrigen;
    private WaypointPainter<Waypoint> waypointPainterDestino;
    private PolylinePainter polylinePainter;

    public MapViaje(GeoPosition origen, GeoPosition destino, int zoomLevel) {
        setTitle("Mapa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configurar el mapa
        TileFactoryInfo info = new TileFactoryInfo(1, 17, 17,
                256, true, true,
                "http://tile.openstreetmap.org/",
                "x", "y", "z") {
            public String getTileUrl(int x, int y, int zoom) {
                int z = 17 - zoom;
                return this.baseURL + z + "/" + x + "/" + y + ".png";
            }
        };
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        mapViewer.setZoom(zoomLevel);

        // Inicializar waypoints y painters
        waypointsOrigen = new HashSet<>();
        waypointsDestino = new HashSet<>();

        waypointPainterOrigen = new WaypointPainter<>();
        waypointPainterDestino = new WaypointPainter<>();

        waypointPainterOrigen.setWaypoints(waypointsOrigen);
        waypointPainterDestino.setWaypoints(waypointsDestino);

        ColoredWaypointRenderer origenRenderer = new ColoredWaypointRenderer(Color.RED);
        ColoredWaypointRenderer destinoRenderer = new ColoredWaypointRenderer(Color.BLUE);

        waypointPainterOrigen.setRenderer(origenRenderer);
        waypointPainterDestino.setRenderer(destinoRenderer);

        polylinePainter = new PolylinePainter();

        // Crear y configurar el CustomPainter
        CustomPainter customPainter = new CustomPainter(waypointPainterOrigen, polylinePainter);
        mapViewer.setOverlayPainter(customPainter);

        // Añadir el JXMapViewer al JFrame
        add(mapViewer, BorderLayout.CENTER);

        // Actualizar el mapa con las posiciones iniciales
        updateMap(origen, destino);

        // Configuración del JFrame
        setLocationRelativeTo(null);
        setVisible(true);
        mapViewer.addMouseListener(new PanMouseInputListener(mapViewer));
mapViewer.addMouseMotionListener(new PanMouseInputListener(mapViewer));
mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

    }

//    private void updateMap(GeoPosition origen, GeoPosition destino) {
//        // Limpiar y actualizar waypoints
//        waypointsOrigen.clear();
//        waypointsDestino.clear();
//        waypointsOrigen.add(new DefaultWaypoint(origen));
//        waypointsDestino.add(new DefaultWaypoint(destino));
//
//        // Configurar pintores
//        waypointPainterOrigen.setWaypoints(waypointsOrigen);
//        waypointPainterDestino.setWaypoints(waypointsDestino);
//
//        // Configurar PolylinePainter
//        polylinePainter.setWaypoints(List.of(origen, destino));
//        polylinePainter.setLineColor(Color.GREEN);
//        polylinePainter.setLineWidth(3);
//
//        // Crear CompositePainter y asignar al mapa
//        CompositePainter<JXMapViewer> compositePainter = new CompositePainter<>(List.of(
//            waypointPainterOrigen,
//            waypointPainterDestino,
//            polylinePainter
//        ));
//        mapViewer.setOverlayPainter(compositePainter);
//
//        // Calcular y ajustar el punto medio y el zoom
//        double midLatitude = (origen.getLatitude() + destino.getLatitude()) / 2;
//        double midLongitude = (origen.getLongitude() + destino.getLongitude()) / 2;
//        GeoPosition midpoint = new GeoPosition(midLatitude, midLongitude);
//        mapViewer.setAddressLocation(midpoint);
//
//        double maxLat = Math.max(origen.getLatitude(), destino.getLatitude());
//        double minLat = Math.min(origen.getLatitude(), destino.getLatitude());
//        double maxLon = Math.max(origen.getLongitude(), destino.getLongitude());
//        double minLon = Math.min(origen.getLongitude(), destino.getLongitude());
//
//        double latDiff = maxLat - minLat;
//        double lonDiff = maxLon - minLon;
//        int zoom = calcularZoom(latDiff, lonDiff);
//        mapViewer.setZoom(zoom);
//
//        // Asegúrate de repintar el mapa
//        mapViewer.repaint();
//    }
//
    private int calcularZoom(double latDiff, double lonDiff) {
        // Determinar el nivel de zoom basado en la diferencia en grados
        if (latDiff > 5 || lonDiff > 5) {
            return 10; // Zoom bajo para distancias mayores
        } else if (latDiff > 2 || lonDiff > 2) {
            return 7; // Zoom medio para distancias intermedias
        } else {
            return 4; // Zoom alto para distancias menores
        }
    }
    
private void updateMap(GeoPosition origen, GeoPosition destino) {
    // Limpiar y actualizar waypoints
    waypointsOrigen.clear();
    waypointsDestino.clear();
    waypointsOrigen.add(new DefaultWaypoint(origen));
    waypointsDestino.add(new DefaultWaypoint(destino));

    // Configurar pintores
    waypointPainterOrigen.setWaypoints(waypointsOrigen);
    waypointPainterDestino.setWaypoints(waypointsDestino);

    // Configurar PolylinePainter
    polylinePainter.setWaypoints(List.of(origen, destino));
    polylinePainter.setLineColor(Color.GREEN);
    polylinePainter.setLineWidth(3);

    // Crear CompositePainter y asignar al mapa
    CompositePainter<JXMapViewer> compositePainter = new CompositePainter<>(List.of(
        waypointPainterOrigen,
        waypointPainterDestino,
        polylinePainter
    ));
    mapViewer.setOverlayPainter(compositePainter);

    // Calcular y ajustar el punto medio
    double midLatitude = (origen.getLatitude() + destino.getLatitude()) / 2;
    double midLongitude = (origen.getLongitude() + destino.getLongitude()) / 2;
    GeoPosition midpoint = new GeoPosition(midLatitude, midLongitude);
    mapViewer.setAddressLocation(midpoint);

    // Ajustar el zoom para que la línea y los puntos sean visibles
    double maxLat = Math.max(origen.getLatitude(), destino.getLatitude());
    double minLat = Math.min(origen.getLatitude(), destino.getLatitude());
    double maxLon = Math.max(origen.getLongitude(), destino.getLongitude());
    double minLon = Math.min(origen.getLongitude(), destino.getLongitude());

    double latDiff = maxLat - minLat;
    double lonDiff = maxLon - minLon;
    int zoom = calcularZoom(latDiff, lonDiff);
    mapViewer.setZoom(zoom);

    // Asegúrate de repintar el mapa
    mapViewer.repaint();
}
}

