package com.example.ProyectoFinal_Grupo03;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.repository.ICategoriaRepository;
import com.example.ProyectoFinal_Grupo03.repository.IProductoRepository;
import com.example.ProyectoFinal_Grupo03.repository.IUsuarioRepository;

@DataJpaTest
public class ProductoTest {

	@Autowired
	private IProductoRepository repoProducto;
	
	@Autowired
    private ICategoriaRepository repoCategoria;
	
	@Autowired
    private IUsuarioRepository repoUsuario;

	@Test
	public void testGuardarProducto() {

		Categoria c = new Categoria("cases");
		repoCategoria.save(c);

		Usuario u = new Usuario("Nala", "nalas.coven", "n.vertiz@gmail.com", "Huanchado #123", "La libertad",
				"Trujillo", "Huanchaco", "12345", "930512341", "ADMI", "Nala123");
		
		repoUsuario.save(u);

		Producto p = new Producto("Xiaomi", "Negro", "A_53", 1235.4, 3, u, c);
		repoProducto.save(p);
	}
	
	@Test
	public void testActualizarProducto() {

		Categoria c = new Categoria("cases");
		repoCategoria.save(c);

		Usuario u = new Usuario("Nala", "nalas.coven", "n.vertiz@gmail.com", "Huanchado #123", "La libertad",
				"Trujillo", "Huanchaco", "12345", "930512341", "ADMI", "Nala123");
		repoUsuario.save(u);
		
		Producto p = new Producto(1,"Xiaomi", "Negro", "A_53", 1235.4, 3, u, c);
		repoProducto.save(p);
	}
	
	@Test
	public void testBuscarProducto() {
		testGuardarProducto();
		repoProducto.buscarPorTodo("Xiaomi");
	}
	
	@Test
	public void testEliminarProducto() {
		testGuardarProducto();
		List<Producto> productos = (List<Producto>)repoProducto.findAll();
	    for (Producto producto : productos) {
	        System.out.println(producto.toString());
	    }
		repoProducto.deleteById(1);
	}
	
	@Test
	public void testListarProducto() {
		testGuardarProducto();
		List<Producto> productos = (List<Producto>)repoProducto.findAll();
	    for (Producto producto : productos) {
	        System.out.println(producto.toString());
	    }
	}
	
}
