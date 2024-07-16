package com.estudio.dtos;

public class TatuajesDTO {
	
    private Integer idTatuajes;
    private String descripcion;
    private Integer color;
    private double tamano;
    private double precio;
    
	public TatuajesDTO() {
		super();
	}

	public TatuajesDTO(Integer idTatuajes, String descripcion, Integer color, double tamano, double precio) {
		super();
		this.idTatuajes = idTatuajes;
		this.descripcion = descripcion;
		this.color = color;
		this.tamano = tamano;
		this.precio = precio;
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
