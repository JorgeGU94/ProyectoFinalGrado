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
@Table(name = "tatuajes")
public class TatuajesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tatuajes")
    private Integer idTatuajes;
    
    @Column(name = "Descripcion")
    private String descripcion;
    
    @Column(name = "Color")
    private Integer color;
    
    @Column(name = "Tamano")
    private double tamano;
    
    @Column(name = "Precio")
    private double precio;
    
    @OneToMany(mappedBy = "tatuajes")
    @JsonManagedReference(value="tatuajes-citas")
    private List<CitasEntity> citas;
	
	public TatuajesEntity() {
		super();
	}

	public TatuajesEntity(Integer idTatuajes) {
		super();
		this.idTatuajes = idTatuajes;
	}

	public TatuajesEntity(Integer idTatuajes, String descripcion, Integer color, double tamano, double precio) {
		super();
		this.idTatuajes = idTatuajes;
		this.descripcion = descripcion;
		this.color = color;
		this.tamano = tamano;
		this.precio = precio;
	}

	public TatuajesEntity(String descripcion, Integer color, double tamano, double precio) {
		super();
		this.descripcion = descripcion;
		this.color = color;
		this.tamano = tamano;
		this.precio = precio;
	}
	
	public TatuajesEntity(Integer idTatuajes, String descripcion, Integer color, double tamano, double precio,
			List<CitasEntity> citas) {
		super();
		this.idTatuajes = idTatuajes;
		this.descripcion = descripcion;
		this.color = color;
		this.tamano = tamano;
		this.precio = precio;
		this.citas = citas;
	}

	public Integer getIdTatuajes() {
		return idTatuajes;
	}

	public void setIdTatuajes(Integer idTatuajes) {
		this.idTatuajes = idTatuajes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public double getTamano() {
		return tamano;
	}

	public void setTamano(double tamano) {
		this.tamano = tamano;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
