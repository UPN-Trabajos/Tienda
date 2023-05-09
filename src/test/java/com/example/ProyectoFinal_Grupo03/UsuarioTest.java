package com.example.ProyectoFinal_Grupo03;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ProyectoFinal_Grupo03.model.DetalleOrden;
import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.repository.IDetalleOrdenRepository;
import com.example.ProyectoFinal_Grupo03.repository.IOrdenRepository;
import com.example.ProyectoFinal_Grupo03.repository.IUsuarioRepository;

@DataJpaTest
public class UsuarioTest {
	@Autowired
    private IUsuarioRepository repoUsuario;
	
	@Autowired
    private IOrdenRepository repoOrden;
	
	@Autowired
    private IDetalleOrdenRepository repoDetalle;
	
	@Test
	public void testGuardarUsuario() {
		Usuario u = new Usuario("Nala", "nalas.coven", "n.vertiz@gmail.com", "Huanchado #123", "La libertad",
				"Trujillo", "Huanchaco", "12345","930512341", "USER", "Nala123");
		repoUsuario.save(u);
	}
	
	@Test
	public void testObtenerCompras() {
		testGuardarUsuario();
		Usuario usuario =repoUsuario.findById(1).get();
		repoOrden.findByUsuario(usuario);
	}

	@Test
	public void testDetalleCompra() {
		testGuardarUsuario();
		testObtenerCompras();
		Optional<Orden> orden =repoOrden.findById(1);
//		orden.get().getDetalle();
	}
	
	@Test
	public void testDetalleOrden() {
		testGuardarUsuario();
		testObtenerCompras();
		Optional<Orden> orden =repoOrden.findById(1);
//		orden.get().getDetalle();
	}
}
	
