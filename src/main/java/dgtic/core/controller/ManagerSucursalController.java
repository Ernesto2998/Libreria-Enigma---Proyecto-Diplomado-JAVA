package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.model.dto.LibroDto;
import dgtic.core.model.dto.SucursalDto;
import dgtic.core.service.sucursal.SucursalService;
import dgtic.core.service.pais.PaisService;
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
@RequestMapping(value = "libreria/gestionar/sucursal")
public class ManagerSucursalController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    SucursalService sucursalService;
    @Autowired
    PaisService paisService;

    @GetMapping("")
    public String getGestionar(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Sucursal> sucursales = sucursalService.findPage(pageable);
        RenderPagina<Sucursal> renderPagina = new RenderPagina<>("sucursal", sucursales);
        List<Pais> paises = paisService.findAll();

        modelo.addAttribute("sucursal", new Sucursal());
        modelo.addAttribute("sucursalB", new Sucursal());
        modelo.addAttribute("paises", paises);
        modelo.addAttribute("paisId", "");
        modelo.addAttribute("contenido", "Gestionar Sucursales");
        modelo.addAttribute("listaSucursales", sucursales);
        modelo.addAttribute("page", renderPagina);
        return "principal/sucursal/gestionSucursal";
    }

    @PostMapping("add-sucursal")
    public String addLibro(@RequestParam(name = "page", defaultValue = "0") int page,
                           @Valid Sucursal sucursal,
                           BindingResult bindingResult,
                           Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Sucursal> sucursales = sucursalService.findPage(pageable);
        RenderPagina<Sucursal> renderPagina = new RenderPagina<>("/libreria/gestionar/sucursal/buscar-sucursal-tabla", sucursales);

        List<Pais> paises = paisService.findAll();

        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("sucursalB", new Sucursal());
        model.addAttribute("paises", paises);
        model.addAttribute("paisId", "");
        model.addAttribute("contenido", "Gestionar Sucursales");
        model.addAttribute("listaSucursales", sucursales);
        model.addAttribute("page", renderPagina);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/sucursal/gestionSucursal";
        }

        try {
            sucursalService.save(sucursal);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.sucursalDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("calle", "calle", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/sucursal/gestionSucursal";
        }

        model.addAttribute("sucursal", new Sucursal());
        return "redirect:/libreria/gestionar/sucursal";
    }

