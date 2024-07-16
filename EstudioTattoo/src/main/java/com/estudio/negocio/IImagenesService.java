package com.estudio.negocio;

import java.sql.SQLException;
import java.util.List;

import com.estudio.dtos.ImagenesDTO;

public interface IImagenesService {

	public List<ImagenesDTO> buscarImagenes(Integer idImagen, Integer tatuador, String descripcion, String imagenes)throws ClassNotFoundException, SQLException;

}
