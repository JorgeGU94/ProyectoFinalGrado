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

import com.estudio.dtos.CitasDTO;
import com.estudio.entities.CitasEntity;
import com.estudio.negocio.ICitasService;
import com.estudio.repositories.ICitasRepository;

@RestController
public class CitasControllerRest {

	@Autowired
	private ICitasRepository citasRepository;
	
	@Autowired
	ICitasService citasService;
	
	/********************************* LISTAR CITAS ******************************************/
	
	@GetMapping("/citas")
	public Iterable<CitasEntity> obtenerTodosCitas() {
		
		Iterable<CitasEntity> cita = citasRepository.findAll();
		
		return cita;
		
	}

	@GetMapping(value="/citas", params = {"idCitas","fecha","cliente","tatuador","tatuajes","activo"})
	public List<CitasDTO> obtenerCitasConFiltros(
			@RequestParam (value="idCitas",required=false) Integer idCitas,
			@RequestParam (value="fecha",required=false)String fecha,
			@RequestParam (value="cliente",required=false)Integer cliente,
			@RequestParam (value="tatuador",required=false)Integer tatuador,
			@RequestParam (value="tatuajes",required=false)Integer tatuajes,
			@RequestParam (value="activo",required=false)Integer activo) throws ClassNotFoundException, SQLException{
		
		List<CitasDTO> cita = citasService.buscarCitas(idCitas, fecha, cliente, tatuador, tatuajes, activo);
		
		return cita;
		
	}
	
	//http://localhost:8080/tiendaTattoos/citas
	
	@GetMapping(value="/citas/{idCitas}")
	public ResponseEntity<CitasEntity> obtenerCitasPorId(@PathVariable("idCitas")Integer idCitas){
		
		CitasEntity cita = citasRepository.findById(idCitas).get();
		
		return new ResponseEntity<>(cita, HttpStatus.OK);
		
	}
	
	//http://localhost:8080/tiendaTattoos/citas
	
	
	/********************************* INSERTAR CITAS ******************************************/

	@PostMapping("/citas")
	public ResponseEntity insertarCitas (@RequestBody CitasEntity citas)throws ClassNotFoundException, SQLException, NamingException{
				
		Integer resul = citasService.insertarCitas(citas.getFecha(), citas.getCliente().getIdClientes(), citas.getTatuador().getIdTatuadores(), citas.getTatuajes().getIdTatuajes(), citas.getActivo());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Cita insertada correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Cita no se inserto correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* ACTUALIZAR CITAS ******************************************/

	@PutMapping("/citas")
	public ResponseEntity actualizarCitas (@RequestBody CitasEntity citas)throws ClassNotFoundException, SQLException, NamingException{
		
		Integer resul = citasService.actualizarCitas(citas.getIdCitas(), citas.getFecha(), citas.getCliente().getIdClientes(), citas.getTatuador().getIdTatuadores(), citas.getTatuajes(), citas.getActivo());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Cita actualizada correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Cita no se actualizo correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* BORRAR CITAS ******************************************/
	
	@DeleteMapping("/citas/{id}")
	public ResponseEntity borrarCitas(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, NamingException {
		
		Integer resultado = citasService.borrarCitas(id);
		
		if (resultado >=1) {			
			return new ResponseEntity<>("Cita borrada correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Cita no se borro correctamente", HttpStatus.NOT_MODIFIED);
		}
	}

}
