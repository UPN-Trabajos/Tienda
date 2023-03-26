package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    //  Declaracion de los metodos a usar
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id); //Devuelve una entidad al consultar
    public void Guardar(Usuario p); //Tambien se usa para actualizar
    public void Eliminar (int id);
    public List<Usuario> Buscar(String dato);
    public Optional<Usuario> findByEmail(String email);
    public List<Usuario> ListarTipo(String dato);
}
