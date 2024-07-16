package com.estudio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudio.dtos.ImagenesDTO;
import com.estudio.entities.ImagenesEntity;

@Repository
public interface IImagenesRepository extends CrudRepository<ImagenesEntity, Integer>{

	/* IS NULL OR = si viene el parametro como null lo ignora y no realiza la comprobacion y filtra por el resto */
	
	@Query(value = "SELECT new com.estudio.dtos.ImagenesDTO "
			   + "(ie.idImagen, te.idTatuadores, ie.descripcion, ie.imagenes)"
			   + " FROM ImagenesEntity ie "
			   + "		JOIN ie.tatuador te "
			   + "					WHERE (:id IS NULL OR ie.idImagen = :id) "
			   + " 					AND (:tatuador IS NULL OR te.idTatuadores = :tatuador) "
			   + "					AND (:descrip IS NULL OR ie.descripcion LIKE CONCAT('%', :descrip, '%')) "
               + "					AND  ie.imagenes LIKE CONCAT ('%', :imagenes, '%')")	
	public List<ImagenesDTO> buscarImagenes(
			@Param("id") Integer idImagen, 
			@Param("tatuador") Integer tatuador, 
			@Param("descrip") String descripcion, 
			@Param("imagenes") String imagenes);
}
