package com.example.ProyectoFinal_Grupo03;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import com.example.ProyectoFinal_Grupo03.repository.ICategoriaRepository;
import com.example.ProyectoFinal_Grupo03.service.ICategoriaService;

@DataJpaTest
public class CategoriaTest {

	@Autowired
    private ICategoriaRepository repo;
	
	@Test
	public void testGuardarCategoria() {
		Categoria c = new Categoria("cases");
        repo.save(c);
	}
	
	@Test
	public void testBuscarCategoria() {
		repo.buscarPorTodo("Cel");
	}
	
	public void testBuscarCategoriaPorID() {
		testGuardarCategoria();
		repo.findById(1);
	}
	
	@Test
	public void testActualizarCategoria() {
		testGuardarCategoria();
		Categoria c = new Categoria(1, "Case para Celulares");
		repo.save(c);
	}
	
	@Test
	public void testEliminarCategoria() {
		testGuardarCategoria();
		repo.deleteById(1);
	}
	
	@Test
	public void testListarCategoria() {
		testGuardarCategoria();
		repo.findAll();
	}


}
