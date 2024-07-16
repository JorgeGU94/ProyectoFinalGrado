package com.estudio.dtos;

public class TatuadoresDTO {
	
	private Integer idTatuadores;
    private String nombre;
    private String apellidos;
    private String email;
    private String especialidad;
    private String descripcion;
    private Integer activo;
	
	public TatuadoresDTO() {
		super();
	}

	public TatuadoresDTO(Integer idTatuadores, String nombre, String apellidos, String email, String especialidad,
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
