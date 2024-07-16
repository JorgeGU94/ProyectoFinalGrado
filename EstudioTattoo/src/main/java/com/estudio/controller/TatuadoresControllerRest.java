package com.estudio.controller;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudio.dtos.TatuadoresDTO;
import com.estudio.entities.TatuadoresEntity;
import com.estudio.negocio.ITatuadoresService;
import com.estudio.repositories.ITatuadoresRepository;

@RestController
public class TatuadoresControllerRest {

	@Autowired
	private ITatuadoresRepository tatuadoresRepository;
	
	@Autowired
	ITatuadoresService tatuadoresService;
	
	/********************************* LISTAR TATUADORES ******************************************/

	@GetMapping(value="/tatuadores", params = {"idTatuadores","nombre","apellidos","email","especialidad","descripcion","activo"})
	public List<TatuadoresDTO> obtenerTatuadoresConFiltros(
			@RequestParam (value="idTatuajes",required=false) Integer idTatuadores,
			@RequestParam (value="nombre",required=false)String nombre,
			@RequestParam (value="apellidos",required=false)String apellidos,
			@RequestParam (value="email",required=false)String email,
			@RequestParam (value="especialidad",required=false)String especialidad,
			@RequestParam (value="descripcion",required=false)String descripcion,
			@RequestParam (value="activo",required=false)Integer activo){
		
		if ("".equals(nombre)) nombre = null;
        if ("".equals(apellidos)) apellidos = null;
        if ("".equals(email)) email = null;
        if ("".equals(especialidad)) especialidad = null;
        if ("".equals(descripcion)) descripcion = null;
		
		
		List<TatuadoresDTO> tatuado = tatuadoresRepository.buscarTatuadores(idTatuadores, nombre, apellidos, email, especialidad, descripcion, activo);
		
		return tatuado;
		
	}
	
	@GetMapping(value="/tatuadores/{idTatuadores}")
	public ResponseEntity<TatuadoresEntity> obtenerTatuadoresPorId(@PathVariable("idTatuadores")Integer idTatuadores){
		
		TatuadoresEntity tatuado = tatuadoresRepository.findById(idTatuadores).get();
		
		return new ResponseEntity<>(tatuado, HttpStatus.OK);
		
	}
	
	@GetMapping("/tatuadores")
	public Iterable<TatuadoresEntity> obtenerTodosTatuadores() {
		
		Iterable<TatuadoresEntity> tatuado = tatuadoresRepository.findAll();
		
		return tatuado;
		
	}
	
	/********************************* INSERTAR TATUADORES ******************************************/

	@PostMapping("/tatuadores")
	public ResponseEntity insertarTatuadores (@RequestBody TatuadoresEntity tatuadores)throws ClassNotFoundException, SQLException, NamingException{
		
		Integer resul = tatuadoresService.insertarTatuador(tatuadores.getNombre(), tatuadores.getApellidos(), tatuadores.getEmail(), tatuadores.getEspecialidad(), tatuadores.getDescripcion(), tatuadores.getActivo());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Tatuador insertado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Tatuador no se insertado correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* ACTUALIZAR TATUADORES ******************************************/

	@PutMapping("/tatuadores")
	public ResponseEntity actualizarTatuadores (@RequestBody TatuadoresEntity tatuadores)throws ClassNotFoundException, SQLException, NamingException{
		
		Integer resul = tatuadoresService.actualizarTatudor(tatuadores.getIdTatuadores(), tatuadores.getNombre(), tatuadores.getApellidos(), tatuadores.getEmail(), tatuadores.getEspecialidad(), tatuadores.getDescripcion(), tatuadores.getActivo());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Tatuador actualizado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Tatuador no se actualizado correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* BORRAR TATUADORES ******************************************/
	
	@DeleteMapping("/tatuadores/{id}")
	public ResponseEntity borrarTatuadores(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, NamingException {
		
		Integer resultado = tatuadoresService.borrarTatuador(id);
		
		if (resultado >=1) {			
			return new ResponseEntity<>("Tatuador borrado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Tatuador no se borrado correctamente", HttpStatus.NOT_MODIFIED);
		}
	}
	
}
