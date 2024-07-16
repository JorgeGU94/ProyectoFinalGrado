package com.estudio.dao;

import java.sql.SQLException;
import java.util.List;


import com.estudio.dtos.ClientesDTO;

public interface IClientesDAO {
	
	public List<ClientesDTO> buscarClientes(Integer id, String nombre, String apellidos, String telefono, String email, String password) throws ClassNotFoundException, SQLException;
	
	public Integer insertarClientes(String nombre, String apellidos, String telefono, String email, String password)throws ClassNotFoundException, SQLException;

	public Integer actualizarClientes(Integer id, String nombre, String apellidos, String telefono, String email, String password)throws ClassNotFoundException, SQLException;

}
