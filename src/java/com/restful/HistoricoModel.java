/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restful;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Daniel
 */
public class HistoricoModel {
    
    private int idHisotrico;
    private String email;
    private float latitude;
    private float longitude;
    private String FechaHora;
    

    public int getIdHisotrico() {
        return idHisotrico;
    }

    public void setIdHisotrico(int idHisotrico) {
        this.idHisotrico = idHisotrico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String FechaHora) {
        this.FechaHora = FechaHora;
    }

    
    
    
    
    
    
}
