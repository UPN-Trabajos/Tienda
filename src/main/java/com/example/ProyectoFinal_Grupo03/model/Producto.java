package com.example.ProyectoFinal_Grupo03.model;

import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto {
    @Id //La linea siguiente es ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincremental
    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;

    public String toString() {
        return "Producto [id_producto="+id_producto+", nombre="+nombre+", descripcion="+descripcion+", imagen="+imagen
                +", precio="+precio+", cantidad="+cantidad+", categoria="+categoria+"]";
    }
    
    @ManyToOne
    @JoinColumn(name="id_usuario") //FK
    private Usuario usuario;
    
    @ManyToOne()
    @JoinColumn(name="id_categoria") //FK
    private Categoria categoria;

	public Integer getId() {
		return id_producto;
	}

	public void setId(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
    
}
