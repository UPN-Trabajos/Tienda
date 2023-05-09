package com.example.ProyectoFinal_Grupo03.service;

import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.repository.IProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Para poder implementar los metodos definidos en la interfaz
public class ProductoServiceImpl implements IProductoService{

    @Autowired //Permite declarar variable tipo interfaz
    private IProductoRepository data; //Mismo nombre del repositorio, permite acceder a la conexion con bd
    
    @Override
    public List<Producto> Listar() {
         return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Producto> OrdenAscendente() {
        return data.OrderAsc();
    }

    @Override
    public List<Producto> OrdenDescendente() {
        return data.OrderDesc();
    }

    @Override
    public List<Producto> ListarPorCategoria(String dato) {
        return data.ListarPorCategoria(dato);
    }

	@Override
	public List<Producto> BuscarCelulares(String dato) {
		 return data.buscarCelulares(dato);
	}

	@Override
	public List<Producto> BuscarCases(String dato) {
		 return data.buscarCases(dato);
	}
}
