package com.estudio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudio.dtos.CitasDTO;
import com.estudio.entities.CitasEntity;

@Repository
public interface ICitasRepository extends CrudRepository<CitasEntity, Integer>{
	
	@Query(value = "SELECT new com.estudio.dtos.CitasDTO "
            + "(ce.idCitas, ce.fecha, cle.idClientes, te.idTatuadores, tae.idTatuajes, ce.activo) "
            + "FROM CitasEntity ce "
            + "JOIN ce.cliente cle "
            + "JOIN ce.tatuador te "
            + "JOIN ce.tatuajes tae "
            + "WHERE (:id IS NULL OR ce.idCitas = :id) "
            + "AND (:fecha IS NULL OR ce.fecha LIKE CONCAT('%', :fecha, '%')) "
            + "AND (:cliente IS NULL OR cle.idClientes = :cliente) "
            + "AND (:tatuador IS NULL OR te.idTatuadores = :tatuador) "
            + "AND (:tatuajes IS NULL OR tae.idTatuajes = :tatuajes) "
            + "AND (:activo IS NULL OR ce.activo = :activo)")
	public List<CitasDTO> buscarCitas(
		@Param("id") Integer id,
		@Param("fecha") String fecha,
		@Param("cliente") Integer cliente,
		@Param("tatuador") Integer tatuador,
		@Param("tatuajes") Integer tatuajes,
		@Param("activo") Integer activo);
	
}
