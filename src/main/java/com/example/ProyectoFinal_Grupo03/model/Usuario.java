package com.example.ProyectoFinal_Grupo03.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
    @Id //La linea siguiente es ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincremental
    private int id_usuario;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String zip;
    private String telefono;
    private String tipo;
    private String password;

    public Usuario(int id_usuario, String nombre, String username, String email, String direccion, String departamento, String provincia, String distrito, String zip, String telefono, String tipo, String password) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.zip = zip;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
    }
    
    

    public Usuario(String nombre, String username, String email, String direccion, String departamento,
			String provincia, String distrito, String zip, String telefono, String tipo, String password) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.direccion = direccion;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
		this.zip = zip;
		this.telefono = telefono;
		this.tipo = tipo;
		this.password = password;
	}



	public Usuario() {
    }
    
    @OneToMany(mappedBy="usuario")
    private List<Producto> productos;
    
    @OneToMany(mappedBy="usuario")
    private List<Orden> ordenes;

	public int getId() {
		return id_usuario;
	}

	public void setId(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}
    
    

}
