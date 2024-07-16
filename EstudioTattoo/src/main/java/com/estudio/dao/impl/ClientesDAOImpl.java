package com.estudio.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.IClientesDAO;
import com.estudio.dtos.ClientesDTO;
import com.estudio.entities.ClientesEntity;
import com.estudio.repositories.IClientesRepository;

@Component
public class ClientesDAOImpl implements IClientesDAO{

	@Autowired
	private IClientesRepository clientesRepository;

	@Override
	public List<ClientesDTO> buscarClientes(Integer id, String nombre, String apellidos, String telefono, String email,
			String password) throws ClassNotFoundException, SQLException {
		
		return clientesRepository.buscarClientes(id, nombre, apellidos, telefono, email, password);
	
	}

	@Override
	public Integer insertarClientes(String nombre, String apellidos, String telefono, String email, String password)
			throws ClassNotFoundException, SQLException {

		ClientesEntity ce = new ClientesEntity(nombre, apellidos, telefono, email, password);
		clientesRepository.save(ce);
		
		return ce.getIdClientes();
	
	}

	@Override
	public Integer actualizarClientes(Integer id, String nombre, String apellidos, String telefono, String email,
			String password) throws ClassNotFoundException, SQLException {
		
		ClientesEntity ce = new ClientesEntity(id, nombre, apellidos, telefono, email, password);
		clientesRepository.save(ce);
		
		return ce.getIdClientes();
	
	}	

}
