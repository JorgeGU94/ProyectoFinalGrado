package com.estudio.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas")
public class CitasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Citas")
    private Integer idCitas;
    
    @Column(name = "Fecha")
    private String fecha;
    
    @ManyToOne
    @JoinColumn(name="ID_Clientes")
    @JsonBackReference(value="cliente-citas")
    private ClientesEntity cliente;
    
    @ManyToOne
    @JoinColumn(name="ID_Tatuadores")
    @JsonBackReference(value="tatuador-citas")
    private TatuadoresEntity tatuador;
    
    @ManyToOne
    @JoinColumn(name="ID_Tatuajes")
    @JsonBackReference(value="tatuajes-citas")
    private TatuajesEntity tatuajes;
    
    @Column(name="Activo")
    private Integer activo;

	public CitasEntity() {
		super();
	}
	
	public CitasEntity(Integer idCitas, String fecha, ClientesEntity cliente, TatuadoresEntity tatuador,
			TatuajesEntity tatuajes, Integer activo) {
		super();
		this.idCitas = idCitas;
		this.fecha = fecha;
		this.cliente = cliente;
		this.tatuador = tatuador;
		this.tatuajes = tatuajes;
		this.activo = activo;
	}

	public CitasEntity(String fecha, ClientesEntity cliente, TatuadoresEntity tatuador, TatuajesEntity tatuajes,
			Integer activo) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.tatuador = tatuador;
		this.tatuajes = tatuajes;
		this.activo = activo;
	}

	public Integer getIdCitas() {
		return idCitas;
	}

	public void setIdCitas(Integer idCitas) {
		this.idCitas = idCitas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public ClientesEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClientesEntity cliente) {
		this.cliente = cliente;
	}

	public TatuadoresEntity getTatuador() {
		return tatuador;
	}

	public void setTatuador(TatuadoresEntity tatuador) {
		this.tatuador = tatuador;
	}

	public TatuajesEntity getTatuajes() {
		return tatuajes;
	}

	public void setTatuajes(TatuajesEntity tatuajes) {
		this.tatuajes = tatuajes;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
