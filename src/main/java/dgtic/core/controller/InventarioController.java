package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.inventario.InventarioService;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.sucursal.SucursalService;
import dgtic.core.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "libreria/inventario")
public class InventarioController {
    @Autowired
    MessageSource mensaje;
    @Autowired
    InventarioService inventarioService;
    @Autowired
    SucursalService sucursalService;
    @Autowired
    LibroService libroService;

    @GetMapping("")
    public String getInventario(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("libro.titulo").ascending());
        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
        RenderPagina<Inventario> renderPagina = new RenderPagina<>("inventario", pageInventarios);

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

//        modelo.addAttribute("libro", new Libro());
        modelo.addAttribute("inventarioB", new Inventario());
        modelo.addAttribute("sucursales", sucursales);
        modelo.addAttribute("libros", libros);
//        modelo.addAttribute("clasificacionesB", listaClasificaciones);
//        modelo.addAttribute("editorialesB", listaEditoriales);
//        modelo.addAttribute("autorId", "");
//        modelo.addAttribute("clasificacionId", "");
//        modelo.addAttribute("editorialId", "");
        modelo.addAttribute("contenido", "Inventario");
        modelo.addAttribute("listaInventario", pageInventarios);
        modelo.addAttribute("page", renderPagina);
        return "principal/inventario/inventario";
    }

    @GetMapping("buscar-inventario-tabla")
    public String getBuscarEdotiralTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sucursalId", required = false) Integer sucursalId,
            @RequestParam(name = "libroId", required = false) Integer libroId,
            Model model) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("libro.titulo").ascending());
        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
//        RenderPagina<Inventario> renderPagina = new RenderPagina<>("inventario", inventarios);

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

        if (sucursalId != null) {
            pageInventarios = inventarioService.findInventarioBySucursalId(sucursalId, pageable);}
        else if (libroId != null) {
            pageInventarios = inventarioService.findInventarioByLibroId(libroId, pageable);
        }

        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);

        model.addAttribute("inventarioB", new Inventario());
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("libros", libros);
        model.addAttribute("sucursalId", sucursalId);
        model.addAttribute("libroId", libroId);
        model.addAttribute("contenido", "Inventario");
        model.addAttribute("listaInventario", pageInventarios);
        model.addAttribute("page", renderPagina);

        return "principal/inventario/inventario";
    }

    @PostMapping("buscar-inventario-tabla")
    public String buscarEdotiralTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sucursalId", required = false) Integer sucursalId,
            @RequestParam(name = "libroId", required = false) Integer libroId,
            Model model) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("libro.titulo").ascending());
        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
//        RenderPagina<Inventario> renderPagina = new RenderPagina<>("inventario", inventarios);

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

        if (sucursalId != null) {
            pageInventarios = inventarioService.findInventarioBySucursalId(sucursalId, pageable);}
        else if (libroId != null) {
            pageInventarios = inventarioService.findInventarioByLibroId(libroId, pageable);
        }

        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);

        model.addAttribute("inventarioB", new Inventario());
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("libros", libros);
        model.addAttribute("sucursalId", sucursalId);
        model.addAttribute("libroId", libroId);
        model.addAttribute("contenido", "Inventario");
        model.addAttribute("listaInventario", pageInventarios);
        model.addAttribute("page", renderPagina);

        return "principal/inventario/inventario";
    }
}
