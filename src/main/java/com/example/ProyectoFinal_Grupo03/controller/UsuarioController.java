package com.example.ProyectoFinal_Grupo03.controller;

import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Suscripcion;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.service.IOrdenService;
import com.example.ProyectoFinal_Grupo03.service.ISuscripcionService;
import com.example.ProyectoFinal_Grupo03.service.IUsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;
    
    @Autowired
    private ISuscripcionService suscripcionService;

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping("/sign-up")
    public String SignUp() {
        return "usuario/SignUp";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        log.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        
        String nombre = usuario.getNombre();
        String username = usuario.getUsername();
        String email = usuario.getEmail();
        String password = usuario.getPassword();
        String telefono = usuario.getTelefono();
        String direccion = usuario.getDireccion();
        String departamento = usuario.getDepartamento();
        String provincia = usuario.getProvincia();
        String distrito = usuario.getDistrito();
        String zip = usuario.getZip();

        if(nombre!="" &&username!="" && email!=""&&password!=""&&telefono!=""&&direccion!=""&&departamento!=""&&provincia!=""&&distrito!=""&&zip!="") {
        	usuarioService.Guardar(usuario);
        	return "redirect:/";
        }
        	
        else {
        	return "usuario/SignUp";
        }
        
    }
    
    @PostMapping("/saveSuscripcion")
    public String saveSuscripcion(Suscripcion suscripcion) {
        log.info("suscripcion registro: {}", suscripcion);
        String email = suscripcion.getEmail();
        if(email!="") {
        	suscripcionService.Guardar(suscripcion);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/Login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        log.info("Accesos: {}", usuario);
        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        log.info("Usuario de db: {}", user.get());

		if (user.isPresent()) {
			session.setAttribute("id_usuario", user.get().getId());
			if (user.get().getTipo().equals("ADMI")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			log.info("Usuario no existe.");
		}

		return "redirect:/";
	}

	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {

		Usuario usuario = usuarioService.ConsultarId(Integer.parseInt(session.getAttribute("id_usuario").toString()))
				.get();
		List<Orden> ordenes = ordenService.findByUsuario(usuario);

		model.addAttribute("ordenes", ordenes);
		model.addAttribute("sesion", session.getAttribute("id_usuario"));

		return "usuario/compras";
	}

	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, Model model, HttpSession session) {
		log.info("Id de la orden: {}", id);

		Optional<Orden> orden = ordenService.findById(id);

		model.addAttribute("detalles", orden.get().getDetalle());
		model.addAttribute("sesion", session.getAttribute("id_usuario"));

		return "usuario/detalleCompra";
	}

	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("id_usuario");
		return "redirect:/";
	}
}
