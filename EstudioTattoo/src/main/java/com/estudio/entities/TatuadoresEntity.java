package com.estudio.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tatuadores")
public class TatuadoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tatuadores")
    private Integer idTatuadores;
    
    @Column(name = "Nombre")
    private String nombre;
    
    @Column(name = "Apellidos")
    private String apellidos;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Especialidad")
    private String especialidad;
    
    @Column(name = "Descripcion")
    private String descripcion;
    
    @Column(name = "Activo")
    private Integer activo;
    
    @OneToMany(mappedBy = "tatuador")
    @JsonManagedReference(value="tatuador-citas")
    private List<CitasEntity> citas;

    @OneToMany(mappedBy = "tatuador")
    @JsonManagedReference(value="tatuador-imagenes")
    private List<ImagenesEntity> imagenes;
	
	public TatuadoresEntity() {
		super();
	}

	public TatuadoresEntity(Integer idTatuadores) {
		super();
		this.idTatuadores = idTatuadores;
	}

	public TatuadoresEntity(String nombre, String apellidos, String email, String especialidad, String descripcion,
			Integer activo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.especialidad = especialidad;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public TatuadoresEntity(Integer idTatuadores, String nombre, String apellidos, String email, String especialidad,
			String descripcion, Integer activo) {
		super();
		this.idTatuadores = idTatuadores;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.especialidad = especialidad;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public TatuadoresEntity(Integer idTatuadores, String nombre, String apellidos, String email, String especialidad,
			String descripcion, Integer activo, List<CitasEntity> citas) {
		super();
		this.idTatuadores = idTatuadores;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.especialidad = especialidad;
		this.descripcion = descripcion;
		this.activo = activo;
		this.citas = citas;
	}

	public Integer getIdTatuadores() {
		return idTatuadores;
	}

	public void setIdTatuadores(Integer idTatuadores) {
		this.idTatuadores = idTatuadores;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
}
