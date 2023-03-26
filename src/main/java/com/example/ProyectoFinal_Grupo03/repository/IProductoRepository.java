package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //sirve como repositorio en la BD
                                   //Metodos para el CRUD
public interface IProductoRepository extends CrudRepository<Producto, Integer>{ 
    //Aqui se pueden crear otros metodos
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.categoria_id = categoria.id "
            + "WHERE producto.nombre LIKE %?1% "
            + "OR descripcion LIKE %?1% "
            + "OR cantidad LIKE %?1% "
            + "OR precio LIKE %?1% "
            + "OR categoria.nombre LIKE %?1%",nativeQuery=true)
    List<Producto> buscarPorTodo(String dato);
    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.categoria_id = categoria.id "
            + "ORDER BY producto.nombre ASC",nativeQuery=true)
    List<Producto> OrderAsc();
    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.categoria_id = categoria.id "
            + "ORDER BY producto.nombre DESC",nativeQuery=true)
    List<Producto> OrderDesc();
    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.categoria_id = categoria.id "
            + "WHERE categoria.nombre LIKE %?1%",nativeQuery=true)
    List<Producto> ListarPorCategoria(String dato);
}
