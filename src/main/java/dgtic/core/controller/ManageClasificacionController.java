package dgtic.core.controller;

import dgtic.core.model.Clasificacion;
import dgtic.core.model.dto.ClasificacionDto;
import dgtic.core.service.clasificacion.ClasificacionService;
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
@RequestMapping(value = "libreria/gestionar/clasificacion")
public class ManageClasificacionController {

    @Autowired
    MessageSource mensaje;

    @Autowired
    ClasificacionService clasificacionService;

    @GetMapping("")
    public String getGestionar(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Clasificacion> clasificaciones = clasificacionService.findPage(pageable);
        RenderPagina<Clasificacion> renderPagina = new RenderPagina<>("clasificacion", clasificaciones);

        modelo.addAttribute("clasificacion", new Clasificacion());
        modelo.addAttribute("clasificacionB", new Clasificacion());
        modelo.addAttribute("contenido", "Gestionar Clasificaciones");
        modelo.addAttribute("listaClasificaciones", clasificaciones);
        modelo.addAttribute("page", renderPagina);
        return "principal/clasificacion/gestionClasificacion";
    }

    @PostMapping("add-clasificacion")
    public String addClasificacion(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @Valid Clasificacion clasificacion,
//                                   RedirectAttributes redirectAttributes,
                                   BindingResult bindingResult,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Clasificacion> clasificaciones = clasificacionService.findPage(pageable);
        RenderPagina<Clasificacion> renderPagina = new RenderPagina<>("/libreria/gestionar/clasificacion/buscar-clasificacion-tabla", clasificaciones);

        model.addAttribute("contenido", "Gestionar Clasificaciones");
        model.addAttribute("listaClasificaciones", clasificaciones);
        model.addAttribute("page", renderPagina);
        model.addAttribute("clasificacionB", new Clasificacion());

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/clasificacion/gestionClasificacion";
        }

        try {
            clasificacionService.save(clasificacion);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.clasificacionDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("tipoClasificacion", "tipoClasificacion", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/clasificacion/gestionClasificacion";
        }

        String cadena = "Clasificación : " + clasificacion.getTipoClasificacion();
        model.addAttribute("info", cadena);
        model.addAttribute("clasificacion", new Clasificacion());
//        redirectAttributes.addFlashAttribute("success","Se almaceno con éxito: " + clasificacion.getTipoClasificacion());
        return "redirect:/libreria/gestionar/clasificacion";
    }

    @GetMapping("add-clasificacion")
    public String getAddClasificacion(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "tipoClasificacion", required = false, defaultValue = "") String tipoClasificacion,
                                      BindingResult bindingResult,
                                      Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Clasificacion> clasificaciones = clasificacionService.findPage(pageable);
        RenderPagina<Clasificacion> renderPagina = new RenderPagina<>("/libreria/gestionar/clasificacion/add-clasificacion", clasificaciones);

        model.addAttribute("contenido", "Gestionar Clasificaciones");
        model.addAttribute("listaClasificaciones", clasificaciones);
        model.addAttribute("page", renderPagina);
        model.addAttribute("clasificacion", new Clasificacion());
        model.addAttribute("clasificacionB", new Clasificacion());
        model.addAttribute("tipoClasificacion", tipoClasificacion);

        return "principal/clasificacion/gestionClasificacion";
    }

    @PostMapping("edit-clasificacion")
    public String editClasificacion( @Valid Clasificacion clasificacion,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        model.addAttribute("contenido", "Gestionar Clasificaciones");

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/clasificacion/gestionClasificacion";
        }

        try {
            Clasificacion clasificacionEdit = new Clasificacion();
            Optional<Clasificacion> clasificacionOp = clasificacionService.findById(clasificacion.getId());
            if(clasificacionOp.isPresent()){clasificacionEdit = clasificacionOp.get();}
            clasificacionEdit.setTipoClasificacion(clasificacion.getTipoClasificacion());
            clasificacionService.save(clasificacionEdit);
            redirectAttributes.addFlashAttribute("success","Se almaceno con éxito: " + clasificacionEdit.getTipoClasificacion());
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.clasificacionDuplicada",
                    null, LocaleContextHolder.getLocale());
            model.addAttribute("id", clasificacion.getId());
            bindingResult.rejectValue("tipoClasificacion", "tipoClasificacion", msg);
            return "principal/clasificacion/editClasificacion";
        }

        String cadena = "Clasificación : " + clasificacion.getTipoClasificacion();
        model.addAttribute("info", cadena);
        model.addAttribute("clasificacion", new Clasificacion());

        return "redirect:/libreria/gestionar/clasificacion";
    }

    @GetMapping("edit-clasificacion/{id}")
    public String modificarClasificacion(@PathVariable("id") Integer id, Model modelo) {
        Optional<Clasificacion> clasificacion = clasificacionService.findById(id);
        modelo.addAttribute("clasificacion", clasificacion);
        modelo.addAttribute("contenido", "Modificar Clasificacion");
        return "principal/clasificacion/editClasificacion";
    }


    @GetMapping("delete-clasificacion/{id}")
    public String eliminarClasificacion(@PathVariable("id") Integer id,
                                        RedirectAttributes modelo) {
        clasificacionService.deleteById(id);
//        modelo.addFlashAttribute("success","Se borro con éxito Lote");
        return "redirect:/libreria/gestionar/clasificacion";
    }

    @GetMapping(value = "buscar-clasificacion-nombre/{dato}", produces = "application/json")
    public @ResponseBody List<ClasificacionDto> findClasificacion(@PathVariable String dato) {
        return clasificacionService.findClasificacionView(dato);
    }

    @GetMapping("buscar-clasificacion-tabla")
    public String getBuscarClasificacionTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "tipoClasificacion", required = false, defaultValue = "") String tipoClasificacion,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Clasificacion> pageClasificaciones = clasificacionService.findClasificacionByName(tipoClasificacion, pageable);
        RenderPagina<Clasificacion> renderPagina = new RenderPagina<>("/libreria/gestionar/clasificacion/buscar-clasificacion-tabla", pageClasificaciones);

        model.addAttribute("clasificacion", new Clasificacion());
        model.addAttribute("clasificacionB", new Clasificacion(tipoClasificacion));
        model.addAttribute("contenido", "Gestionar Clasificaciones");
        model.addAttribute("listaClasificaciones", pageClasificaciones);
        model.addAttribute("page", renderPagina);
        model.addAttribute("tipoClasificacion", tipoClasificacion);

        return "principal/clasificacion/gestionClasificacion";
    }

    @PostMapping("buscar-clasificacion-tabla")
    public String buscarClasificacionTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "tipoClasificacion", required = false, defaultValue = "") String tipoClasificacion,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Clasificacion> pageClasificaciones = clasificacionService.findClasificacionByName(tipoClasificacion, pageable);
        RenderPagina<Clasificacion> renderPagina = new RenderPagina<>("/libreria/gestionar/clasificacion/buscar-clasificacion-tabla", pageClasificaciones);

        model.addAttribute("clasificacion", new Clasificacion());
        model.addAttribute("clasificacionB", new Clasificacion(tipoClasificacion));
        model.addAttribute("contenido", "Gestionar Clasificaciones");
        model.addAttribute("listaClasificaciones", pageClasificaciones);
        model.addAttribute("page", renderPagina);
        model.addAttribute("tipoClasificacion", tipoClasificacion);

        return "principal/clasificacion/gestionClasificacion";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.clasificacionDuplicada",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