//    @PostMapping("edit-libro")
//    public String editLibro(@Valid Libro libro,
//                            BindingResult bindingResult,
//                            RedirectAttributes redirectAttributes,
//                            Model model) {
//
//        List<Autor> listaAutores = autorService.findAll();
//        List<Clasificacion> listaClasificaciones = clasificacionService.findAll();
//        List<Editorial> listaEditoriales = editorialService.findAll();
//
//        model.addAttribute("contenido", "Gestionar Libros");
//        model.addAttribute("id", libro.getId());
//        model.addAttribute("autores", listaAutores);
//        model.addAttribute("clasificaciones", listaClasificaciones);
//        model.addAttribute("editoriales", listaEditoriales);
//
//        if (bindingResult.hasErrors()) {
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                System.out.println("Error " + error.getDefaultMessage());
//            }
//            return "principal/libro/editLibro";
//        }
//
//        try {
//            Libro libroEdit = new Libro();
//            Optional<Libro> libroOp = libroService.findById(libro.getId());
//            if (libroOp.isPresent()) {
//                libroEdit = libroOp.get();
//            }
//            libroEdit.setTitulo(libro.getTitulo());
//            libroEdit.setTipoPasta(libro.getTipoPasta());
//            libroEdit.setAutores(libro.getAutores());
//            libroEdit.setClasificaciones(libro.getClasificaciones());
//            libroEdit.setEditorial(libro.getEditorial());
//            libroEdit.setPrecio(libro.getPrecio());
//            libroEdit.setDescuento(libro.getDescuento());
//            libroEdit.setSinopsis(libro.getSinopsis());
//
//            libroService.save(libroEdit);
//            redirectAttributes.addFlashAttribute("success",
//                    "Se almaceno exitosamente... Autor: "
//                            + libroEdit.getTitulo() + " Editorial: "
//                            + libroEdit.getEditorial().getEditorialName() + " Precio: "
//                            + libroEdit.getPrecio() + " Descuento:"
//                            + libroEdit.getDescuento() + " ...y más"            );
//        } catch (Exception e) {
//            String msg = mensaje.getMessage("Error.base.libroDuplicado",
//                    null, LocaleContextHolder.getLocale());
//
////            model.addAttribute("libro", new Libro());
////            model.addAttribute("libroB", new Libro());
//            bindingResult.rejectValue("titulo", "titulo", msg);
//            bindingResult.rejectValue("tipoPasta", "tipoPasta", msg);
//            bindingResult.rejectValue("autores", "autores", msg);
//            bindingResult.rejectValue("clasificaciones", "clasificaciones", msg);
//            bindingResult.rejectValue("editoriales", "editoriales", msg);
//
//            return "principal/libro/editLibro";
//        }
//
//        return "redirect:/libreria/gestionar/libro";
//    }
//
//    @GetMapping("edit-libro/{id}")
//    public String modificarLibro(@PathVariable("id") Integer id, Model modelo) {
//        Libro libro = libroService.findById(id).orElseThrow();
//
//        List<Autor> listaAutores = autorService.findAll();
//        List<Clasificacion> listaClasificaciones = clasificacionService.findAll();
//        List<Editorial> listaEditoriales = editorialService.findAll();
//
//        modelo.addAttribute("libro", libro);
//        modelo.addAttribute("autores", listaAutores);
//        modelo.addAttribute("clasificaciones", listaClasificaciones);
//        modelo.addAttribute("editoriales", listaEditoriales);
//        modelo.addAttribute("contenido", "Modificar Libro");
//        return "principal/libro/editLibro";
//    }

    @GetMapping("delete-sucursal/{id}")
    public String eliminarSucursal(@PathVariable("id") Integer id,
                                RedirectAttributes modelo) {
        sucursalService.deleteById(id);
        return "redirect:/libreria/gestionar/sucursal";
    }

    @GetMapping(value = "buscar-sucursal-calle/{dato}", produces = "application/json")
    public @ResponseBody List<SucursalDto> findSucursalCalle(@PathVariable String dato) {
        return sucursalService.findSucursalViewCalle(dato);
    }

    @GetMapping(value = "buscar-sucursal-colonia/{dato}", produces = "application/json")
    public @ResponseBody List<SucursalDto> findSucursalColonia(@PathVariable String dato) {
        return sucursalService.findSucursalViewColonia(dato);
    }

    @GetMapping(value = "buscar-sucursal-municipio/{dato}", produces = "application/json")
    public @ResponseBody List<SucursalDto> findSucursalMunicipio(@PathVariable String dato) {
        return sucursalService.findSucursalViewMunicipio(dato);
    }

    @GetMapping("buscar-sucursal-tabla")
    public String getBuscarSucursalTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String calle,
            @RequestParam(name = "coloniaB", required = false, defaultValue = "") String colonia,
            @RequestParam(name = "municipioB", required = false) String municipio,
            @RequestParam(name = "cpB", required = false) Integer cp,
            @RequestParam(name = "paisId", required = false) Integer paisId,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Sucursal> sucursales = sucursalService.findPage(pageable);

        Sucursal sucursalBusqueda = new Sucursal();

        if (!calle.isEmpty()) {
            sucursales = sucursalService.findSucursalByCalle(calle, pageable);
            sucursalBusqueda.setCalle(calle);
        } else if (!colonia.isEmpty()) {
            sucursales = sucursalService.findSucursalByColonia(colonia, pageable);
            sucursalBusqueda.setColonia(colonia);
        } else if (!municipio.isEmpty()) {
            sucursales = sucursalService.findSucursalByMunicipio(municipio, pageable);
            sucursalBusqueda.setMunicipio(municipio);
        } else if (cp != null) {
            sucursales = sucursalService.findSucursalByCodigoPostal(cp, pageable);
            sucursalBusqueda.setCodigoPostal(cp);
        } else if (paisId != null) {
            sucursales = sucursalService.findSucursalByPaisId(paisId, pageable);
        }

        RenderPagina<Sucursal> renderPagina = new RenderPagina<>("/libreria/gestionar/sucursal/buscar-sucursal-tabla", sucursales);
        List<Pais> paises = paisService.findAll();

        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("sucursalB", new Sucursal());
        model.addAttribute("paises", paises);
        model.addAttribute("paisId", "");
        model.addAttribute("contenido", "Gestionar Sucursales");
        model.addAttribute("listaSucursales", sucursales);
        model.addAttribute("page", renderPagina);

        return "principal/sucursal/gestionSucursal";
    }

    @PostMapping("buscar-sucursal-tabla")
    public String buscarEdotiralTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "datoAbuscar", required = false, defaultValue = "") String calle,
            @RequestParam(name = "coloniaB", required = false, defaultValue = "") String colonia,
            @RequestParam(name = "municipioB", required = false) String municipio,
            @RequestParam(name = "cpB", required = false) Integer cp,
            @RequestParam(name = "paisId", required = false) Integer paisId,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Sucursal> sucursales = sucursalService.findPage(pageable);

        Sucursal sucursalBusqueda = new Sucursal();

        if (!calle.isEmpty()) {
            sucursales = sucursalService.findSucursalByCalle(calle, pageable);
            sucursalBusqueda.setCalle(calle);
        } else if (!colonia.isEmpty()) {
            sucursales = sucursalService.findSucursalByColonia(colonia, pageable);
            sucursalBusqueda.setColonia(colonia);
        } else if (!municipio.isEmpty()) {
            sucursales = sucursalService.findSucursalByMunicipio(municipio, pageable);
            sucursalBusqueda.setMunicipio(municipio);
        } else if (cp != null) {
            sucursales = sucursalService.findSucursalByCodigoPostal(cp, pageable);
            sucursalBusqueda.setCodigoPostal(cp);
        } else if (paisId != null) {
            sucursales = sucursalService.findSucursalByPaisId(paisId, pageable);
        }

        RenderPagina<Sucursal> renderPagina = new RenderPagina<>("/libreria/gestionar/sucursal/buscar-sucursal-tabla", sucursales);
        List<Pais> paises = paisService.findAll();

        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("sucursalB", new Sucursal());
        model.addAttribute("paises", paises);
        model.addAttribute("paisId", "");
        model.addAttribute("contenido", "Gestionar Sucursales");
        model.addAttribute("listaSucursales", sucursales);
        model.addAttribute("page", renderPagina);

        return "principal/sucursal/gestionSucursal";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.sucursalDuplicada",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
