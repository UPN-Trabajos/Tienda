package com.example.ProyectoFinal_Grupo03.controller;

import com.example.ProyectoFinal_Grupo03.model.DetalleOrden;
import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.service.IDetalleOrdenService;
import com.example.ProyectoFinal_Grupo03.service.IOrdenService;
import com.example.ProyectoFinal_Grupo03.service.IProductoService;
import com.example.ProyectoFinal_Grupo03.service.IUsuarioService;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

    @GetMapping("")
    public String Home(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));
        
        //session
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        return "usuario/UsuarioHome";
    }

    @GetMapping("/celulares")
    public String celulares(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));
        List<Producto> productos = productoService.ListarPorCategoria("Celular");
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        model.addAttribute("productos", productos);
        return "usuario/celulares";
    }
    
    @GetMapping("/cases")
    public String cases(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));
        List<Producto> productos = productoService.ListarPorCategoria("Cases");
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        model.addAttribute("productos", productos);
        return "usuario/celulares";
    }

    @GetMapping("/productoHome/{id}")
    public String productoHome(@PathVariable int id, Model model, HttpSession session) {
        log.info("ID producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.ConsultarId(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        return "usuario/productoHome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam("id") int id, @RequestParam("cantidad") int cant, Model model, HttpSession session) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        
        log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));

        Optional<Producto> optionalProducto = productoService.ConsultarId(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cant);

        producto = optionalProducto.get();
        detalleOrden.setCantidad(cant);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(producto.getPrecio() * cant);
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setProducto(producto);

        //validar que no se añada 2 veces
        int idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);
        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); //suma todos los detalles

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        model.addAttribute("sesion",session.getAttribute("id_usuario"));

        return "usuario/carrito";
    }

    @PostMapping("/delete/cart")
    public String deleteProducto(@RequestParam("id") int id, Model model, HttpSession session) {

        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        log.info("id: {}", id);
        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) { //Anade a una lista auxiliar todo menos el eliminado
                ordenesNueva.add(detalleOrden);
            }
        }
        detalles = ordenesNueva; //poner la nueva lista

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); //suma todos los detalles

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        model.addAttribute("sesion",session.getAttribute("id_usuario"));

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
    	log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));
    	
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        //session
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
    	log.info("Sesion del usuario: {}", session.getAttribute("id_usuario"));
        Usuario usuario = usuarioService.ConsultarId(Integer.parseInt(session.getAttribute("id_usuario").toString())).get();
        log.info("usuario orden:" + usuario.toString());
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        model.addAttribute("usuario", usuario);
        
        return "usuario/resumenOrden";
    }

    @GetMapping("/saveOrder")
    public String SaveOrder(HttpSession session) {
    	log.info("Sesion del usuario saveOrder: {}", session.getAttribute("id_usuario"));
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        Usuario usuario = usuarioService.ConsultarId(Integer.parseInt(session.getAttribute("id_usuario").toString())).get();
        orden.setUsuario(usuario);
        log.info("setUsuario saveOrder: {}", orden.getUsuario().getId());

        ordenService.Guardar(orden);

//        Guardar Detalles
        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden);
            detalleOrdenService.Guardar(dt);
        }

        //limpiar lista y orden
        orden = new Orden();
        detalles.clear();
        
        return "redirect:/";
    }
    
    @PostMapping("/search")
    public String SeachProduct(@RequestParam("dato") String dato,Model model, HttpSession session){
        log.info("Nombre del producto: {}", dato);
        
        List<Producto> productos = productoService.Buscar(dato);
        model.addAttribute("productos", productos);
        model.addAttribute("sesion",session.getAttribute("id_usuario"));
        
        return "usuario/celulares";
    }
}
