/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import db.Conexion;
import static db.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author useri
 */
public class Usuarios extends javax.swing.JFrame {

    public Usuarios(String nombre, String apellido, String correo, String contra, String fknit, String rol, String situacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contra = contra;
        this.fknit = fknit;
        this.rol = rol;
        this.situacion = situacion;
    }
 private String nombre;
private String apellido;
private String correo;
private String contra;
private String fknit;
private String rol;
private String situacion;
private DefaultTableModel dtm;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getFknit() {
        return fknit;
    }

    public void setFknit(String fknit) {
        this.fknit = fknit;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }
    
    public void iniciartxt()
{
    nombre=jTextField1.getText();
    apellido = jTextField2.getText();
    correo = jTextField3.getText();
    contra = jTextField4.getText();
    fknit = jTextField5.getText();
    rol=(String)jComboBox1.getSelectedItem();
    situacion=(String)jComboBox2.getSelectedItem();
}
    
     private void limpiartext(){
     // Limpiar los campos después de insertar
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");   
    }
    
     
       private void CrearUsuario(String nombre1, String apellido1, String correo1, String contra1, String fknit1, String rol1, String situacion1) {
            
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena, FK_NIT, rol, situacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
         try (Connection conn =Conexion.getConnection() ;
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombre1);
        stmt.setString(2, apellido1);
        stmt.setString(3, correo1);
        stmt.setString(4, contra1);
        stmt.setString(5, fknit1);
        stmt.setString(6, rol1);
        stmt.setString(7, situacion1);  

        stmt.executeUpdate();
        System.out.println("Usuario insertado correctamente.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
      
          // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "Usuario insertado correctamente.");   

          
    }
       
       
           private void actualizardatosUsuario(String nombre1, String apellido1, String correo1, String contra1, String fknit1, String rol1, String situacion1,Integer codigo1) 
    {
         String sql = "UPDATE usuarios SET NOMBRE = ?, APELLIDO = ?, CORREO = ?, CONTRASENA = ?, FK_NIT = ?, ROL = ?, SITUACION = ? WHERE CODIGO_USUARIO = ?";
        
    try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                   
                       
            // Asignar los valores a los parámetros
            stmt.setString(1,nombre1);
            stmt.setString(2, apellido1);
            stmt.setString(3, correo1);
            stmt.setString(4, contra1);
          if (fknit1 == null || fknit1.trim().isEmpty()) {
            stmt.setNull(5, java.sql.Types.VARCHAR); // Si es nulo o vacío, se asigna NULL
        } else {
            stmt.setString(5, fknit1);  // Si no es nulo, se asigna el valor proporcionado
        }
            stmt.setString(6, rol1);
            stmt.setString(7, situacion1);
            stmt.setInt(8, codigo1); // Este es el identificador para saber qué cliente actualizar

            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
               JOptionPane.showMessageDialog(this, "Vehiculo actualizado correctamente."); 
            } else {
                JOptionPane.showMessageDialog(this, "placa no encontrada, favor de corroborar datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
      
         
    }
           
           
           
           private void eliminarRegistroUsuario(String cd)
   {
        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de que desea eliminar este registro?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        if(confirmacion==JOptionPane.YES_OPTION){
        String sql = "DELETE FROM usuarios WHERE codigo_usuario = ?";
        try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Asignar el valor del NIT al parámetro de la consulta
            System.out.println("el codigo es "+cd);
        stmt.setString(1, cd);

        // Ejecutar la eliminación
        int filasEliminadas = stmt.executeUpdate();

        if (filasEliminadas > 0) {
            System.out.println("Usuario eliminado correctamente.");
             JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro la codigo solicitada.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        }else{
            System.out.println("Eliminacion cancelada por el usuario");
            JOptionPane.showMessageDialog(this, "Eliminacion cancelada por el usuario");
        }
       
   }
           
       
       
          public void llenartablita() 
   {
       String[] columnNames = {"CODIGO_USUARIO","NOMBRE","APELLIDO","CORREO","CONTRASENA","FK_NIT","ROL","SITUACION"};
        dtm = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(dtm);
         try (Connection conn = getConnection()) {
            String query ="SELECT CODIGO_USUARIO, NOMBRE, APELLIDO, CORREO, CONTRASENA, FK_NIT, ROL, SITUACION FROM USUARIOS";
             PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString("CODIGO_USUARIO");
                String nom = rs.getString("NOMBRE");
                 String ap = rs.getString("APELLIDO");
                String co = rs.getString("CORREO");
                String con = rs.getString("CONTRASENA");
                String nit = rs.getString("FK_NIT");
                String ro = rs.getString("ROL");
                String sit = rs.getString("SITUACION");
                
             dtm.addRow(new Object[]{id,nom,ap,co,con,nit,ro,sit});
         }
         
         }
         catch (Exception e) {
            e.printStackTrace();}
   }
     
    
    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        initComponents();
        llenartablita();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jLabel3.setText("Correo");

        jLabel4.setText("Password");

        jLabel5.setText("NIT");

        jLabel6.setText("ROL");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "C", "A" }));

        jLabel7.setText("SITUACION");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "0", "1" }));

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setText("codigo");

        jTextField6.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton2)
                        .addGap(167, 167, 167)
                        .addComponent(jButton3)
                        .addGap(149, 149, 149)
                        .addComponent(jButton4)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GestionViajesApp gv = new GestionViajesApp();
        gv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        iniciartxt();
        CrearUsuario(nombre, apellido, correo, contra, fknit, rol, situacion);
        llenartablita();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int rowIndex = jTable1.rowAtPoint(evt.getPoint());
         if (rowIndex < 0) {
        return; // Salir si la fila seleccionada no es válida
    }
         String codigotext = String.valueOf(jTable1.getValueAt(rowIndex,0));
         String nombretext = String.valueOf(jTable1.getValueAt(rowIndex,1));
         String apellidotext = String.valueOf(jTable1.getValueAt(rowIndex,2));
         String correotext = String.valueOf(jTable1.getValueAt(rowIndex,3));
         String contrasenatext = String.valueOf(jTable1.getValueAt(rowIndex,4));
         String fknittext = String.valueOf(jTable1.getValueAt(rowIndex,5));
         String roltext = String.valueOf(jTable1.getValueAt(rowIndex,6));
         String situatext = String.valueOf(jTable1.getValueAt(rowIndex,7));
         
         jTextField6.setText(codigotext);
         jTextField1.setText(nombretext);
         jTextField2.setText(apellidotext);
         jTextField3.setText(correotext);
         jTextField4.setText(contrasenatext);
         jTextField5.setText(fknittext);
         jComboBox1.setSelectedItem(roltext);
         jComboBox2.setSelectedItem(situatext);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        iniciartxt();
        String c = jTextField6.getText();
        int codigo =Integer.parseInt(c); 
        System.out.println("codigo "+codigo);
        actualizardatosUsuario(nombre, apellido, correo, contra, fknit, rol, situacion, codigo);
        llenartablita();
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String c = jTextField6.getText();
        eliminarRegistroUsuario(c);
        llenartablita();            
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
