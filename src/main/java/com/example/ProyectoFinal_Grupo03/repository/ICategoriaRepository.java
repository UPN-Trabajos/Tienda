package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria,Integer> {
    
    @Query(value="SELECT * FROM categoria "
            + "WHERE nombre LIKE %?1% "
            ,nativeQuery=true)
    List<Categoria> buscarPorTodo(String dato);
    
    @Query(value="SELECT * FROM categoria "
            + "ORDER BY nombre ASC",nativeQuery=true)
    List<Categoria> OrderAsc();
    
    @Query(value="SELECT * FROM categoria "
            + "ORDER BY nombre DESC",nativeQuery=true)
    List<Categoria> OrderDesc();
}
