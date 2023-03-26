package com.example.ProyectoFinal_Grupo03.controller;

import com.example.ProyectoFinal_Grupo03.model.Categoria;
import com.example.ProyectoFinal_Grupo03.service.ICategoriaService;
import java.util.List;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(CategoriaController.class);
    String carpeta="categoria/";
    @Autowired
    private ICategoriaService categoriaService;
    
    @GetMapping("")
    public String ListaCategorias(Model model) {
        List<Categoria> categorias = categoriaService.Listar();
        model.addAttribute("categorias", categorias);
        return "categoria/ListaCategorias";
    }
    
    @PostMapping("/registrar")
    public String Registrar(@RequestParam("nom") String nom,
                            Model model)
    {
        Categoria c = new Categoria();
        c.setNombre(nom);
        
        categoriaService.Guardar(c);
        
        return ListaCategorias(model);
    }
    
    @PostMapping("/buscarCategoria")
    public String Buscar(@RequestParam("dato") String dato, Model model)
    {
        List<Categoria> categorias = categoriaService.Buscar(dato);
        model.addAttribute("categorias", categorias);
        return carpeta+"ListaCategorias";
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
                            @RequestParam("nombre") String nom,
                            Model model)
    {
        Categoria c = new Categoria();
        c.setId(id);
        c.setNombre(nom);
        
        categoriaService.Guardar(c);
        
        return ListaCategorias(model);
    }
    
    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model)
    {
        categoriaService.Eliminar(id);
        return "redirect:/categoria";
    }
    
    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model)
    {
        List<Categoria> categorias = categoriaService.OrdenAscendente();
        model.addAttribute("categorias", categorias);
        return carpeta+"listacategorias"; //listacategorias.html
    }
    
    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model)
    {
        List<Categoria> categorias = categoriaService.OrdenDescendente();
        model.addAttribute("categorias", categorias);
        return carpeta+"listacategorias"; //listacategorias.html
    }
}
