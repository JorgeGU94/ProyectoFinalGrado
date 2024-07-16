package com.estudio.controller;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudio.dtos.ColorTamanoDTO;
import com.estudio.dtos.TatuajesDTO;
import com.estudio.entities.TatuajesEntity;
import com.estudio.negocio.ITatuajesService;
import com.estudio.repositories.ITatuajesRepository;

@RestController
public class TatuajesControllerRest {

	@Autowired
	private ITatuajesRepository tatuajesRepository;
	
	@Autowired
	ITatuajesService tatuajesService;
	
	/********************************* LISTAR TATUAJES ******************************************/
	
	@GetMapping(value = "/tatuajes", params = {"idTatuajes", "descripcion", "color", "tamano", "precio"})
	public List<TatuajesDTO> obtenerTatuajesConFiltros(
			@RequestParam (value="idTatuajes",required=false)Integer idTatuajes,
			@RequestParam (value="descripcion",required=false)String descripcion,
			@RequestParam (value="color",required=false)String color,
			@RequestParam (value="tamano",required=false)double tamano,
			@RequestParam (value="precio",required=false)double precio) {
		
		if ("".equals(descripcion)) descripcion = null;
        if ("".equals(color)) color = null;
		
		List<TatuajesDTO> tatu = tatuajesRepository.buscarTatuajes(idTatuajes, descripcion, color, tamano, precio);
		
		return tatu;
	}
	
	//http://localhost:8080/tiendaTattoos/tatuajes/2
	
	@GetMapping(value="/tatuajes/{idTatuajes}")
	public ResponseEntity<TatuajesEntity> obtenerTatuajesPorId(@PathVariable("idTatuajes")Integer idTatuajes){
		
		TatuajesEntity tatu = tatuajesRepository.findById(idTatuajes).get();
		
		return new ResponseEntity<>(tatu, HttpStatus.OK);
		
	}
	
	//http://localhost:8080/tiendaTattoos/tatuajes
	
	@GetMapping(value="/tatuajes")
	public Iterable<TatuajesEntity> obtenerTodosTatuajes(){
		
		Iterable<TatuajesEntity> tatu = tatuajesRepository.findAll();
		
		return tatu;
		
	}
	
	/********************************* INSERTAR TATUAJES ******************************************/

	@PostMapping("/tatuajes")
	public ResponseEntity insertarTatuaje(@RequestBody TatuajesEntity tatuajes)throws ClassNotFoundException, SQLException, NamingException {
		
		Integer resul = tatuajesService.insertarTatuajes(tatuajes.getDescripcion(), tatuajes.getColor().toString(), tatuajes.getTamano(), tatuajes.getPrecio());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Tatuaje insertado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Tatuaje no se inserto correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	/********************************* ACTUALIZAR TATUAJES ******************************************/

	@PutMapping("/tatuajes")
	public ResponseEntity actualizarTatuaje(@RequestBody TatuajesEntity tatuajes)throws ClassNotFoundException, SQLException, NamingException {
		
		Integer resul = tatuajesService.actualizarTatuajes(tatuajes.getIdTatuajes(), tatuajes.getDescripcion(), tatuajes.getColor().toString(), tatuajes.getTamano(), tatuajes.getPrecio());

		if (resul >=1) {			
			return new ResponseEntity<>("Tatuaje actualizado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Tatuaje no se actualizado correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* 	CALCULAR PRECIO ******************************************/
	
	@PostMapping("/tatuajes/calcularPrecio")
	public @ResponseBody Double calcularPrecio(ModelMap model, @RequestBody ColorTamanoDTO colorTamano)throws ClassNotFoundException, SQLException {
		Integer color = colorTamano.getColor();
        double tamano = colorTamano.getTamano();

        double precio = tatuajesService.calcularPrecio(color, tamano);
        return precio;
        
	}
	
	
	
}
