package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    
    public List<Categoria> Listar();
    public Optional<Categoria> ConsultarId(int id);
    public void Guardar(Categoria p);
    public void Eliminar(int id);
    public List<Categoria> Buscar(String dato);
    public List<Categoria> OrdenAscendente();
    public List<Categoria> OrdenDescendente();
    
}
