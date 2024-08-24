/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author bryger
 */
public class Viaje {
    int idViaje;
    String origen,destino,estado;
    Double latitudOrigen,latitudDestino,longitudOrigen,longitudDestino;
    Date fechaSalida,fechaLlegada;

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(Double latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public Double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Double getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(Double longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public Double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino = longitudDestino;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
