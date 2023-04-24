package com.example.ProyectoFinal_Grupo03.repository;

import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends CrudRepository<Orden, Integer>{
    List<Orden> findByUsuario(Usuario usuario);
    
    @Query(value="SELECT sum(total) FROM Orden "
            ,nativeQuery=true)
    float montoTotal();
    
    @Query(value="SELECT count(id_orden) FROM Orden "
            ,nativeQuery=true)
    int cantidadOrdenes();
    
    @Query(value="SELECT * FROM Orden "
            + "WHERE numero LIKE %?1% "
            ,nativeQuery=true)
    List<Orden> findByDate(String dato);
    
    @Query(value="SELECT * FROM Orden "
            + "ORDER BY fecha_creacion ASC",nativeQuery=true)
    List<Orden> OrderAsc();
    
    @Query(value="SELECT * FROM Orden "
            + "ORDER BY fecha_creacion DESC",nativeQuery=true)
    List<Orden> OrderDesc();
    
    @Query(value="SELECT count(*) FROM Orden "
            + "INNER JOIN detalle_orden ON Orden.id_orden = detalle_orden.id_orden "
            + "INNER JOIN Producto ON Producto.id_producto = detalle_orden.id_producto "
            + "WHERE detalle_orden.id_producto = ?1",nativeQuery=true)
    int VentasPorProducto(Integer id);
    
    @Query(value="SELECT count(*) FROM orden "
            + "INNER JOIN usuario ON orden.id_usuario=usuario.id_usuario "
            + "WHERE orden.id_usuario= ?1 ",nativeQuery=true)
    int VentasPorCliente(Integer id);
}
