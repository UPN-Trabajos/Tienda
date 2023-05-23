package com.example.ProyectoFinal_Grupo03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="suscripcion")
public class Suscripcion {
	@Id //La linea siguiente es ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincremental
    private int id_suscripcion;
	private String email;
	public Suscripcion(int id_suscripcion, String email) {
		super();
		this.id_suscripcion = id_suscripcion;
		this.email = email;
	}
	public Suscripcion(String email) {
		super();
		this.email = email;
	}
	public Suscripcion() {
		super();
	}
	@Override
	public String toString() {
		return "Suscripcion [id_suscripcion=" + id_suscripcion + ", email=" + email + "]";
	}
	public int getId_suscripcion() {
		return id_suscripcion;
	}
	public void setId_suscripcion(int id_suscripcion) {
		this.id_suscripcion = id_suscripcion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
