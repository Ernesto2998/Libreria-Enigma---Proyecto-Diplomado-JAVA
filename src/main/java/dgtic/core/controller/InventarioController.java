package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.ReportesPdf.ReportesPdfService;
import dgtic.core.service.inventario.InventarioService;
import dgtic.core.service.libro.LibroService;
import dgtic.core.service.sucursal.SucursalService;
import dgtic.core.util.RenderPagina;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    ReportesPdfService reportesPdfService;

    @GetMapping("")
    public String getInventario(@RequestParam(name = "page", defaultValue = "0") int page,
                                Model model) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by("libro.titulo").ascending());
        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
        RenderPagina<Inventario> renderPagina = new RenderPagina<>("inventario", pageInventarios);

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

//        modelo.addAttribute("libro", new Libro());
        model.addAttribute("inventarioB", new Inventario());
        model.addAttribute("inventario", new Inventario());
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("libros", libros);
//        modelo.addAttribute("clasificacionesB", listaClasificaciones);
//        modelo.addAttribute("editorialesB", listaEditoriales);
//        modelo.addAttribute("autorId", "");
//        modelo.addAttribute("clasificacionId", "");
//        modelo.addAttribute("editorialId", "");
        model.addAttribute("contenido", "Inventario");
        model.addAttribute("listaInventario", pageInventarios);
        model.addAttribute("page", renderPagina);
        return "principal/inventario/inventario";
    }

    @PostMapping("add-inventario")
    public String addClasificacion(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @Valid Inventario inventario,
//                                   RedirectAttributes redirectAttributes,
                                   BindingResult bindingResult,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Inventario> pageInventarios = inventarioService.findPage(pageable);
        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);

        List<Sucursal> sucursales = sucursalService.findAllByOrderByCalleAsc();
        List<Libro> libros = libroService.findAllByOrderByTituloAsc();

        model.addAttribute("contenido", "Inventario");
        model.addAttribute("listaInventario", pageInventarios);
        model.addAttribute("page", renderPagina);
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("libros", libros);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/inventario/inventario";
        }

        try {
            inventarioService.save(inventario);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.inventarioDuplicada",
                    null, LocaleContextHolder.getLocale());

            // Agrega error global
            bindingResult.reject(null, msg);

            // Abre modal otra vez
            model.addAttribute("showModal", true);
            return "principal/inventario/inventario";
        }

        return "redirect:/libreria/inventario";
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
            pageInventarios = inventarioService.findInventarioBySucursalId(sucursalId, pageable);
        } else if (libroId != null) {
            pageInventarios = inventarioService.findInventarioByLibroId(libroId, pageable);
        }

        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);

        model.addAttribute("inventarioB", new Inventario());
        model.addAttribute("inventario", new Inventario());
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
            pageInventarios = inventarioService.findInventarioBySucursalId(sucursalId, pageable);
        } else if (libroId != null) {
            pageInventarios = inventarioService.findInventarioByLibroId(libroId, pageable);
        }

        RenderPagina<Inventario> renderPagina = new RenderPagina<>("/libreria/inventario/buscar-inventario-tabla", pageInventarios);

        model.addAttribute("inventarioB", new Inventario());
        model.addAttribute("inventario", new Inventario());
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("libros", libros);
        model.addAttribute("sucursalId", sucursalId);
        model.addAttribute("libroId", libroId);
        model.addAttribute("contenido", "Inventario");
        model.addAttribute("listaInventario", pageInventarios);
        model.addAttribute("page", renderPagina);

        return "principal/inventario/inventario";
    }

    @PostMapping("edit-inventario")
    public String procesarEdicionInventario(@ModelAttribute Inventario inventario,
                                            BindingResult bindingResult,
                                            RedirectAttributes redirectAttributes,
                                            Model model) {
        model.addAttribute("contenido", "Inventario");
        model.addAttribute("id", inventario.getId());

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            return "principal/inventario/editInventario";
        }

        Inventario inventarioEdot = new Inventario();
        Optional<Inventario> inventarioOp = inventarioService.findById(inventario.getId());
        if (inventarioOp.isPresent()) {
            inventarioEdot = inventarioOp.get();
        }
        inventarioEdot.setStock(inventario.getStock());
        inventarioService.save(inventarioEdot);
        redirectAttributes.addFlashAttribute("success", "Se modificó con éxito el stock ");

        return "redirect:/libreria/inventario";
    }

    @GetMapping("edit-inventario/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Inventario inventario = inventarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventario no encontrado con ID: " + id));

        model.addAttribute("inventario", inventario);
        model.addAttribute("contenido", "Modificar Stock");
        return "principal/inventario/editInventario";
    }

    @GetMapping("delete-inventario/{id}")
    public String eliminarInventario(@PathVariable("id") Integer id,
                                     RedirectAttributes modelo) {
        inventarioService.deleteBy(id);
        return "redirect:/libreria/inventario";
    }

    @GetMapping("reporte")
    public void generarReportePDF(HttpServletResponse response) throws IOException {

        List<Inventario> inventarios = inventarioService.findAll();

        ByteArrayInputStream bis = reportesPdfService.generarReporteInventario(inventarios);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_inventario.pdf");
        IOUtils.copy(bis, response.getOutputStream());
    }
}
