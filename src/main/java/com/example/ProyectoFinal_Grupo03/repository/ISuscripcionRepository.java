package com.example.ProyectoFinal_Grupo03.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal_Grupo03.model.Suscripcion;

@Repository
public interface ISuscripcionRepository extends CrudRepository<Suscripcion, Integer>{

}
