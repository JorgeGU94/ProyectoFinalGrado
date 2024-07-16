package com.estudio.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ITatuadoresDAO;
import com.estudio.dtos.TatuadoresDTO;
import com.estudio.entities.TatuadoresEntity;
import com.estudio.repositories.ITatuadoresRepository;

@Component
public class TatuadoresDAOImpl implements ITatuadoresDAO {

	@Autowired
	private ITatuadoresRepository tatuadoresRepository;
	
	@Override
	public List<TatuadoresDTO> buscarTatuadores(Integer id, String nombre, String apellidos, String email,
			String especialidad, String descripcion, Integer activo) throws ClassNotFoundException, SQLException {
		
		return tatuadoresRepository.buscarTatuadores(id, nombre, apellidos, email, especialidad, descripcion, activo);
		
	}

	@Override
	public Integer insertarTatuador(String nombre, String apellidos, String email, String especialidad, String descripcion, Integer activo)
			throws ClassNotFoundException, SQLException {

		TatuadoresEntity tae = new TatuadoresEntity(nombre, apellidos, email, especialidad, descripcion, activo);
		tatuadoresRepository.save(tae);
		
		return tae.getIdTatuadores();
		
	}

	@Override
	public Integer actualizarTatudor(Integer id, String nombre, String apellidos, String email, String especialidad, String descripcion, 
			Integer activo) throws ClassNotFoundException, SQLException {

		TatuadoresEntity tae = new TatuadoresEntity(id, nombre, apellidos, email, especialidad, descripcion, activo);
		tatuadoresRepository.save(tae);

		return tae.getIdTatuadores();
	}

	@Override
	public Integer borrarTatuador(Integer id) throws ClassNotFoundException, SQLException {
		
		TatuadoresEntity tae = tatuadoresRepository.findById(id).get();
		tae.setActivo(0);
		
		tatuadoresRepository.save(tae);
				
		return tae.getIdTatuadores();
	}

}
