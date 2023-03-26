/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoFinal_Grupo03.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
public class Reporte {
    public long cantidadTotal = 0;
    public double montoTotal=0.0;
	public long getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(long cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
    
    
}
