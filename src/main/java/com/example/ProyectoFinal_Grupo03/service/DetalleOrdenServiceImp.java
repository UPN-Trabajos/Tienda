package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.DetalleOrden;
import com.example.ProyectoFinal_Grupo03.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImp implements IDetalleOrdenService{

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;
    
    @Override
    public void Guardar(DetalleOrden dt) {
        detalleOrdenRepository.save(dt);
    }
    
}
