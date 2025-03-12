package dgtic.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InicioController {
    @Value("${mensaje.application}")
    private String valor;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "modelo", method = RequestMethod.GET)
    public String lenguajeExpresivo(Model model){
        model.addAttribute("mensaje", "Diplomado de Spring");
        return "inicio";
    }

    @RequestMapping(value = "propiedades", method = RequestMethod.GET)
    public String propiedades(Model model){
        model.addAttribute("valor", valor);
        return "inicio";
    }

    @RequestMapping(value = "principal", method = RequestMethod.GET)
    public String principal(){
        return "principal";
    }
}
