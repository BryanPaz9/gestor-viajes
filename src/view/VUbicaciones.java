/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import com.toedter.calendar.JDateChooser;
import controller.GestionController;
import db.Conexion;
import static db.Conexion.getConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import maps.ColoredWaypointRenderer;
import maps.MapaSeleccionDestino;
import maps.MapaSeleccionOrigen;
import maps.ColoredWaypointRenderer;
import maps.CustomPainter;
import maps.PolylinePainter;
import maps.CompositePainter;
import maps.MapViaje;
import model.Viaje;
import view.GestionViajesApp;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.painter.AbstractPainter;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
/**
 *
 * @author bryge
 */
public class VUbicaciones extends javax.swing.JFrame {
    GestionController opciones = new GestionController();
    private DefaultTableModel dtm;
    private TableRowSorter trsfiltro;
    String filtro;
    
    private String codigo;
    private String nombre;
    private Double latitud;
    private Double longitud;
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    
    /**
     * mapa
     */
    private JXMapViewer mapViewer;
    private Set<Waypoint> waypointsOrigen;
    private Set<Waypoint> waypointsDestino;
    private WaypointPainter<Waypoint> waypointPainterOrigen;
    private WaypointPainter<Waypoint> waypointPainterDestino;
    private PolylinePainter polylinePainter;
    
    
    
    /**
     * Creates new form GestionViajesApp
     */
    public VUbicaciones() {
        initComponents();
        refreshTable();    
        setLocationRelativeTo(null);
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUbicaciones = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        ubicacion = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        lblOrigen1 = new javax.swing.JLabel();
        lat = new javax.swing.JTextField();
        lblOrigen4 = new javax.swing.JLabel();
        lon = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        regresarAdminUbi = new javax.swing.JButton();
        lblOrigen6 = new javax.swing.JLabel();
        lblOrigen2 = new javax.swing.JLabel();
        code = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tblUbicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUbicacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUbicaciones);

        eliminar.setText("Eliminar");
        eliminar.setActionCommand("AgregarViaje");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ubicaciones");

        name.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });

        lblNombre.setText("Nombre");

        ubicacion.setText("Abrir Mapa");
        ubicacion.setActionCommand("AgregarViaje");
        ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicacionActionPerformed(evt);
            }
        });

        agregar.setText("Agregar");
        agregar.setActionCommand("AgregarViaje");
        agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarMouseClicked(evt);
            }
        });
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        lblOrigen1.setText("Latitud");

        lat.setEditable(false);
        lat.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        lat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latActionPerformed(evt);
            }
        });

        lblOrigen4.setText("Longitud");

        lon.setEditable(false);
        lon.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        lon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lonActionPerformed(evt);
            }
        });

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        regresarAdminUbi.setText("Regresar");
        regresarAdminUbi.setActionCommand("Regresar");
        regresarAdminUbi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regresarAdminUbiMouseClicked(evt);
            }
        });
        regresarAdminUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarAdminUbiActionPerformed(evt);
            }
        });

        lblOrigen6.setText("Coordenadas");

        lblOrigen2.setText("Código");

        code.setEditable(false);
        code.setToolTipText("");
        code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(regresarAdminUbi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 40, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name))
                                .addGap(48, 48, 48))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(lblOrigen6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigen4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigen1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(code)
                            .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regresarAdminUbi)
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblOrigen1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOrigen4)
                            .addComponent(lblOrigen6)
                            .addComponent(lblOrigen2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ubicacion)
                            .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eliminar)
                        .addComponent(agregar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limpiar)
                        .addComponent(editar)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        codigo=code.getText();
        eliminarcliente(codigo);
        refreshTable();
        LimpiarCampos();

    }//GEN-LAST:event_eliminarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed

    }//GEN-LAST:event_nameActionPerformed

    private void ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacionActionPerformed
        // TODO add your handling code here:
        MapaSeleccionOrigen mapaSeleccionOrigen = new MapaSeleccionOrigen(VUbicaciones.this);
        mapaSeleccionOrigen.setVisible(true);
    }//GEN-LAST:event_ubicacionActionPerformed

    
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
           
        txtFields();
        insertUbicacion(nombre,latitud,longitud);
        LimpiarCampos();
        refreshTable();
         
    }//GEN-LAST:event_agregarActionPerformed
    
