/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Viaje;
import model.GestionViajes;
import javax.swing.JOptionPane;
import view.GestionViajesApp;
import db.Conexion;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bryge
 */
public class GestionController {
    
    private List <Viaje> viajes;
    
    /**
     * Constructor vacío de GestionViajes.
     */
    public GestionController(){
        this.viajes = new ArrayList<>();
    }
    
    /**
     * Método para agregar un viaje nuevo.
     * @param viaje 
     */
    public void agregarViaje(Viaje viaje){
        viajes.add(viaje);
    }
    
    /**
     * Método para editar un viaje.
     * @param idViaje
     * @param viajeActualizado 
     */
    
    public void editarViaje(int idViaje, Viaje viajeActualizado){
        for(int i = 0; i< viajes.size(); i++){
            if(viajes.get(i).getIdViaje() == idViaje){
                viajes.set(i, viajeActualizado);
                break;
            }
        }
    }
    
    /**
     * Método para eliminar un viaje en específico.
     * @param idViaje 
     */
    public void eliminarViaje(int idViaje) {
        viajes.removeIf(viaje -> viaje.getIdViaje() == idViaje);
    }
    
    /**
     * Método para obtener todos los viajes.
     * @return 
     */
    public List <Viaje> obtenerViajes(){
        return viajes;
    }
    
    
    public void exportarViajesACSV(List<Viaje> viajes, String nombreArchivo) {
    String path = System.getProperty("user.dir");
    String directorioCSV = path + "/CSV/" + nombreArchivo;

    StringBuilder csvBuilder = new StringBuilder();
    csvBuilder.append("ID,Origen,Destino,FechaSalida,FechaLlegada,Estado\n");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    for (Viaje viaje : viajes) {
        csvBuilder.append(viaje.getIdViaje()).append(",")
                .append(viaje.getOrigen()).append(",")
                .append(viaje.getDestino()).append(",")
                .append(viaje.getFechaSalida() != null ? dateFormat.format(viaje.getFechaSalida()) : "").append(",")
                .append(viaje.getFechaLlegada() != null ? dateFormat.format(viaje.getFechaLlegada()) : "").append(",")
                .append(viaje.getEstado()).append("\n");

    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(directorioCSV))) {
        writer.write(csvBuilder.toString());
        JOptionPane.showMessageDialog(null, "CSV exportado exitosamente a " + directorioCSV);
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea abrir el directorio?", "Documento exportado con éxito", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            abreDirectorio();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo CSV: " + e.getMessage());
    }
}


    public void abreDirectorio(){
            // TODO add your handling code here:
        String path = System.getProperty("user.dir");
                try {
            Desktop.getDesktop().open(Paths.get(path+"/CSV").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void viajes_por_mes(String mes, String anio) {
        String sql = "SELECT CODIGO_VIAJE, USU.NOMBRE || ' ' || USU.APELLIDO AS SOLICITANTE, " +
                "CLI.NOMBRE AS CLIENTE, FK_PLACA, COSTO, ORI.NOMBRE AS ORIGEN, " +
                "DES.NOMBRE AS DESTINO, DISTANCIA, FECHA_SALIDA, FECHA_LLEGADA, ESTADO, FECHA_REGISTRO " +
                "FROM VIAJES " +
                "JOIN USUARIOS USU ON FK_CODIGO_USUARIO = USU.CODIGO_USUARIO " +
                "JOIN CLIENTES CLI ON FK_NIT = CLI.NIT " +
                "JOIN UBICACIONES ORI ON ORIGEN = ORI.CODIGO_UBICACION " +
                "JOIN UBICACIONES DES ON DESTINO = DES.CODIGO_UBICACION " +
                "WHERE FECHA_LLEGADA BETWEEN TO_DATE ('01-"+mes+"-"+anio+"','DD-MM-YYYY') "+ 
                "AND LAST_DAY(TO_DATE('" + mes + "-" + anio + "','MM-YYYY'))";  // Último día del mes

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asignar los parámetros de mes y año a la consulta
            //stmt.setString(1, "%"+mes + "-" + anio+"%");
            System.out.println(sql);
            ResultSet resultado = stmt.executeQuery();
            if (!resultado.isBeforeFirst()) { // Verifica si hay resultados
                //System.out.println("No se encontraron viajes para el mes y año especificados.");
                JOptionPane.showMessageDialog(null, "No se encontraron viajes para el mes y año especificados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                // Crear un modelo de tabla con los nombres de columnas
                String[] columnas = {"Código", "Solicitante", "Cliente", "Placa", "Costo", "Origen", 
                                     "Destino", "Distancia", "Fecha Salida", "Fecha Llegada", "Estado", "Fecha Registro"};
                DefaultTableModel model = new DefaultTableModel(columnas, 0); // 0 es el número de filas inicial

                // Llenar el modelo con los datos obtenidos
                while (resultado.next()) {
                    Object[] fila = new Object[12];  // Número de columnas
                    fila[0] = resultado.getInt("CODIGO_VIAJE");
                    fila[1] = resultado.getString("SOLICITANTE");
                    fila[2] = resultado.getString("CLIENTE");
                    fila[3] = resultado.getString("FK_PLACA");
                    fila[4] = resultado.getDouble("COSTO");
                    fila[5] = resultado.getString("ORIGEN");
                    fila[6] = resultado.getString("DESTINO");
                    fila[7] = resultado.getDouble("DISTANCIA");
                    fila[8] = resultado.getDate("FECHA_SALIDA");
                    fila[9] = resultado.getDate("FECHA_LLEGADA");
                    fila[10] = resultado.getString("ESTADO");
                    fila[11] = resultado.getDate("FECHA_REGISTRO");

                    model.addRow(fila); // Añadir la fila al modelo
                }

                // Crear la tabla y asignarle el modelo
                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table); // Añadir la tabla a un JScrollPane para scroll
                table.setFillsViewportHeight(true);

                // Mostrar la tabla en una ventana nueva
                JFrame frame = new JFrame("Resultados de Viajes");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(scrollPane);
                frame.setSize(800, 600); // Ajustar el tamaño de la ventana
                frame.setVisible(true);  // Hacer visible la ventana
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        }
    }
    
    public String vehiculo_cantidad_viajes(){
        String sql="SELECT FK_PLACA,COUNT(*) AS Viajes_realizados,SUM(DISTANCIA) AS DISTANCIA_TOTAL_RECORRIDA " +
        "FROM VIAJES GROUP BY FK_PLACA ORDER BY Viajes_realizados Desc  FETCH FIRST 1 ROWS ONLY";
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            String response = "";
            if(!res.isBeforeFirst()){
                
                response = "No se encontraron vehículos. Verifique los datos";
                return response;
            }else{
                while(res.next()){
                    String placa = res.getString("FK_PLACA");
                    String viajes_realizados = res.getString("VIAJES_REALIZADOS");
                    String distancia_recorrida= res.getString("DISTANCIA_TOTAL_RECORRIDA");
                    response = "Placa: "+placa+"\n"+
                            "Viajes realizados: "+viajes_realizados+"\n"+
                            "Distancia total recorrida "+distancia_recorrida+" Km.";
                    return response;
                    
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
        return null;
    }
    
    public String cliente_cantidad_solicitudes (){
        String sql="SELECT  " +
            "CLI.NOMBRE AS CLIENTE, " +
            "COUNT(VIA.CODIGO_VIAJE) AS TOTAL_VIAJES, " +
            "SUM(VIA.COSTO) AS COSTO_TOTAL, " +
            "SUM(VIA.DISTANCIA) AS DISTANCIA_TOTAL " +
            "FROM VIAJES VIA " +
            "JOIN USUARIOS USU ON VIA.FK_CODIGO_USUARIO = USU.CODIGO_USUARIO " +
            "JOIN CLIENTES CLI ON USU.FK_NIT = CLI.NIT " +
            "JOIN UBICACIONES ORI ON VIA.ORIGEN = ORI.CODIGO_UBICACION " +
            "JOIN UBICACIONES DES ON VIA.DESTINO = DES.CODIGO_UBICACION " +
            "GROUP BY CLI.NOMBRE " +
            "FETCH FIRST 1 ROWS ONLY";
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            String response = "";
            if(!res.isBeforeFirst()){
                response = "No se encontraron vehículos. Verifique los datos";
                return response;
            }else{
                while(res.next()){
                    String cliente = res.getString("CLIENTE");
                    String viajes_realizados = res.getString("TOTAL_VIAJES");
                    String costo= res.getString("COSTO_TOTAL");
                    String distancia= res.getString("DISTANCIA_TOTAL");
                    response = "Cliente: "+cliente+"\n"+
                            "Viajes realizados: "+viajes_realizados+"\n"+
                            "Total gastado: Q. "+costo+"\n"+
                            "Distancia total recorrida "+distancia+" Km.";
                    return response;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
        return null;
    }
    public String ganancias_mes(String mes,String anio){
        
        
        
        String sql="SELECT SUM(COSTO)AS TOTAL_GANANCIA " +
        "FROM VIAJES "+
        "WHERE FECHA_LLEGADA BETWEEN TO_DATE ('01-"+mes+"-"+anio+"','DD-MM-YYYY') "+ 
        "AND LAST_DAY(TO_DATE('" + mes + "-" + anio + "','MM-YYYY'))";  // Último día del mes
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            if(!res.isBeforeFirst()){
                System.out.println("No se encontraron ganancias. Verifique los datos");
            }else{
                while(res.next()){
                    System.out.println("Total de ganancias en el mes: "+res.getString("TOTAL_GANANCIA"));
                    return res.getString("TOTAL_GANANCIA");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
        return null;
    }
    
    public String vehiculo_recorrido(){
        String sql="SELECT FK_PLACA,COUNT(*) AS Viajes_realizados,SUM(DISTANCIA) AS DISTANCIA_TOTAL_RECORRIDA " +
        "FROM VIAJES WHERE ESTADO = 4 GROUP BY FK_PLACA ORDER BY DISTANCIA_TOTAL_RECORRIDA Desc FETCH FIRST 1 ROWS ONLY";
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            String response ="";
            if(!res.isBeforeFirst()){
                response = "No ses encontraron vehiculos. Verifique los datos.";
                return response;
            }else{
                while(res.next()){
                    String placa = res.getString("FK_PLACA");
                    String viajes = res.getString("VIAJES_REALIZADOS");
                    String distancia = res.getString("DISTANCIA_TOTAL_RECORRIDA");
                    response = "Placa: "+placa+"\n"+
                            "Viajes realizados: "+viajes+"\n"+
                            "Distancia total recorrida "+distancia+" Km.";
                    return response;
                }
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void aprobar(String placa, Double precio,Integer codigoViaje){
        String sql = "UPDATE VIAJES SET FK_PLACA = ?, COSTO = ?, ESTADO = ? WHERE CODIGO_VIAJE = ?";
        try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {          
            // Asignar los valores a los parámetros
            stmt.setString(1, placa);
            stmt.setDouble(2, precio);
            stmt.setInt(3, 2);  
            stmt.setInt(4, codigoViaje); // Este es el identificador para   saber qué cliente actualizar
            System.out.println(placa);
            System.out.println(precio);
            System.out.println(codigoViaje);
            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje aprobado satisfactoriamente!","Aprobación de viaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró un viaje con el codigo proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        }
        
    
    }
    
    
    
    public void denegar(Integer codigoViaje){
        String sql = "UPDATE VIAJES SET ESTADO = ? WHERE CODIGO_VIAJE = ?";
        try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {          
            // Asignar los valores a los parámetros
            stmt.setInt(1, 0);
            stmt.setInt(2, codigoViaje); // Este es el identificador para saber qué cliente actualizar
            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje denegado satisfactoriamente!","Viaje denegado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró un viaje con el codigo proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void iniciarViaje(Integer codigoViaje){
        String sql = "UPDATE VIAJES SET FECHA_SALIDA = TO_DATE(?, 'YYYY-MM-DD'), ESTADO = ? WHERE CODIGO_VIAJE = ?";
        try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {          
            LocalDate fechaActual = LocalDate.now();
            
            String fechaFormateada = fechaActual.toString();  // Formato 'YYYY-MM-DD'
            
            System.out.println(fechaFormateada);
            // Asignar los valores a los parámetros
            stmt.setString(1, fechaFormateada);
            stmt.setInt(2, 3);
            stmt.setInt(3, codigoViaje); // Este es el identificador para saber qué cliente actualizar
            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje iniciado satisfactoriamente!","Inicio de viaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró un viaje con el codigo proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void finalizarViaje(Integer codigoViaje){
        String sql = "UPDATE VIAJES SET FECHA_LLEGADA = TO_DATE(?, 'YYYY-MM-DD'), ESTADO = ? WHERE CODIGO_VIAJE = ?";
        try (Connection conn = Conexion.getConnection();
       
            PreparedStatement stmt = conn.prepareStatement(sql)) {          
            LocalDate fechaActual = LocalDate.now();
            
            String fechaFormateada = fechaActual.toString();  // Formato 'YYYY-MM-DD'
            
            System.out.println(fechaFormateada);
            // Asignar los valores a los parámetros
            stmt.setString(1, fechaFormateada);
            stmt.setInt(2, 4);
            stmt.setInt(3, codigoViaje); // Este es el identificador para saber qué cliente actualizar
            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje finalizado satisfactoriamente!","Fin de viaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró un viaje con el codigo proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void solicitarViaje(String solicitante,String origen, String destino) {
        // Consulta SQL parametrizada
        String sql = "INSERT INTO VIAJES (FK_CODIGO_USUARIO, ORIGEN, DESTINO, ESTADO, COSTO,DISTANCIA) VALUES (?, ?, ?, 1,0,0)";

        // Variables para almacenar los valores dinámicos
        int usuarioId = Integer.parseInt(solicitante);
        int origenId = Integer.parseInt(origen);
        int destinoId = Integer.parseInt(destino);

        // Construcción de la consulta con los valores para depuración
        String consultaCompleta = String.format(
            "INSERT INTO VIAJES (FK_CODIGO_USUARIO, ORIGEN, DESTINO, ESTADO) VALUES (%d, %d, %d, 1)",
            usuarioId, origenId, destinoId
        );

        System.out.println("Ejecutando: " + consultaCompleta);  // Imprimir la consulta para depuración

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asignar los valores a los parámetros del PreparedStatement
            stmt.setInt(1, usuarioId); // FK_CODIGO_USUARIO
            stmt.setInt(2, origenId);  // ORIGEN
            stmt.setInt(3, destinoId); // DESTINO

            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje solicitado satisfactoriamente!", 
                                              "Viaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudo insertar el viaje.");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // Mostrar la consulta que causó el error junto con el mensaje de excepción
            JOptionPane.showMessageDialog(null, "Error de integridad: " + e.getMessage() + 
                                          "\nConsulta: " + consultaCompleta,
                                          "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            // Mostrar cualquier otro error SQL junto con la consulta
            System.out.println("Error al ejecutar: " + consultaCompleta);
            e.printStackTrace();
        }

    }



    
}
