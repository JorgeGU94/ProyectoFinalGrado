package com.estudio.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudio.dtos.ImagenesDTO;
import com.estudio.entities.ImagenesEntity;
import com.estudio.negocio.IImagenesService;
import com.estudio.repositories.IImagenesRepository;

@RestController
public class ImagenesControllerRest {

	@Autowired
	private IImagenesRepository imagenesRepository;
	
	@Autowired
	IImagenesService imagenesService;
	
	/********************************* LISTAR IMAGENES ******************************************/

	@GetMapping(value="/imagenes",params= {"idImagen","tatuador","descripcion","imagenes"})
	public List<ImagenesDTO> obtenerImagenesConFiltros(
			@RequestParam (value="idImagen",required=false) Integer idImagen,
			@RequestParam (value="tatuador",required=false) Integer tatuador,
			@RequestParam (value="descripcion",required=false) String descripcion,
			@RequestParam (value="imagenes",required=false) String imagenes){
		
		List<ImagenesDTO> img = imagenesRepository.buscarImagenes(idImagen, tatuador, descripcion, imagenes);
		
		return img;
		
	}

	//http://localhost:8080/tiendaTattoos/imagenes/2
	
	@GetMapping(value="/imagenes/{idImagen}")
	public ResponseEntity<ImagenesEntity> obtenerImagenesPorId(@PathVariable("idImagen")Integer idImagen){
		
		ImagenesEntity img = imagenesRepository.findById(idImagen).get();
		
		return new ResponseEntity<>(img, HttpStatus.OK);
		
	}
	
	//http://localhost:8080/tiendaTattoos/imagenes
	
	@GetMapping("/imagenes")
	public Iterable<ImagenesEntity> obtenerTodosImagenes() {
		
		Iterable<ImagenesEntity> img = imagenesRepository.findAll();
		
		return img;
		
	}
	
}
