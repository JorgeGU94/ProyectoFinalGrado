package com.estudio.dao;

import java.sql.SQLException;
import java.util.List;


import com.estudio.dtos.TatuajesDTO;


public interface ITatuajesDAO {

	public List<TatuajesDTO> buscarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio) throws ClassNotFoundException, SQLException;
	
	public Integer insertarTatuajes(String descripcion, String color, double tamano, double precio) throws ClassNotFoundException, SQLException;
	
	public Integer actualizarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio) throws ClassNotFoundException, SQLException;
	
}
