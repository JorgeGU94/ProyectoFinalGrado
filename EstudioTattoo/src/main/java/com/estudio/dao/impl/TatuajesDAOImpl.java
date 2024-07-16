package com.estudio.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ITatuajesDAO;
import com.estudio.dtos.TatuajesDTO;
import com.estudio.entities.TatuajesEntity;
import com.estudio.repositories.ITatuajesRepository;

@Component
public class TatuajesDAOImpl implements ITatuajesDAO {

	@Autowired
	private ITatuajesRepository tatuajesRepository;
	
	@Override
	public List<TatuajesDTO> buscarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio)
			throws ClassNotFoundException, SQLException {
		
		return tatuajesRepository.buscarTatuajes(idTatuajes, descripcion, color, tamano, precio);
		
	}

	@Override
	public Integer insertarTatuajes(String descripcion, String color, double tamano, double precio) throws ClassNotFoundException, SQLException {
		
		TatuajesEntity te = new TatuajesEntity(descripcion, Integer.parseInt(color), tamano, precio);
		tatuajesRepository.save(te);
		
		return te.getIdTatuajes();
		
	}

	@Override
	public Integer actualizarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio)
			throws ClassNotFoundException, SQLException {

		TatuajesEntity te = new TatuajesEntity(idTatuajes, descripcion, Integer.parseInt(color), tamano, precio);
		tatuajesRepository.save(te);
		
		return te.getIdTatuajes();
		
	}

}
