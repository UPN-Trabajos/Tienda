package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return usuarioRepository.findById(id);
    }
    
    @Override
    public void Guardar(Usuario u) {
        usuarioRepository.save(u);
    }

    @Override
    public void Eliminar(int id) {
      usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> Buscar(String dato) {
        return usuarioRepository.buscarPorTodo(dato);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> ListarTipo(String dato) {
        return usuarioRepository.listarTipo(dato);
    }
}
