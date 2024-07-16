package com.estudio.dtos;

public class ImagenesDTO {
	
	private Integer idImagen;
    private Integer tatuador;
    private String descripcion;
    private String imagenes;
    
	public ImagenesDTO(Integer idImagen, Integer tatuador, String descripcion, String imagenes) {
		super();
		this.idImagen = idImagen;
		this.tatuador = tatuador;
		this.descripcion = descripcion;
		this.imagenes = imagenes;
	}

	public ImagenesDTO() {
		super();
	}

	public Integer getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public Integer getTatuador() {
		return tatuador;
	}

	public void setTatuador(Integer tatuador) {
		this.tatuador = tatuador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenes() {
		return imagenes;
	}

	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}
}
