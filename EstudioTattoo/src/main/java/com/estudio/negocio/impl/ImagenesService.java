package com.estudio.negocio.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.IImagenesDAO;
import com.estudio.dtos.ImagenesDTO;
import com.estudio.negocio.IImagenesService;

@Component
public class ImagenesService implements IImagenesService{

	@Autowired
	IImagenesDAO imagenesDAOImpl;
	
	@Override
	public List<ImagenesDTO> buscarImagenes(Integer idImagen, Integer tatuador, String descripcion, String imagenes)
			throws ClassNotFoundException, SQLException {
		
		return imagenesDAOImpl.buscarImagenes(idImagen, tatuador, descripcion, imagenes);
	}

	

}
