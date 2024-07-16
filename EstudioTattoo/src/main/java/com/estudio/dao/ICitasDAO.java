package com.estudio.dao;

import java.sql.SQLException;
import java.util.List;

import com.estudio.dtos.CitasDTO;

public interface ICitasDAO {

	public List<CitasDTO> buscarCitas(Integer id, String fecha, Integer cliente, Integer tatuador,  Integer tatuajes, Integer activo)throws ClassNotFoundException, SQLException;

	public Integer insertarCitas(String fecha, Integer cliente, Integer tatuador,  Integer tatuajes, Integer activo)throws ClassNotFoundException, SQLException;
	
	public Integer actualizarCitas(Integer id, String fecha, Integer cliente, Integer tatuador,  Integer tatuajes, Integer activo)throws ClassNotFoundException, SQLException;
	
	public Integer borrarCitas(Integer id)throws ClassNotFoundException, SQLException;
}
