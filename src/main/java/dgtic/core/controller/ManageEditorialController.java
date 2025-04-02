package dgtic.core.controller;

import dgtic.core.model.Editorial;
import dgtic.core.model.dto.EditorialDto;
import dgtic.core.service.editorial.EditorialService;
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
@RequestMapping(value = "libreria/gestionar/editorial")
public class ManageEditorialController {

    @Autowired
    MessageSource mensaje;

    @Autowired
    EditorialService editorialService;

    @GetMapping("")
    public String getGestionar(@RequestParam(name = "page", defaultValue = "0") int page,
                               Model modelo) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Editorial> editoriales = editorialService.findPage(pageable);
        RenderPagina<Editorial> renderPagina = new RenderPagina<>("editorial", editoriales);

        modelo.addAttribute("editorial", new Editorial());
        modelo.addAttribute("editorialB", new Editorial());
        modelo.addAttribute("contenido", "Gestionar Editorial");
        modelo.addAttribute("listaEditoriales", editoriales);
        modelo.addAttribute("page", renderPagina);
        return "principal/editorial/gestionEditorial";
    }

    @PostMapping("add-editorial")
    public String addEditorial(@RequestParam(name = "page", defaultValue = "0") int page,
                               @Valid Editorial editorial,
//                                   RedirectAttributes redirectAttributes,
                               BindingResult bindingResult,
                               Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Editorial> editoriales = editorialService.findPage(pageable);
        RenderPagina<Editorial> renderPagina = new RenderPagina<>("/libreria/gestionar/editorial/buscar-editorial-tabla", editoriales);

        model.addAttribute("contenido", "Gestionar Editoriales");
        model.addAttribute("listaEditoriales", editoriales);
        model.addAttribute("page", renderPagina);
        model.addAttribute("editorialB", new Editorial());

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/editorial/gestionEditorial";
        }

        try {
            editorialService.save(editorial);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.editorialDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("editorialName", "editorialName", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/editorial/gestionEditorial";
        }

        model.addAttribute("editorial", new Editorial());
//        redirectAttributes.addFlashAttribute("success","Se almaceno con éxito: " + editorial.getTipoClasificacion());
        return "redirect:/libreria/gestionar/editorial";
    }

//    @GetMapping("add-editorial")
//    public String getAddEditorial(@RequestParam(name = "page", defaultValue = "0") int page,
//                                  @RequestParam(name = "editorialName", required = false, defaultValue = "") String editorialName,
//                                  BindingResult bindingResult,
//                                  Model model) {
//        Pageable pageable = PageRequest.of(page, 10);
//        Page<Editorial> editoriales = editorialService.findPage(pageable);
//        RenderPagina<Editorial> renderPagina = new RenderPagina<>("/libreria/gestionar/editorial/add-editorial", editoriales);
//
//        model.addAttribute("contenido", "Gestionar Editoriales");
//        model.addAttribute("listaEditoriales", editoriales);
//        model.addAttribute("page", renderPagina);
//        model.addAttribute("editorial", new Editorial());
//        model.addAttribute("editorialB", new Editorial());
//        model.addAttribute("editorialName", editorialName);
//
//        return "principal/clasificacion/gestionClasificacion";
//    }

    @PostMapping("edit-editorial")
    public String editEditorial(@Valid Editorial editorial,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        model.addAttribute("contenido", "Gestionar Editoriales");
        model.addAttribute("id", editorial.getId());

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error " + error.getDefaultMessage());
            }
            return "principal/editorial/editEditorial";
        }

        try {
            Editorial editorialEdit = new Editorial();
            Optional<Editorial> editorialOp = editorialService.findById(editorial.getId());
            if (editorialOp.isPresent()) {
                editorialEdit = editorialOp.get();
            }
            editorialEdit.setEditorialName(editorial.getEditorialName());
            editorialService.save(editorialEdit);
            redirectAttributes.addFlashAttribute("success", "Se almaceno con éxito: " + editorialEdit.getEditorialName());
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.editorialDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("editorialName", "editorialName", msg);
            return "principal/editorial/editEditorial";
        }

        return "redirect:/libreria/gestionar/editorial";
    }

    @GetMapping("edit-editorial/{id}")
    public String modificarEditorial(@PathVariable("id") Integer id, Model modelo) {
        Optional<Editorial> editorial = editorialService.findById(id);
        modelo.addAttribute("editorial", editorial);
        modelo.addAttribute("contenido", "Modificar Editorial");
        return "principal/editorial/editEditorial";
    }

    @GetMapping("delete-editorial/{id}")
    public String eliminarEditorial(@PathVariable("id") Integer id,
                                    RedirectAttributes modelo) {
        editorialService.deleteById(id);

        return "redirect:/libreria/gestionar/editorial";
    }

    @GetMapping(value = "buscar-editorial-nombre/{dato}", produces = "application/json")
    public @ResponseBody List<EditorialDto> findEditorial(@PathVariable String dato) {
        return editorialService.findEditorialView(dato);
    }

    @GetMapping("buscar-editorial-tabla")
    public String getBuscarEditorialTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "editorialName", required = false, defaultValue = "") String editorialName,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Editorial> pageEditoriales = editorialService.findEditorialByName(editorialName, pageable);
        RenderPagina<Editorial> renderPagina = new RenderPagina<>("/libreria/gestionar/editorial/buscar-editorial-tabla", pageEditoriales);

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editorialB", new Editorial(editorialName));
        model.addAttribute("contenido", "Gestionar Editoriales");
        model.addAttribute("listaEditoriales", pageEditoriales);
        model.addAttribute("page", renderPagina);
        model.addAttribute("editorialName", editorialName);

        return "principal/editorial/gestionEditorial";
    }

    @PostMapping("buscar-editorial-tabla")
    public String buscarEdotiralTabla(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "editorialName", required = false, defaultValue = "") String editorialName,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Editorial> pageEditoriales = editorialService.findEditorialByName(editorialName, pageable);
        RenderPagina<Editorial> renderPagina = new RenderPagina<>("/libreria/gestionar/editorial/buscar-editorial-tabla", pageEditoriales);

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editorialB", new Editorial(editorialName));
        model.addAttribute("contenido", "Gestionar Editoriales");
        model.addAttribute("listaEditoriales", pageEditoriales);
        model.addAttribute("page", renderPagina);
        model.addAttribute("tipoEditoriales", editorialName);

        return "principal/editoriales/gestionEditorial";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                         Model model) {
        String msg = mensaje.getMessage("Error.base.editorialDuplicada",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion", msg);
        return "error-general";
    }
}
