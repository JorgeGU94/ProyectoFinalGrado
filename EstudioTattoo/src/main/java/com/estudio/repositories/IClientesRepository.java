package com.estudio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudio.dtos.ClientesDTO;
import com.estudio.entities.ClientesEntity;

@Repository
public interface IClientesRepository extends CrudRepository<ClientesEntity, Integer>{

    @Query(value = "SELECT new com.estudio.dtos.ClientesDTO "
         + "(ce.idClientes, ce.nombre, ce.apellidos, ce.telefono, ce.email, ce.password) "
         + "FROM ClientesEntity ce "
         + "WHERE (:id IS NULL OR ce.idClientes = :id) "
         + "AND (:nombre IS NULL OR ce.nombre LIKE CONCAT('%', :nombre, '%')) "
         + "AND (:apellidos IS NULL OR ce.apellidos LIKE CONCAT('%', :apellidos, '%')) "
         + "AND (:telefono IS NULL OR ce.telefono LIKE CONCAT('%', :telefono, '%')) "
         + "AND (:email IS NULL OR ce.email LIKE CONCAT('%', :email, '%')) "
         + "AND (:pass IS NULL OR ce.password LIKE CONCAT('%', :pass, '%'))")
    List<ClientesDTO> buscarClientes(
        @Param("id") Integer id,
        @Param("nombre") String nombre,
        @Param("apellidos") String apellidos,
        @Param("telefono") String telefono,
        @Param("email") String email,
        @Param("pass") String password);
}
