package dgtic.core.controller;

import dgtic.core.model.Clasificacion;
import dgtic.core.service.ClasificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "user/manager/gestionar")
public class ManageClasificacionController {

    @Autowired
    MessageSource mensaje;

    @Autowired
    ClasificacionService clasificacionService;

    @GetMapping("clasificacion")
    public String getGestionar(Model modelo){

        modelo.addAttribute("clasificacion", new Clasificacion());
        modelo.addAttribute("contenido", "Gestionar Clasificaiones");
        modelo.addAttribute("description",
                "Se mostrará una tabla con Clasificaciones y se podrá editar o añadir");
        return "principal/gestionClasificacion";
    }

    @PostMapping("recibir-clasificacion")
    public String recibirClasificacion(@Valid Clasificacion clasificacion,
                                   BindingResult bindingResult,
                                   Model model){

        if(bindingResult.hasErrors()){
            for(ObjectError error: bindingResult.getAllErrors()){
                System.out.println("Error " + error.getDefaultMessage());
            }
            model.addAttribute("showModal", true); // Indica que el modal debe abrirse
            return "principal/gestionClasificacion";
        }

        try{
            clasificacionService.save(clasificacion);
        }catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.clasificacionDuplicada",
                    null, LocaleContextHolder.getLocale());
            bindingResult.rejectValue("tipoClasificacion", "tipoClasificacion", msg);
            model.addAttribute("showModal", true); // También abre el modal si hay error de BD
            return "principal/gestionClasificacion";
        }

        String cadena="Clasificación : "+ clasificacion.getTipoClasificacion();
        model.addAttribute("info",cadena);
        model.addAttribute("clasificacion",new Clasificacion());
        model.addAttribute("contenido","Los datos que ingresas son:");
        model.addAttribute("description", cadena);

        return "principal/gestionClasificacion";
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
