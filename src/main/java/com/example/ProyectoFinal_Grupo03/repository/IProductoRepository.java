package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //sirve como repositorio en la BD
                                   //Metodos para el CRUD
public interface IProductoRepository extends CrudRepository<Producto, Integer>{ 
	@Query(value="SELECT * FROM producto "
	        + "INNER JOIN categoria ON producto.id_categoria = categoria.id_categoria "
	        + "WHERE LOWER(producto.nombre) LIKE LOWER(CONCAT('%', ?1, '%')) ",nativeQuery=true)
	List<Producto> buscarPorTodo(String dato);

    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.id_categoria = categoria.id_categoria "
            + "ORDER BY producto.nombre ASC",nativeQuery=true)
    List<Producto> OrderAsc();
    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.id_categoria = categoria.id_categoria "
            + "ORDER BY producto.nombre DESC",nativeQuery=true)
    List<Producto> OrderDesc();
    
    @Query(value="SELECT * FROM producto "
            + "INNER JOIN categoria ON producto.id_categoria = categoria.id_categoria "
            + "WHERE categoria.nombre LIKE %?1%",nativeQuery=true)
    List<Producto> ListarPorCategoria(String dato);
}
