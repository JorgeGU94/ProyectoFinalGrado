package com.estudio.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.IImagenesDAO;
import com.estudio.dtos.ImagenesDTO;
import com.estudio.repositories.IImagenesRepository;

@Component
public class IImagenesDAOImpl implements IImagenesDAO{

	@Autowired
	private IImagenesRepository imagenRepository;
	
	@Override
	public List<ImagenesDTO> buscarImagenes(Integer idImagen, Integer tatuador, String descripcion, String imagenes)
			throws ClassNotFoundException, SQLException {
		
		if (descripcion == "") {
			descripcion = null;
		}
		if (imagenes == "") {
			imagenes = null;
		}
		
		return imagenRepository.buscarImagenes(idImagen, tatuador, descripcion, imagenes);
		
	}

}
