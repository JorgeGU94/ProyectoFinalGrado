package com.estudio.dtos;

public class ColorTamanoDTO {

	private Integer color;
	private double tamano;
	
	public ColorTamanoDTO() {
		super();
	}

	public ColorTamanoDTO(Integer color, double tamano) {
		super();
		this.color = color;
		this.tamano = tamano;
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
	
	
}
