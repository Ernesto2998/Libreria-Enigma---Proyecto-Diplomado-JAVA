package dgtic.core.controller;

import dgtic.core.model.Editorial;
import dgtic.core.service.EditorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "user/manager/gestionar")
public class ManageEditorialController {

    @Autowired
    MessageSource mensaje;

    @Autowired
    EditorialService editorialService;

    @GetMapping("editorial")
    public String getGestionar(Model modelo){

        modelo.addAttribute("editorial", new Editorial());
        modelo.addAttribute("contenido", "Gestionar Editorial");
        modelo.addAttribute("description",
                "Se mostrará una tabla con Editoriales y se podrá editar o añadir");
        return "principal/gestionEditorial";
    }

    @PostMapping("recibir-editorial")
    public String recibirEditorial(@Valid Editorial editorial,
                                   BindingResult bindingResult,
                                   Model model){

        if(bindingResult.hasErrors()){
            for(ObjectError error: bindingResult.getAllErrors()){
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/gestionEditorial";
        }

        try{
            editorialService.save(editorial);
        }catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.editorialDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("editorialName", "editorialName", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/gestionEditorial";
        }

        String cadena="Editorial : "+editorial.getEditorialName();
        model.addAttribute("info",cadena);
        model.addAttribute("editorial",new Editorial());
        model.addAttribute("contenido","Los datos que ingresas son:");
        model.addAttribute("description", cadena);

        return "principal/gestionEditorial";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String errorRuntimeDuplicated(SQLIntegrityConstraintViolationException e,
                                  Model model){
        String msg= mensaje.getMessage("Error.base.duplicado",
                null, LocaleContextHolder.getLocale());
        model.addAttribute("explicacion",msg);
        return "error-general";
    }
}
