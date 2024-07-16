package com.estudio.dtos;

public class CitasDTO {

	private Integer idCitas;
	private String fecha;
	private Integer cliente;
	private Integer tatuador;
	private Integer tatuajes;
	private Integer activo;
	
	public CitasDTO(Integer idCitas, String fecha, Integer cliente, Integer tatuador,
			Integer tatuajes, Integer activo) {
		super();
		this.idCitas = idCitas;
		this.fecha = fecha;
		this.cliente = cliente;
		this.tatuador = tatuador;
		this.tatuajes = tatuajes;
		this.activo = activo;
	}

	public CitasDTO() {
		super();
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

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getTatuador() {
		return tatuador;
	}

	public void setTatuador(Integer tatuador) {
		this.tatuador = tatuador;
	}

	public Integer getTatuajes() {
		return tatuajes;
	}

	public void setTatuajes(Integer tatuajes) {
		this.tatuajes = tatuajes;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	

}
