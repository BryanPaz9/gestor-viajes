/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.swing.JOptionPane;
import controller.GestionController;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.text.SimpleDateFormat;
/**
 *
 * @author bryge
 */
public class GestionViajesApp extends javax.swing.JFrame {
    GestionController opciones = new GestionController();
    private DefaultTableModel dtm;
    private Object[] datos = new Object[6];
    private int filaSelec;
    private TableRowSorter trsfiltro;
    String filtro;
    
    /**
     * Creates new form GestionViajesApp
     */
    public GestionViajesApp() {
        initComponents();
        dtm = (DefaultTableModel)tblViajes.getModel();   
        /*setResizable(false);
        
        // Opcional: puedes establecer un tamaño fijo utilizando setSize
        setSize(1200, 800);*/
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // Método para inicializar el estado de los botones
    private void inicializarBotones() {
    agregar.setEnabled(true);
    eliminar.setEnabled(true);
    editar.setEnabled(true);
    modificar.setEnabled(false);
    limpiar.setEnabled(true);
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblViajes = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        filtrar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        origen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblOrigen = new javax.swing.JLabel();
        destino = new javax.swing.JTextField();
        lblDestino = new javax.swing.JLabel();
        lblFecIni = new javax.swing.JLabel();
        fecFin = new javax.swing.JTextField();
        lblFecFin = new javax.swing.JLabel();
        fecIni = new javax.swing.JTextField();
        ubiOrigen = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        ubiDestino = new javax.swing.JButton();
        lblOrigen1 = new javax.swing.JLabel();
        latitudOrigen = new javax.swing.JTextField();
        latitudDestino = new javax.swing.JTextField();
        lblOrigen2 = new javax.swing.JLabel();
        lblOrigen3 = new javax.swing.JLabel();
        lblOrigen4 = new javax.swing.JLabel();
        longitudOrigen = new javax.swing.JTextField();
        lblOrigen5 = new javax.swing.JLabel();
        longitudDestino = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        editar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        abreDirectorio = new javax.swing.JMenuItem();
        exportar = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tblViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Origen", "Destino", "Fecha de Inicio", "Fecha de Fin", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblViajes);
        if (tblViajes.getColumnModel().getColumnCount() > 0) {
            tblViajes.getColumnModel().getColumn(0).setResizable(false);
            tblViajes.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblViajes.getColumnModel().getColumn(1).setResizable(false);
            tblViajes.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblViajes.getColumnModel().getColumn(4).setResizable(false);
            tblViajes.getColumnModel().getColumn(4).setPreferredWidth(15);
            tblViajes.getColumnModel().getColumn(5).setResizable(false);
            tblViajes.getColumnModel().getColumn(5).setPreferredWidth(35);
        }

        eliminar.setText("Eliminar");
        eliminar.setActionCommand("AgregarViaje");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        filtrar.setText("Filtrar");
        filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jEditorPane1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión de viajes");

        origen.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                origenActionPerformed(evt);
            }
        });
        origen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                origenKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mapa");

        lblOrigen.setText("Origen");

        destino.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinoActionPerformed(evt);
            }
        });

        lblDestino.setText("Destino");

        lblFecIni.setText("Fecha de inicio");

        fecFin.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        fecFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecFinActionPerformed(evt);
            }
        });

        lblFecFin.setText("Fecha de Fin");

        fecIni.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        fecIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecIniActionPerformed(evt);
            }
        });

        ubiOrigen.setText("Seleccionar Ubicación de Origen");
        ubiOrigen.setActionCommand("AgregarViaje");
        ubiOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubiOrigenActionPerformed(evt);
            }
        });

        agregar.setText("Agregar");
        agregar.setActionCommand("AgregarViaje");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        ubiDestino.setText("Seleccionar Ubicación de Destino");
        ubiDestino.setActionCommand("AgregarViaje");
        ubiDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubiDestinoActionPerformed(evt);
            }
        });

        lblOrigen1.setText("Latitud Origen");

        latitudOrigen.setEditable(false);
        latitudOrigen.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        latitudOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latitudOrigenActionPerformed(evt);
            }
        });

        latitudDestino.setEditable(false);
        latitudDestino.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        latitudDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latitudDestinoActionPerformed(evt);
            }
        });

        lblOrigen2.setText("Latitud Destino");

        lblOrigen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrigen3.setText("Coordenadas de las ubicaciones");

        lblOrigen4.setText("Longitud Origen");

        longitudOrigen.setEditable(false);
        longitudOrigen.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        longitudOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longitudOrigenActionPerformed(evt);
            }
        });

        lblOrigen5.setText("Longitud Destino");

        longitudDestino.setEditable(false);
        longitudDestino.setToolTipText("Ingrese aquí el nombre de la ubicación de origen.");
        longitudDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longitudDestinoActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        jMenu2.setText("Opciones");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Documentación");
        jMenu2.add(jMenuItem2);

        abreDirectorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        abreDirectorio.setText("Abrir directorio de CSV");
        abreDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreDirectorioActionPerformed(evt);
            }
        });
        jMenu2.add(abreDirectorio);

        exportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exportar.setText("Exportar en CSV");
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        jMenu2.add(exportar);

        salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu2.add(salir);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblOrigen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fecIni)
                                    .addComponent(lblFecIni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fecFin)
                                    .addComponent(lblFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(longitudOrigen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigen4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(latitudOrigen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ubiOrigen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(origen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrigen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(destino)
                                    .addComponent(lblDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(ubiDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(latitudDestino)
                                    .addComponent(lblOrigen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(longitudDestino)
                                    .addComponent(lblOrigen5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubiOrigen)
                            .addComponent(ubiDestino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(lblOrigen3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(latitudOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(latitudDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(longitudOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOrigen5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(longitudDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblFecIni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(fecIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblFecFin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fecFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eliminar)
                            .addComponent(filtrar)
                            .addComponent(limpiar)
                            .addComponent(agregar)
                            .addComponent(editar)
                            .addComponent(modificar))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane2))))
                .addGap(56, 56, 56))
        );

        destino.getAccessibleContext().setAccessibleDescription("Ingrese aquí el nombre de la ubicación de destino.");
        fecIni.getAccessibleContext().setAccessibleDescription("Seleccione la fecha de fin");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
   
        if (tblViajes.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro de la tabla", "ERROR AL ELIMINAR REGISTRO", JOptionPane.ERROR_MESSAGE);
            
        }else{
          dtm.removeRow(tblViajes.getSelectedRow());  
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarActionPerformed
        
                String cadena = origen.getText();
                origen.setText(cadena);
                repaint();
                filtro();
                tblViajes.setRowSorter(trsfiltro);
            
            
        
    }//GEN-LAST:event_filtrarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        LimpiarCampos();
        inicializarBotones();
    }//GEN-LAST:event_limpiarActionPerformed

    private void origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_origenActionPerformed
         destino.requestFocus();
    }//GEN-LAST:event_origenActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro que desea salir de la aplicación?", 
            "Saliendo...", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            // Si se selecciona "Sí", termina la aplicación
            System.exit(0);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinoActionPerformed
        
    }//GEN-LAST:event_destinoActionPerformed

    private void fecFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecFinActionPerformed

    private void fecIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecIniActionPerformed
        fecFin.requestFocus();
    }//GEN-LAST:event_fecIniActionPerformed

    private void ubiOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubiOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubiOrigenActionPerformed

    private int id = 1;
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        
         if (!camposvacios()){      
             
        datos [0] = id ++;
        datos [1] = origen.getText().trim();
        datos [2] = destino.getText().trim();
        datos [3] = fecIni.getText().trim();
        datos [4] = fecFin.getText().trim();   
        datos [5] = "Registrado";
        dtm.addRow(datos);
        LimpiarCampos();
         }
         
    }//GEN-LAST:event_agregarActionPerformed
    
    private void LimpiarCampos(){
       
       
       origen.setText("");
       destino.setText("");
       fecIni.setText("");
       fecFin.setText("");
       trsfiltro.setRowFilter(null);
       origen.requestFocus();
       trsfiltro.setRowFilter(null);
       
        
    }
    
    private boolean camposvacios(){
        
        if (origen.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El Campo Origen esta vacio", "Verificar Campos", JOptionPane.ERROR_MESSAGE);
            origen.requestFocus();
            return true;
        }else if (destino.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El Campo Destino esta vacio", "Verificar Campos", JOptionPane.ERROR_MESSAGE);
            destino.requestFocus();
            return true;
                }
        
        
        return false;
    }
    
    
    
    
                  
    private void ubiDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubiDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubiDestinoActionPerformed

    private void latitudOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latitudOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_latitudOrigenActionPerformed

    private void latitudDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latitudDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_latitudDestinoActionPerformed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed
        // Obtener la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();
        // Crear un DateTimeFormatter con el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        // Formatear la fecha
        String fechaexport = fechaActual.format(formato);
        opciones.exportarViajesACSV("viajes-"+fechaexport+".csv");
    }//GEN-LAST:event_exportarActionPerformed

    private void longitudOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longitudOrigenActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_longitudOrigenActionPerformed

    private void longitudDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longitudDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_longitudDestinoActionPerformed

    private void abreDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreDirectorioActionPerformed
        opciones.abreDirectorio();
    }//GEN-LAST:event_abreDirectorioActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
   
        if (tblViajes.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro de la tabla", "ERROR AL MODIFICAR REGISTRO", JOptionPane.WARNING_MESSAGE);
            
        }else{
         filaSelec = tblViajes.getSelectedRow();
        
        origen.setText(tblViajes.getValueAt(filaSelec, 1).toString());
        destino.setText(tblViajes.getValueAt(filaSelec, 2).toString());
        fecIni.setText(tblViajes.getValueAt(filaSelec, 3).toString());
        fecFin.setText(tblViajes.getValueAt(filaSelec, 4).toString());
        
         // Deshabilita los botones Agregar y Eliminar
        agregar.setEnabled(false);
        eliminar.setEnabled(false);
        
        // Habilita los botones Modificar y Limpiar
        modificar.setEnabled(true);
        limpiar.setEnabled(true);
        
        System.out.println("Fila: " + filaSelec);  
        }
         
    }//GEN-LAST:event_editarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
      
       if (!camposvacios()){      
             
       dtm.setValueAt(origen.getText().trim(), filaSelec, 1);
       dtm.setValueAt(destino.getText().trim(), filaSelec, 2);
       dtm.setValueAt(fecIni.getText().trim(), filaSelec, 3);
       dtm.setValueAt(fecFin.getText().trim(), filaSelec, 4);
       //agregar un ws 
        
        
        LimpiarCampos();
        inicializarBotones();
         } 
       
    }//GEN-LAST:event_modificarActionPerformed

    private void origenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_origenKeyTyped
        trsfiltro = new TableRowSorter(tblViajes.getModel());
        tblViajes.setRowSorter(trsfiltro);
    }//GEN-LAST:event_origenKeyTyped
  
    public void filtro(){
        filtro = origen.getText();
        trsfiltro.setRowFilter(RowFilter.regexFilter(origen.getText(), 1));
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
            java.util.logging.Logger.getLogger(GestionViajesApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionViajesApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionViajesApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionViajesApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionViajesApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abreDirectorio;
    private javax.swing.JButton agregar;
    private javax.swing.JTextField destino;
    private javax.swing.JButton editar;
    private javax.swing.JButton eliminar;
    private javax.swing.JMenuItem exportar;
    private javax.swing.JTextField fecFin;
    private javax.swing.JTextField fecIni;
    private javax.swing.JButton filtrar;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField latitudDestino;
    private javax.swing.JTextField latitudOrigen;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblFecFin;
    private javax.swing.JLabel lblFecIni;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblOrigen1;
    private javax.swing.JLabel lblOrigen2;
    private javax.swing.JLabel lblOrigen3;
    private javax.swing.JLabel lblOrigen4;
    private javax.swing.JLabel lblOrigen5;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField longitudDestino;
    private javax.swing.JTextField longitudOrigen;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField origen;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTable tblViajes;
    private javax.swing.JButton ubiDestino;
    private javax.swing.JButton ubiOrigen;
    // End of variables declaration//GEN-END:variables
}
