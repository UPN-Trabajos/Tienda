package com.example.ProyectoFinal_Grupo03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal_Grupo03.model.Suscripcion;
import com.example.ProyectoFinal_Grupo03.repository.ISuscripcionRepository;


@Service
public class SuscripcionServiceImpl implements ISuscripcionService{
	@Autowired
    private ISuscripcionRepository suscripcionRepository;

	@Override
	public void Guardar(Suscripcion suscripcion) {
		suscripcionRepository.save(suscripcion);
	}
}
