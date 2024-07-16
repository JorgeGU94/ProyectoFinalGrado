package com.estudio.controller;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudio.dtos.ClientesDTO;
import com.estudio.entities.ClientesEntity;
import com.estudio.negocio.IClientesService;
import com.estudio.repositories.IClientesRepository;

@RestController
public class ClientesControllerRest {

	@Autowired
	private IClientesRepository clientesRepository;
	
	@Autowired
	IClientesService clientesService;
	
	/********************************* LISTAR CLIENTES ******************************************/

	 @GetMapping(value = "/clientes", params = {"id", "nombre", "apellidos", "telefono", "email", "pass"})
	    public List<ClientesDTO> obtenerClientesConFiltros(
	            @RequestParam(value = "id", required = false) Integer id,
	            @RequestParam(value = "nombre", required = false) String nombre,
	            @RequestParam(value = "apellidos", required = false) String apellidos,
	            @RequestParam(value = "telefono", required = false) String telefono,
	            @RequestParam(value = "email", required = false) String email,
	            @RequestParam(value = "pass", required = false) String password) {

	        if ("".equals(nombre)) nombre = null;
	        if ("".equals(apellidos)) apellidos = null;
	        if ("".equals(telefono)) telefono = null;
	        if ("".equals(email)) email = null;
	        if ("".equals(password)) password = null;

	        return clientesRepository.buscarClientes(id, nombre, apellidos, telefono, email, password);
	    }
	
	//http://localhost:8080/tiendaTattoos/clientes/2
	
	@GetMapping(value="/clientes/{idClientes}")
	public ResponseEntity<ClientesEntity> obtenerClientesPorId(@PathVariable("idClientes")Integer idClientes){
		
		ClientesEntity cliente = clientesRepository.findById(idClientes).get();
		
		return new ResponseEntity<>(cliente, HttpStatus.OK);
		
	}
	
	//http://localhost:8080/tiendaTattoos/clientes
	
	@GetMapping("/clientes")
	public Iterable<ClientesEntity> obtenerTodosClientes() {
		
		Iterable<ClientesEntity> cliente = clientesRepository.findAll();

		return cliente;
		
	}
	
	/********************************* INSERTAR CLIENTES ******************************************/

	@PostMapping("/clientes")
	public ResponseEntity insertarClientes (@RequestBody ClientesEntity clientes)throws ClassNotFoundException, SQLException, NamingException{
		
		Integer resul = clientesService.insertarClientes(clientes.getNombre(), clientes.getApellidos(), clientes.getTelefono(), clientes.getEmail(), clientes.getPassword());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Cliente insertado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Cliente no se insertado correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	/********************************* ACTUALIZAR TATUADORES ******************************************/

	@PutMapping("/clientes")
	public ResponseEntity actualizarClientes (@RequestBody ClientesEntity clientes)throws ClassNotFoundException, SQLException, NamingException{
		
		Integer resul = clientesService.actualizarClientes(clientes.getIdClientes(), clientes.getNombre(), clientes.getApellidos(), clientes.getTelefono(), clientes.getEmail(), clientes.getPassword());
		
		if (resul >=1) {			
			return new ResponseEntity<>("Cliente actualizado correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Cliente no se actualizado correctamente", HttpStatus.NOT_IMPLEMENTED);
		}
	}

}
