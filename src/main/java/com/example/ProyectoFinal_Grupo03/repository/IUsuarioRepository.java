package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer>{
    //Aqui se pueden crear otros metodos
    @Query(value="SELECT * FROM usuario "
            + "WHERE usuario.nombre LIKE %?1% "
            + "OR username LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1% "
            + "OR telefono LIKE %?1% "
            + "OR tipo LIKE %?1%",nativeQuery=true)
    List<Usuario> buscarPorTodo(String dato);
    
    Optional<Usuario> findByEmail(String email);
    
    @Query(value="SELECT * FROM usuario "
            + "WHERE tipo =?1",nativeQuery=true)
    List<Usuario> listarTipo(String dato);
}
