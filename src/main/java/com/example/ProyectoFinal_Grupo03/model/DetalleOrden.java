package com.example.ProyectoFinal_Grupo03.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="DetalleOrden")
public class DetalleOrden {
    @Id //La linea siguiente es ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincremental
    private int id_detalle_orden;
    private String nombre;
    private int cantidad;
    private double precio;
    private double total;
    
    @ManyToOne
    @JoinColumn(name="id_orden") //FK
    private Orden orden;
    
    @ManyToOne
    @JoinColumn(name="id_producto") //FK
    private Producto producto;

	public int getId() {
		return id_detalle_orden;
	}

	public void setId(int id_detalle_orden) {
		this.id_detalle_orden = id_detalle_orden;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
    
    
}