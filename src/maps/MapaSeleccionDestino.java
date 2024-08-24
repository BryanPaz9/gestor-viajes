/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bryger
 */
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import org.jxmapviewer.viewer.DefaultTileFactory;
import view.GestionViajesApp;

/**
 * Ventana para seleccionar la ubicación de destino en el mapa.
 */
public class MapaSeleccionDestino extends JFrame {
    private JXMapViewer mapViewer;
    private GestionViajesApp mainApp;
    private GeoPosition geoPositionSeleccionada; // GeoPosition seleccionada
    private Set<Waypoint> waypointsDestino;
    private WaypointPainter<Waypoint> waypointPainterDestino;

    public MapaSeleccionDestino(GestionViajesApp mainApp) {
        this.mainApp = mainApp;
        setTitle("Seleccionar Ubicación de Destino");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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
        waypointsDestino = new HashSet<>();
        waypointPainterDestino = new WaypointPainter<>();
        waypointPainterDestino.setWaypoints(waypointsDestino);
        waypointPainterDestino.setRenderer(new ColoredWaypointRenderer(Color.BLUE)); // Color azul para destino
        mapViewer.setOverlayPainter(waypointPainterDestino);

        add(mapViewer, BorderLayout.CENTER);

        // Botones de acción
        JButton btnGrabar = new JButton("Grabar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGrabar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción para el botón "Grabar"
        btnGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (geoPositionSeleccionada != null) {
                    mainApp.setUbicacion(false, geoPositionSeleccionada);
                }
                dispose(); // Cierra la ventana después de seleccionar la ubicación
            }
        });

        // Acción para el botón "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Solo cierra la ventana sin hacer cambios
            }
        });

        // Configurar evento de clic en el mapa
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                GeoPosition geoPosition = mapViewer.convertPointToGeoPosition(e.getPoint());
                geoPositionSeleccionada = geoPosition;

                // Actualizar el waypoint y redibujar el mapa
                waypointsDestino.clear();
                waypointsDestino.add(new DefaultWaypoint(geoPosition));
                waypointPainterDestino.setWaypoints(waypointsDestino);
                mapViewer.repaint();
            }
        });
    }
}
