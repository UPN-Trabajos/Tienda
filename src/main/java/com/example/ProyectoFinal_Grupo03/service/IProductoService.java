package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
//  Declaracion de los metodos a usar
    public List<Producto> Listar();
    public Optional<Producto> ConsultarId(int id); //Devuelve una entidad al consultar
    public List<Producto> ListarPorCategoria(String dato);
    public void Guardar(Producto p); //Tambien se usa para actualizar
    public void Eliminar (int id);
    public List<Producto> Buscar(String dato);
    public List<Producto> OrdenAscendente();
    public List<Producto> OrdenDescendente();
}
