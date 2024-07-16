package com.estudio.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "clientes")
public class ClientesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Clientes")
    private Integer idClientes;
    
    @Column(name = "Nombre")
    private String nombre;
    
    @Column(name = "Apellidos")
    private String apellidos;
    
    @Column(name = "Telefono")
    private String telefono;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference(value="cliente-citas")
    private List<CitasEntity> citas;
    
	public ClientesEntity() {
		super();
	}
	
	public ClientesEntity(Integer idClientes) {
		super();
		this.idClientes = idClientes;
	}

	public ClientesEntity(Integer idClientes, String nombre, String apellidos, String telefono, String email,
			String password) {
		super();
		this.idClientes = idClientes;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}
	
	public ClientesEntity(String nombre, String apellidos, String telefono, String email,
			String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}

	public ClientesEntity(Integer idClientes, String nombre, String apellidos, String telefono, String email,
			String password, List<CitasEntity> citas) {
		super();
		this.idClientes = idClientes;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.citas = citas;
	}

	public Integer getIdClientes() {
		return idClientes;
	}

	public void setIdClientes(Integer idClientes) {
		this.idClientes = idClientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
