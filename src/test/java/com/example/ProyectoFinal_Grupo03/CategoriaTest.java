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
		List<Categoria> categorias = repo.buscarPorTodo("Cel");
	}

}
