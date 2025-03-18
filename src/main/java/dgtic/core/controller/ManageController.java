package dgtic.core.controller;

import dgtic.core.service.AutorService;
import dgtic.core.service.ClasificacionService;
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
@RequestMapping(value = "user/manager")
public class ManageController {

    @GetMapping("gestionar/{gestion}")
    public String getGestionar(@PathVariable("gestion") String gestion, Model modelo){
        String capGestion = Character.toUpperCase(gestion.charAt(0)) + gestion.substring(1);
        String url = "";

        if (gestion.equals("autor"))
            url = "principal/gestionAutor";
        else if (gestion.equals("libro"))
            url = "principal/gestionLibro";

        modelo.addAttribute("contenido", "Gestionar " + gestion);
        modelo.addAttribute("description",
                "Se mostrará una tabla con " + gestion + " y se podrá editar o añadir");
        modelo.addAttribute("entidad", gestion);
        return url;
    }
}
