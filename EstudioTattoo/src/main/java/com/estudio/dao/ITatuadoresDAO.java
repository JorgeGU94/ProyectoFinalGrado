package com.estudio.dao;

import java.sql.SQLException;
import java.util.List;

import com.estudio.dtos.TatuadoresDTO;

public interface ITatuadoresDAO {

	public List<TatuadoresDTO> buscarTatuadores(Integer id, String nombre, String apellidos, String email, String especialidad, String descripcion, Integer activo) throws ClassNotFoundException, SQLException;

	public Integer insertarTatuador(String nombre, String apellidos, String email, String especialidad, String descripcion, Integer activo) throws ClassNotFoundException, SQLException;
	
	public Integer actualizarTatudor(Integer id, String nombre, String apellidos, String email, String especialidad, String descripcion, Integer activo) throws ClassNotFoundException, SQLException;
	
	public Integer borrarTatuador(Integer id) throws ClassNotFoundException, SQLException;
	
}
