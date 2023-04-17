package com.example.ProyectoFinal_Grupo03.controller;

import com.example.ProyectoFinal_Grupo03.model.Orden;
import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.model.Reporte;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.service.IOrdenService;
import com.example.ProyectoFinal_Grupo03.service.IProductoService;
import com.example.ProyectoFinal_Grupo03.service.IUsuarioService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
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
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired //Permite declarar variable tipo interfaz
    private IProductoService productoService; //Permite acceder a los metodos

    @Autowired //Permite declarar variable tipo interfaz
    private IUsuarioService usuarioService; //Permite acceder a los metodos

    @Autowired //Permite declarar variable tipo interfaz
    private IOrdenService ordenService; //Permite acceder a los metodos

    private Logger log = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.Listar();
//        System.out.print(productos);
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    @PostMapping("/buscarProductos")
    public String SeachProduct(@RequestParam("dato") String dato, Model model) {
        List<Producto> productos = productoService.Buscar(dato);
        model.addAttribute("productos", productos);

        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.Listar());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.FindAll());

        Reporte r = new Reporte();

        List<Orden> ordenes = ordenService.FindAll();
        if (ordenes.size() > 0) {
            float m = ordenService.montoTotal();
            r.setCantidadTotal(ordenService.cantidadOrdenes());
            r.setMontoTotal(m);
        }

        model.addAttribute("reporte", r);

        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id, HttpSession session) {
        log.info("id de la orden: {}", id);

        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());

        //session
        model.addAttribute("session", session.getAttribute("id_usuario"));

        return "administrador/detalleOrden";
    }

    @PostMapping("/buscarOrdenes")
    public String SeachDate(@RequestParam("dato") String dato, Model model) {
        List<Orden> ordenes = ordenService.findByDate(dato);
        model.addAttribute("ordenes", ordenes);

        Reporte r = new Reporte();
        float m = ordenService.montoTotal();
        r.setCantidadTotal(ordenService.cantidadOrdenes());
        r.setMontoTotal(m);
        model.addAttribute("reporte", r);

        return "administrador/ordenes";
    }

    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model) {
        List<Orden> ordenes = ordenService.OrderAsc();
        model.addAttribute("ordenes", ordenes);

        Reporte r = new Reporte();
        float m = ordenService.montoTotal();
        r.setCantidadTotal(ordenService.cantidadOrdenes());
        r.setMontoTotal(m);
        model.addAttribute("reporte", r);

        return "administrador/ordenes";
    }

    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model) {
        List<Orden> ordenes = ordenService.OrderDesc();
        model.addAttribute("ordenes", ordenes);

        Reporte r = new Reporte();
        float m = ordenService.montoTotal();
        r.setCantidadTotal(ordenService.cantidadOrdenes());
        r.setMontoTotal(m);
        model.addAttribute("reporte", r);

        return "administrador/ordenes";
    }

    @GetMapping("/reporte")
    public String ReporteGrafico(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();

        List<Producto> productos = productoService.Listar();
        for (int i = 0; i < productos.size(); i++) {
            int id = productos.get(i).getId();
            String nom = productos.get(i).getNombre();
            int cant = ordenService.VentasPorProducto(id);
            graphData.put(nom, cant);
        }
        model.addAttribute("graphData", graphData);

        Map<String, Integer> graphData2 = new TreeMap<>();

        List<Usuario> usuarios = usuarioService.ListarTipo("USER");
        for (int i = 0; i < usuarios.size(); i++) {
            int id = usuarios.get(i).getId();
            String nom = usuarios.get(i).getNombre();
            int cant = ordenService.VentasPorCliente(id);
            graphData2.put(nom, cant);
        }
        System.out.println(graphData2);

        model.addAttribute("graphData2", graphData2);

        return "administrador/reporte"; //grafico.html
    }
}
