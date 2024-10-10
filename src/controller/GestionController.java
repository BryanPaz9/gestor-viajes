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
            System.out.println("No se encontraron viajes para el mes y año especificados.");
            } else {
                int i=0;
                while (resultado.next()) {
                    // Procesar resultados
                    i++;
                    System.out.println(i+"  Solicitante: " + resultado.getString("SOLICITANTE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        }
    }
    
    public void vehiculo_cantidad_viajes(){
        String sql="SELECT FK_PLACA,COUNT(*) AS Viajes_realizados,SUM(DISTANCIA) AS DISTANCIA_TOTAL_RECORRIDA " +
        "FROM VIAJES GROUP BY FK_PLACA ORDER BY Viajes_realizados Desc  FETCH FIRST 1 ROWS ONLY";
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            if(!res.isBeforeFirst()){
                System.out.println("No se encontraron vehículos. Verifique los datos");
            }else{
                while(res.next()){
                    System.out.println("Placa: "+res.getString("FK_PLACA"));
                    System.out.println("Viajes realizados: "+res.getString("VIAJES_REALIZADOS"));
                    System.out.println("Distancia total recorrida por el vehículo: "+res.getString("DISTANCIA_TOTAL_RECORRIDA")+" Km.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
    }
    
    public void cliente_cantidad_solicitudes (){
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
            if(!res.isBeforeFirst()){
                System.out.println("No se encontraron vehículos. Verifique los datos");
            }else{
                while(res.next()){
                    System.out.println("Cliente: "+res.getString("CLIENTE"));
                    System.out.println("Viajes realizados: "+res.getString("TOTAL_VIAJES"));
                    System.out.println("Costo Total: Q. "+res.getString("COSTO_TOTAL"));
                    System.out.println("Distancia Total: "+res.getString("DISTANCIA_TOTAL")+" Km.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
        
    }
    public void ganancias_mes(String mes,String anio){
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
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(sql);
        }
    }
    public void vehiculo_recorrido(){
        String sql="SELECT FK_PLACA,COUNT(*) AS Viajes_realizados,SUM(DISTANCIA) AS DISTANCIA_TOTAL_RECORRIDA " +
        "FROM VIAJES GROUP BY FK_PLACA ORDER BY DISTANCIA_TOTAL_RECORRIDA Desc FETCH FIRST 1 ROWS ONLY";
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();
            if(!res.isBeforeFirst()){
                System.out.println("No ses encontraron vehiculos. Verifique los datos.");
            }else{
                while(res.next()){
                    System.out.println("Vehículo con mayor recorrido: "+res.getString("FK_PLACA"));
                    System.out.println("Viajes realizados: "+res.getString("VIAJES_REALIZADOS"));
                    System.out.println("Distancia total recorrida: "+res.getString("DISTANCIA_TOTAL_RECORRIDA")+" Km.");
                }
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
