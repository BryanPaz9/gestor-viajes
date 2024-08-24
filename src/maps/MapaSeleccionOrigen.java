package maps;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.input.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import org.jxmapviewer.viewer.DefaultTileFactory;
import view.GestionViajesApp;



/**
 * Ventana para seleccionar la ubicación de origen en el mapa.
 */
public class MapaSeleccionOrigen extends JFrame {
    private JXMapViewer mapViewer;
    private GestionViajesApp mainApp;
    private GeoPosition geoPositionSeleccionada; // GeoPosition seleccionada
    private Set<Waypoint> waypointsOrigen;
    private WaypointPainter<Waypoint> waypointPainterOrigen;

    public MapaSeleccionOrigen(GestionViajesApp mainApp) {
        this.mainApp = mainApp;
        setTitle("Seleccionar Ubicación de Origen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        System.out.println("MapaSeleccionOrigen window is being displayed."); // Confirmación de creación

        // Configuración del mapa
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
        mapViewer.setZoom(12);
        mapViewer.setAddressLocation(new GeoPosition(14.641804550935518, -90.51326751708984)); // Posición inicial

        // Añadir controladores de mouse y navegación
        PanMouseInputListener panMouseInputListener = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(panMouseInputListener);
        mapViewer.addMouseMotionListener(panMouseInputListener);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Configuración de waypoints para el puntero
        waypointsOrigen = new HashSet<>();
        waypointPainterOrigen = new WaypointPainter<>();
        waypointPainterOrigen.setWaypoints(waypointsOrigen);
        waypointPainterOrigen.setRenderer(new ColoredWaypointRenderer(Color.RED)); // Color rojo para origen
        mapViewer.setOverlayPainter(waypointPainterOrigen);

        add(mapViewer, BorderLayout.CENTER);

        // Botones de acción
        JButton btnGrabar = new JButton("Grabar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGrabar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción para el botón "Grabar"
        btnGrabar.addActionListener(e -> {
            if (geoPositionSeleccionada != null) {
                mainApp.setUbicacion(true, geoPositionSeleccionada);
            }
            dispose(); // Cierra la ventana después de seleccionar la ubicación
        });

        // Acción para el botón "Cancelar"
        btnCancelar.addActionListener(e -> dispose()); // Solo cierra la ventana sin hacer cambios

        // Configurar evento de clic en el mapa
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked!"); // Verificación del evento de clic
                GeoPosition geoPosition = mapViewer.convertPointToGeoPosition(e.getPoint());
                geoPositionSeleccionada = geoPosition;

                System.out.println("Clicked at: " + geoPosition); // Verificación de coordenadas

                // Actualizar el waypoint y redibujar el mapa
                waypointsOrigen.clear();
                waypointsOrigen.add(new DefaultWaypoint(geoPosition));
                waypointPainterOrigen.setWaypoints(waypointsOrigen);
                mapViewer.repaint();

                System.out.println("Waypoint added: " + geoPosition); // Verificación de waypoints
            }
        });
    }
}
