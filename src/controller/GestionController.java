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
    
    // Exportar viajes a CSV y guardar en un archivo
    
//public void exportarViajesACSV(List<Viaje> viajes, String nombreArchivo) {
//    String path = System.getProperty("user.dir");
//    String directorioCSV = path + "/CSV/" + nombreArchivo;
//
//    StringBuilder csvBuilder = new StringBuilder();
//    csvBuilder.append("ID,Origen,Destino,FechaSalida,FechaLlegada,Estado\n");
//
//    // Definir el formato para las fechas
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    for (Viaje viaje : viajes) {
//        csvBuilder.append(viaje.getIdViaje()).append(",")
//                .append(viaje.getOrigen()).append(",")
//                .append(viaje.getDestino()).append(",")
//                .append(viaje.getFechaSalida() != null ? dateFormat.format(viaje.getFechaSalida()) : "").append(",")
//                .append(viaje.getFechaLlegada() != null ? dateFormat.format(viaje.getFechaLlegada()) : "").append(",")
//                .append(viaje.getEstado()).append("\n");
//    }
//
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(directorioCSV))) {
//        writer.write(csvBuilder.toString());
//        System.out.println("CSV exportado exitosamente a: " + directorioCSV);
//
//        JOptionPane.showMessageDialog(null, "CSV exportado exitosamente a " + directorioCSV);
//
//        int respuesta = JOptionPane.showConfirmDialog(
//            null,
//            "¿Desea abrir el directorio?",
//            "Documento exportado con éxito",
//            JOptionPane.YES_NO_OPTION,
//            JOptionPane.QUESTION_MESSAGE
//        );
//
//        if (respuesta == JOptionPane.YES_OPTION) {
//            // Si se selecciona "Sí", abrir el directorio
//            abreDirectorio();
//        }
//    } catch (IOException e) {
//        System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
//        JOptionPane.showMessageDialog(null, "Error al guardar el archivo CSV: " + e.getMessage());
//    }
//}
    
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
}
