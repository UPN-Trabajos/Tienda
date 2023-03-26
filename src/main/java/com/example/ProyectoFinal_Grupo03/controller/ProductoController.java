package com.example.ProyectoFinal_Grupo03.controller;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import com.example.ProyectoFinal_Grupo03.model.Producto;
import com.example.ProyectoFinal_Grupo03.model.Usuario;
import com.example.ProyectoFinal_Grupo03.service.ICategoriaService;
import com.example.ProyectoFinal_Grupo03.service.IProductoService;
import com.example.ProyectoFinal_Grupo03.service.IUsuarioService;
import com.example.ProyectoFinal_Grupo03.service.UploadFileService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired //Permite declarar variable tipo interfaz
    private IProductoService productoService; //Permite acceder a los metodos
    @Autowired
    private ICategoriaService serviceCat;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String ListaProductos(Model model) {
        List<Producto> productos = productoService.Listar();
        model.addAttribute("productos", productos);

        List<Categoria> categorias = serviceCat.Listar();
        model.addAttribute("categorias", categorias);

        return "productos/ListaProductos";
    }

    @PostMapping("/guardarProducto")
    public String GuargarProductos(@RequestParam("nom") String nom,
            @RequestParam("desc") String desc,
            @RequestParam("cant") int cant,
            @RequestParam("prec") float prec,
            @RequestParam("cat") Categoria cat,
            @RequestParam("img") MultipartFile file,
            Model model,
            HttpSession session) throws IOException {
        Producto p = new Producto();
        p.setNombre(nom);
        p.setDescripcion(desc);
        p.setCantidad(cant);
        p.setPrecio(prec);
        p.setCategoria(cat);

        Usuario u = usuarioService.ConsultarId(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        p.setUsuario(u);
        LOGGER.info("Producto guardado: {}", p);

        //Imagen
        if (p.getId() == null) { //cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            p.setImagen(nombreImagen);
        }

        productoService.Guardar(p);
        return "redirect:/productos";
    }

    @PostMapping("/actualizarProducto")
    public String Actualizar(@RequestParam("id") int id,
            @RequestParam("nombre") String nom,
            @RequestParam("descripcion") String desc,
            @RequestParam("cantidad") int cant,
            @RequestParam("precio") float prec,
            @RequestParam("cat") Categoria cat,
            @RequestParam("img") MultipartFile file,
            Model model) throws IOException {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nom);
        p.setDescripcion(desc);
        p.setCantidad(cant);
        p.setPrecio(prec);
        p.setCategoria(cat);

        Producto pr = new Producto();
        pr = productoService.ConsultarId(p.getId()).get();
        System.out.print(pr);
        if (file.isEmpty()) {//se edita el producto, pero no se cambia la imagen
            p.setImagen(p.getImagen());
        } else { //cuando se edita la imagen
            //eliminar cuando no sea la imagen por defecto
            if (!pr.getImagen().equals("default.jpg")) {
                upload.deleteImage(pr.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            p.setImagen(nombreImagen);
        }
        p.setUsuario(p.getUsuario());

        productoService.Guardar(p);

        return ListaProductos(model);
    }
    
    @PostMapping("/buscarProducto")
    public String Buscar(@RequestParam("dato") String dato, Model model)
    {
        List<Producto> productos = productoService.Buscar(dato);
        model.addAttribute("productos", productos);
        
        List<Categoria> categorias = serviceCat.Listar();
        model.addAttribute("categorias", categorias);
        
        return "productos/ListaProductos";
    }

    @GetMapping("/eliminarProducto")
    public String Eliminar(@RequestParam("id") int id, Model model) {
        Producto pr = new Producto();
        pr = productoService.ConsultarId(id).get();

        //eliminar cuando no sea la imagen por defecto
        if (!pr.getImagen().equals("default.jpg")) {
            upload.deleteImage(pr.getImagen());
        }
        LOGGER.info("Producto a eliminar: {}", pr);
        productoService.Eliminar(id);
        return "redirect:/productos";
    }
    
    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model)
    {
        List<Producto> productos = productoService.OrdenAscendente();
        model.addAttribute("productos", productos);
        
        List<Categoria> categorias = serviceCat.Listar();
        model.addAttribute("categorias", categorias);
        
        return "productos/ListaProductos";
    }
    
    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model)
    {
        List<Producto> productos = productoService.OrdenDescendente();
        model.addAttribute("productos", productos);
        
        List<Categoria> categorias = serviceCat.Listar();
        model.addAttribute("categorias", categorias);
        
        return "productos/ListaProductos";
    }
}
