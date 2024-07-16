package com.estudio.dao;

import com.estudio.dtos.ImagenesDTO;
import java.sql.SQLException;
import java.util.List;

public interface IImagenesDAO {

	public List<ImagenesDTO> buscarImagenes(Integer idImagen, Integer tatuador, String descripcion, String imagenes)throws ClassNotFoundException, SQLException;

}
