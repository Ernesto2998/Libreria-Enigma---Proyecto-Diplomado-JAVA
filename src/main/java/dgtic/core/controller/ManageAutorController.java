package dgtic.core.controller;

import dgtic.core.model.Autor;
import dgtic.core.model.Nacionalidad;
import dgtic.core.model.dto.AutorDto;
import dgtic.core.service.autor.AutorService;
import dgtic.core.service.nacionalidad.NacionalidadService;
import dgtic.core.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "libreria/gestionar/autor")
public class ManageAutorController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    AutorService autorService;
    @Autowired
    NacionalidadService nacionalidadService;

    @GetMapping("")
    public String getGestionar(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Autor> autores = autorService.findPage(pageable);
        RenderPagina<Autor> renderPagina = new RenderPagina<>("autor", autores);
        List<Nacionalidad> nacionalidades = nacionalidadService.findAll();


        modelo.addAttribute("autor", new Autor());
        modelo.addAttribute("autorB", new Autor());
        modelo.addAttribute("nacionalidad", nacionalidades);
        modelo.addAttribute("contenido", "Gestionar Autores");
        modelo.addAttribute("listaAutores", autores);
        modelo.addAttribute("page", renderPagina);
        return "principal/autor/gestionAutor";
    }

    @PostMapping("add-autor")
    public String addAutor(@RequestParam(name = "page", defaultValue = "0") int page,
                               @Valid Autor autor,
//                                   RedirectAttributes redirectAttributes,
                               BindingResult bindingResult,
                               Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Autor> autores = autorService.findPage(pageable);
        RenderPagina<Autor> renderPagina = new RenderPagina<>("/libreria/gestionar/autor/buscar-autor-tabla", autores);
        List<Nacionalidad> nacionalidades = nacionalidadService.findAll();

        model.addAttribute("contenido", "Gestionar Autores");
        model.addAttribute("listaAutores", autores);
        model.addAttribute("page", renderPagina);
        model.addAttribute("autorB", new Autor());
        model.addAttribute("nacionalidad", nacionalidades);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/autor/gestionAutor";
        }

        try {
            autorService.save(autor);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.autorDuplicado",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("nombre", "nombre", msg);
            bindingResult.rejectValue("apellidoUno", "apellidoUno", msg);
            bindingResult.rejectValue("apellidoDos", "apellidoDos", msg);
            bindingResult.rejectValue("nacionalidad", "nacionalidad", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/autor/gestionAutor";
        }

        model.addAttribute("autor", new Autor());
//        redirectAttributes.addFlashAttribute("success","Se almaceno con éxito: " + editorial.getTipoClasificacion());
        return "redirect:/libreria/gestionar/autor";
    }

    @PostMapping("edit-autor")
    public String editAutor(@Valid Autor autor,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        List<Nacionalidad> nacionalidades = nacionalidadService.findAll();

        model.addAttribute("contenido", "Gestionar Autores");
        model.addAttribute("id", autor.getId());
        model.addAttribute("nacionalidad", nacionalidades);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            return "principal/autor/editAutor";
        }

        try {
            Autor autorEdit = new Autor();
            Optional<Autor> autorOp = autorService.findById(autor.getId());
            if (autorOp.isPresent()) {
                autorEdit = autorOp.get();
            }
            autorEdit.setNombre(autor.getNombre());
            autorEdit.setApellidoUno(autor.getApellidoUno());
            autorEdit.setApellidoDos(autor.getApellidoDos());
            autorEdit.setNacionalidad(autor.getNacionalidad());

            autorService.save(autorEdit);
            redirectAttributes.addFlashAttribute("success",
                    "Se almaceno exitosamente... Autor: "
                            + autorEdit.getNombre() + " "
                            + autorEdit.getApellidoUno() + " "
                            + autorEdit.getApellidoDos() + " Nacionalidad:"
                            + autorEdit.getNacionalidad().getNacionalidadName()
            );
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.autorDuplicado",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("nombre", "nombre", msg);
            bindingResult.rejectValue("apellidoUno", "apellidoUno", msg);
            bindingResult.rejectValue("apelldidoDos", "apelldidoDos", msg);
            bindingResult.rejectValue("nacionalidad", "nacionalidad", msg);
            return "principal/autor/editAutor";
        }

        return "redirect:/libreria/gestionar/autor";
    }

    @GetMapping("edit-autor/{id}")
    public String modificarAutor(@PathVariable("id") Integer id, Model modelo) {
        Optional<Autor> autor = autorService.findById(id);
        List<Nacionalidad> nacionalidad = nacionalidadService.findAll();

        modelo.addAttribute("autor", autor);
        modelo.addAttribute("nacionalidad", nacionalidad);
        modelo.addAttribute("contenido", "Modificar Autor");
        return "principal/autor/editAutor";
    }

    @GetMapping("delete-autor/{id}")
    public String eliminarAutor(@PathVariable("id") Integer id,
                                RedirectAttributes modelo) {
        autorService.deleteById(id);

        return "redirect:/libreria/gestionar/autor";
    }

    @GetMapping(value = "buscar-autor-nombre/{dato}", produces = "application/json")
    public @ResponseBody List<AutorDto> findAutor(@PathVariable String dato) {
        return autorService.findAutorView(dato);
    }

    @GetMapping("buscar-autor-tabla")
    public String getBuscarAutorTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String nombre,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Autor> pageAutores = autorService.searchAutor(nombre, pageable);
        RenderPagina<Autor> renderPagina = new RenderPagina<>("/libreria/gestionar/autor/buscar-autor-tabla", pageAutores);
        List<Nacionalidad> nacionalidades = nacionalidadService.findAll();

        model.addAttribute("autor", new Autor());
        model.addAttribute("autorB", new Autor(nombre));
        model.addAttribute("nacionalidad", nacionalidades);
        model.addAttribute("contenido", "Gestionar Autores");
        model.addAttribute("listaAutores", pageAutores);
        model.addAttribute("page", renderPagina);
        model.addAttribute("datoAbuscar", nombre);

        return "principal/autor/gestionAutor";
    }

    @PostMapping("buscar-autor-tabla")
    public String buscarAutorTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String nombre,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Autor> pageAutores = autorService.searchAutor(nombre, pageable);
        RenderPagina<Autor> renderPagina = new RenderPagina<>("/libreria/gestionar/autor/buscar-autor-tabla", pageAutores);
        List<Nacionalidad> nacionalidades = nacionalidadService.findAll();

        model.addAttribute("autor", new Autor());
        model.addAttribute("autorB", new Autor(nombre));
        model.addAttribute("nacionalidad", nacionalidades);
        model.addAttribute("contenido", "Gestionar Autores");
        model.addAttribute("listaEditoriales", pageAutores);
        model.addAttribute("page", renderPagina);
        model.addAttribute("datoAbuscar", nombre);

        return "principal/autor/gestionAutor";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.autorDuplicado",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
