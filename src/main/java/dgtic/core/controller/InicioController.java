package dgtic.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InicioController {
    @Value("${mensaje.application}")
    private String valor;

    @GetMapping(value = "/")
    public String inicio(Model model){
        model.addAttribute("valor", valor);
        model.addAttribute("contenido", "En esta página habrá un login.");
        model.addAttribute("contenido2", "Esta página cambiará en 10 seg");
        return "inicio";
    }

    @RequestMapping(value = "principal", method = RequestMethod.GET)
    public String principal(){
        return "principal";
    }
}
