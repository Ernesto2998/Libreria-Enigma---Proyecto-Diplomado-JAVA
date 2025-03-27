package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
