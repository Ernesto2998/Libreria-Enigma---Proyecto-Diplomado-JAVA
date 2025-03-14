package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "user/manager")
public class ManageController {
    @GetMapping("buscar-libro")
    public String getBuscarLibro(Model modelo){
        modelo.addAttribute("contenido", "Buscar libros");
        modelo.addAttribute("description",
                "En esta pagina se podrán hacer busquedas avanzadas para los libros");
        return "principal/libros";
    }

    @GetMapping("gestionar/{gestion}")
    public String getGestionar(@PathVariable("gestion") String gestion, Model modelo){
        modelo.addAttribute("contenido", "Gestionar " + gestion);
        modelo.addAttribute("description",
                "Se mostrará una tabla con " + gestion + " y se podrá editar o añadir");
        return "principal/gestion";
    }
}
