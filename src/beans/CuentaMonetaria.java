/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Ana Ramos
 */
public class CuentaMonetaria {
    private int id;
    private Date fechaApertura;
    private Double montoInicial;
    private int cantidadCheques;
  
    public CuentaMonetaria() {
    }

    public CuentaMonetaria(int id, Date fechaApertura, Double montoInicial, int cantidadCheques) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.montoInicial = montoInicial;
        this.cantidadCheques = cantidadCheques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Double getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }

    public int getCantidadCheques() {
        return cantidadCheques;
    }

    public void setCantidadCheques(int cantidadCheques) {
        this.cantidadCheques = cantidadCheques;
    }
    
    
}