public void refreshTable() {
    String[] columnNames = {"CÓDIGO", "NOMBRE", "LATITUD", "LONGITUD"};
    DefaultTableModel dtm = new DefaultTableModel(columnNames, 0); // Reiniciar el modelo de la tabla
    tblUbicaciones.setModel(dtm); // Asignar el modelo a la tabla
    
    try (Connection conn = getConnection()) {
        String query = "SELECT CODIGO_UBICACION, NOMBRE, LATITUD, LONGITUD FROM UBICACIONES";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // Asegurarse de que no haya valores nulos antes de añadir a la tabla
            String cod = rs.getString("CODIGO_UBICACION") != null ? rs.getString("CODIGO_UBICACION") : "";
            String n = rs.getString("NOMBRE") != null ? rs.getString("NOMBRE") : "";
            String la = rs.getString("LATITUD") != null ? rs.getString("LATITUD") : "";
            String lo = rs.getString("LONGITUD") != null ? rs.getString("LONGITUD") : "";

            // Agregar la fila al modelo de la tabla
            dtm.addRow(new Object[]{cod, n, la, lo});
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    private void insertUbicacion(String nombre, Double latitud, Double longitud){
        String sql = "INSERT INTO UBICACIONES(NOMBRE,LATITUD,LONGITUD) VALUES(?,?,?)";
         try (Connection conn =Conexion.getConnection() ;
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, latitud);
            stmt.setDouble(3, longitud);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ubicación insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    private void actualizarUbicacion(String cod, String nombre, Double lat, Double lon) {
        String sql = "UPDATE UBICACIONES SET NOMBRE = ?, LATITUD = ?, LONGITUD = ? WHERE CODIGO_UBICACION = ?";
        try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                   
                       
            // Asignar los valores a los parámetros
            stmt.setString(1, nombre);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, lon);
            stmt.setString(4, cod); // Este es el identificador para saber qué cliente actualizar
            
            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Ubicación actualizada satisfactoriamente!","Ubicación actualizada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró una ubicación con el CODIGO proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
       private void eliminarcliente(String cod){
        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de que desea eliminar este registro?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        if(confirmacion==JOptionPane.YES_OPTION){
        String sql = "DELETE FROM UBICACIONES WHERE CODIGO_UBICACION = ?";
        try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Asignar el valor del NIT al parámetro de la consulta
        stmt.setString(1, cod);

        // Ejecutar la eliminación
        int filasEliminadas = stmt.executeUpdate();

        if (filasEliminadas > 0) {
             JOptionPane.showMessageDialog(this, "Ubicación eliminada correctamente.");
        } else {
            System.out.println("No se encontró una ubicación con el código proporcionado.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        }else{
            JOptionPane.showMessageDialog(this, "Eliminacion cancelada por el usuario");
        }
       
   }
    
    private void txtFields(){
        codigo = code.getText();
        nombre = name.getText();
        latitud = Double.valueOf(lat.getText());
        longitud = Double.valueOf(lat.getText());
        
        System.out.println("c "+codigo+" n "+nombre+" la "+latitud+" lo "+longitud);
    }
    private void LimpiarCampos() {
        code.setText("");
        name.setText("");
        lat.setText("");
        lon.setText("");
    }

    
    public void setUbicacion(boolean esOrigen, GeoPosition geoPosition) {
        if (esOrigen) {
            lat.setText(String.valueOf(geoPosition.getLatitude()));
            lon.setText(String.valueOf(geoPosition.getLongitude()));
        } else {
        }
    }
           
    private void latActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_latActionPerformed

    private void lonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_lonActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        
        if (tblUbicaciones.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro de la tabla", "ERROR AL MODIFICAR REGISTRO", JOptionPane.WARNING_MESSAGE);
            
        }else{
            txtFields();
            actualizarUbicacion(codigo,nombre,latitud,longitud);
            refreshTable();
            LimpiarCampos();
        }
         
    }//GEN-LAST:event_editarActionPerformed

    private void nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyTyped

    }//GEN-LAST:event_nameKeyTyped


    private void tblUbicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUbicacionesMouseClicked

    int rowIndex = tblUbicaciones.rowAtPoint(evt.getPoint());

    // Validar índice de fila
    if (rowIndex < 0) {
        return; // Salir si la fila seleccionada no es válida
    }

    // Obtener los valores de la fila
    String cod = String.valueOf(tblUbicaciones.getValueAt(rowIndex, 0));
    String nombre = String.valueOf(tblUbicaciones.getValueAt(rowIndex, 1));
    String latitudO = String.valueOf(tblUbicaciones.getValueAt(rowIndex, 2));
    String longitudO = String.valueOf(tblUbicaciones.getValueAt(rowIndex, 3));

    // Actualizar campos de texto
    code.setText(cod);
    name.setText(nombre);
    lat.setText(latitudO);
    lon.setText(longitudO);

    }//GEN-LAST:event_tblUbicacionesMouseClicked

    
    
    
    
    
    private void agregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_agregarMouseClicked

    private void regresarAdminUbiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regresarAdminUbiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_regresarAdminUbiMouseClicked

    private void regresarAdminUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarAdminUbiActionPerformed
        // TODO add your handling code here:
                        // TODO add your handling code here:
        GestionViajesApp admin = new GestionViajesApp();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_regresarAdminUbiActionPerformed

    private void codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeActionPerformed
  

    private void updateMap(GeoPosition origen, GeoPosition destino) {
    // Limpiar y actualizar waypoints
    waypointsOrigen.clear();
    waypointsDestino.clear();
    waypointsOrigen.add(new DefaultWaypoint(origen));
    waypointsDestino.add(new DefaultWaypoint(destino));

    // Configurar pintores
    ColoredWaypointRenderer origenRenderer = new ColoredWaypointRenderer(Color.RED);
    ColoredWaypointRenderer destinoRenderer = new ColoredWaypointRenderer(Color.BLUE);
    waypointPainterOrigen.setWaypoints(waypointsOrigen);
    waypointPainterOrigen.setRenderer(origenRenderer);
    waypointPainterDestino.setWaypoints(waypointsDestino);
    waypointPainterDestino.setRenderer(destinoRenderer);

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

    // Calcular y ajustar el punto medio y el zoom
    double midLatitude = (origen.getLatitude() + destino.getLatitude()) / 2;
    double midLongitude = (origen.getLongitude() + destino.getLongitude()) / 2;
    GeoPosition midpoint = new GeoPosition(midLatitude, midLongitude);
    System.out.println("Mid"+midpoint);
    mapViewer.setAddressLocation(midpoint);

    double maxLat = Math.max(origen.getLatitude(), destino.getLatitude());
    double minLat = Math.min(origen.getLatitude(), destino.getLatitude());
    double maxLon = Math.max(origen.getLongitude(), destino.getLongitude());
    double minLon = Math.min(origen.getLongitude(), destino.getLongitude());

    double latDiff = maxLat - minLat;
    double lonDiff = maxLon - minLon;
    int zoom = calcularZoom(latDiff, lonDiff);
    mapViewer.setZoom(zoom);

    mapViewer.repaint();
}

    
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
    

    
    /*
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VUbicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VUbicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VUbicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VUbicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VUbicaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JTextField code;
    private javax.swing.JButton editar;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lat;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblOrigen1;
    private javax.swing.JLabel lblOrigen2;
    private javax.swing.JLabel lblOrigen4;
    private javax.swing.JLabel lblOrigen6;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField lon;
    private javax.swing.JTextField name;
    private javax.swing.JButton regresarAdminUbi;
    private javax.swing.JTable tblUbicaciones;
    private javax.swing.JButton ubicacion;
    // End of variables declaration//GEN-END:variables
}
