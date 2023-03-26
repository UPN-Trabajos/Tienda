package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.DetalleOrden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenRepository extends CrudRepository<DetalleOrden, Integer>{
    
}
