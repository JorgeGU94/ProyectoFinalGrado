package com.estudio.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ICitasDAO;
import com.estudio.dtos.CitasDTO;
import com.estudio.entities.CitasEntity;
import com.estudio.entities.ClientesEntity;
import com.estudio.entities.TatuadoresEntity;
import com.estudio.entities.TatuajesEntity;
import com.estudio.repositories.ICitasRepository;

@Component
public class CitasDAOImpl implements ICitasDAO{

	@Autowired
	private ICitasRepository citasRepository;
	
	@Override
	public List<CitasDTO> buscarCitas(Integer id, String fecha, Integer cliente, Integer tatuador, Integer tatuajes,
			Integer activo) throws ClassNotFoundException, SQLException {

		return citasRepository.buscarCitas(id, fecha, cliente, tatuador, tatuajes, activo);
		
	}

	@Override
	public Integer insertarCitas(String fecha, Integer cliente, Integer tatuador, Integer tatuajes, Integer activo)
			throws ClassNotFoundException, SQLException {
		
		CitasEntity ce = new CitasEntity(fecha, new ClientesEntity(cliente), new TatuadoresEntity(tatuador), new TatuajesEntity(tatuajes), activo);
		citasRepository.save(ce);
		
		return ce.getIdCitas();
	}

	@Override
	public Integer actualizarCitas(Integer id, String fecha, Integer cliente, Integer tatuador, Integer tatuajes,
			Integer activo) throws ClassNotFoundException, SQLException {
		
		CitasEntity ce = new CitasEntity(id, fecha, new ClientesEntity(cliente), new TatuadoresEntity(tatuador), new TatuajesEntity(tatuajes), activo);
		citasRepository.save(ce);
		
		return ce.getIdCitas();
	}

	@Override
	public Integer borrarCitas(Integer id) throws ClassNotFoundException, SQLException {
		
		CitasEntity ce = citasRepository.findById(id).get();
		ce.setActivo(0);
		
		citasRepository.save(ce);
		
		return ce.getIdCitas();
	}

}
