package dgtic.core.controller;

import dgtic.core.model.Autor;
import dgtic.core.model.Editorial;
import dgtic.core.model.Libro;
import dgtic.core.model.Nacionalidad;
import dgtic.core.model.dto.EditorialDto;
import dgtic.core.model.dto.LibroDto;
import dgtic.core.service.libro.LibroService;
import dgtic.core.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping(value = "libreria/gestionar/libro")
public class ManagerLibroController {
    @Autowired
    MessageSource mensaje;
    @Autowired
    LibroService libroService;

    @GetMapping("")
    public String getGestionar(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Libro> libros = libroService.findPage(pageable);
        RenderPagina<Libro> renderPagina = new RenderPagina<>("libro", libros);

//        modelo.addAttribute("libro", new Libro());
        modelo.addAttribute("libroB", new Libro());
        modelo.addAttribute("contenido", "Gestionar Libros");
        modelo.addAttribute("listaLibros", libros);
        modelo.addAttribute("page", renderPagina);
        return "principal/libro/gestionLibro";
    }



    @GetMapping("delete-libro/{id}")
    public String eliminarLibro(@PathVariable("id") Integer id,
                                RedirectAttributes modelo) {
        libroService.deleteById(id);

        return "redirect:/libreria/gestionar/libro";
    }

    @GetMapping(value = "buscar-libro-nombre/{dato}", produces = "application/json")
    public @ResponseBody List<LibroDto> findLibro(@PathVariable String dato) {
        return libroService.findLibroView(dato);
    }

    @GetMapping("buscar-libro-tabla")
    public String getBuscarLibroTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String titulo,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Libro> pageLibros = libroService.findLibroByTitulo(titulo, pageable);
        RenderPagina<Libro> renderPagina = new RenderPagina<>("/libreria/gestionar/libro/buscar-libro-tabla", pageLibros);

        Libro libroBusqueda = new Libro();
        libroBusqueda.setTitulo(titulo);

//        model.addAttribute("libro", new Libro());
        model.addAttribute("libroB", libroBusqueda);
        model.addAttribute("contenido", "Gestionar Libros");
        model.addAttribute("listaLibros", pageLibros);
        model.addAttribute("page", renderPagina);
        model.addAttribute("datoAbuscar", titulo);

        return "principal/libro/gestionLibro";
    }

    @PostMapping("buscar-libro-tabla")
    public String buscarEdotiralTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String titulo,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Libro> pageLibros = libroService.findLibroByTitulo(titulo, pageable);
        RenderPagina<Libro> renderPagina = new RenderPagina<>("/libreria/gestionar/libro/buscar-libro-tabla", pageLibros);

        Libro libroBusqueda = new Libro();
        libroBusqueda.setTitulo(titulo);

//        model.addAttribute("editorial", new Editorial());
        model.addAttribute("libroB", libroBusqueda);
        model.addAttribute("contenido", "Gestionar Libros");
        model.addAttribute("listaLibros", pageLibros);
        model.addAttribute("page", renderPagina);
        model.addAttribute("datoAbuscar", titulo);

        return "principal/libro/gestionEditorial";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.libroDuplicado",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
